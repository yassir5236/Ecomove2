

package dao;

import model.Billet;
import model.TypeTransport;
import model.StatutBillet;
import util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BilletDAO {
    private final Connection connection = DatabaseConnection.getConnection();

    public void addBillet(Billet billet) {
        String query = "INSERT INTO billet (id, contrat_id, type_transport, prix_achat, prix_vente, date_vente, statut_billet, ville_depart, ville_destination, date_depart, horaire, duree) " +
                "VALUES (?, ?, ?::type_transport, ?, ?, ?, ?::statut_billet, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, billet.getId());
            statement.setObject(2, billet.getContratId());
            statement.setString(3, billet.getTypeTransport().name().toLowerCase());
            statement.setBigDecimal(4, billet.getPrixAchat());
            statement.setBigDecimal(5, billet.getPrixVente());
            statement.setDate(6, java.sql.Date.valueOf(billet.getDateVente()));
            statement.setString(7, billet.getStatutBillet().name().toLowerCase());
            statement.setString(8, billet.getVilleDepart());
            statement.setString(9, billet.getVilleDestination());
            statement.setDate(10, java.sql.Date.valueOf(billet.getDateDepart()));
            statement.setTime(11, java.sql.Time.valueOf(billet.getHoraire()));

            // Insérer la durée en heures
            statement.setBigDecimal(12, BigDecimal.valueOf(billet.getDuree().toHours()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Billet> getAllBillets() {
        List<Billet> billets = new ArrayList<>();
        String query = "SELECT * FROM billet";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject("id");
                UUID contratId = (UUID) resultSet.getObject("contrat_id");
                TypeTransport typeTransport = TypeTransport.valueOf(resultSet.getString("type_transport").toUpperCase());
                BigDecimal prixAchat = resultSet.getBigDecimal("prix_achat");
                BigDecimal prixVente = resultSet.getBigDecimal("prix_vente");
                LocalDate dateVente = resultSet.getDate("date_vente").toLocalDate();
                StatutBillet statutBillet = StatutBillet.valueOf(resultSet.getString("statut_billet").toUpperCase());
                String villeDepart = resultSet.getString("ville_depart");
                String villeDestination = resultSet.getString("ville_destination");
                LocalDate dateDepart = resultSet.getDate("date_depart").toLocalDate();
                LocalTime horaire = resultSet.getTime("horaire").toLocalTime();

                // Lire la durée en heures
                BigDecimal dureeHeures = resultSet.getBigDecimal("duree");
                Duration duree = Duration.ofHours(dureeHeures.longValue());

                Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, dateVente, statutBillet,
                        villeDepart, villeDestination, dateDepart, horaire, duree);
                billets.add(billet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billets;
    }

    public Billet getBilletById(UUID id) {
        Billet billet = null;
        String query = "SELECT * FROM billet WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                billet = new Billet(
                        id,
                        (UUID) resultSet.getObject("contrat_id"),
                        TypeTransport.valueOf(resultSet.getString("type_transport").toUpperCase()),
                        resultSet.getBigDecimal("prix_achat"),
                        resultSet.getBigDecimal("prix_vente"),
                        resultSet.getDate("date_vente").toLocalDate(),
                        StatutBillet.valueOf(resultSet.getString("statut_billet").toUpperCase()),
                        resultSet.getString("ville_depart"),
                        resultSet.getString("ville_destination"),
                        resultSet.getDate("date_depart").toLocalDate(),
                        resultSet.getTime("horaire").toLocalTime(),
                        // Lire la durée en heures
                        Duration.ofHours(resultSet.getBigDecimal("duree").longValue())
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billet;
    }

    public void updateBillet(Billet billet) {
        String query = "UPDATE billet SET contrat_id = ?, type_transport = CAST(? AS type_transport), prix_achat = ?, prix_vente = ?, date_vente = ?, statut_billet = CAST(? AS statut_billet), ville_depart = ?, ville_destination = ?, date_depart = ?, horaire = ?, duree = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, billet.getContratId());
            statement.setString(2, billet.getTypeTransport().name().toLowerCase());
            statement.setBigDecimal(3, billet.getPrixAchat());
            statement.setBigDecimal(4, billet.getPrixVente());
            statement.setDate(5, java.sql.Date.valueOf(billet.getDateVente()));
            statement.setString(6, billet.getStatutBillet().name().toLowerCase());
            statement.setString(7, billet.getVilleDepart());
            statement.setString(8, billet.getVilleDestination());
            statement.setDate(9, java.sql.Date.valueOf(billet.getDateDepart()));
            statement.setTime(10, java.sql.Time.valueOf(billet.getHoraire()));

            // Mettre à jour la durée en heures
            statement.setBigDecimal(11, BigDecimal.valueOf(billet.getDuree().toHours()));
            statement.setObject(12, billet.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBillet(UUID id) {
        String query = "DELETE FROM billet WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public List<Billet> searchBillets(String villeDepart, String villeDestination, String dateDepart) {
        List<Billet> billets = new ArrayList<>();
        String sql = "SELECT * FROM billets WHERE ville_depart = ? AND ville_destination = ? AND date_depart = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eco_move", "user", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, villeDepart);
            pstmt.setString(2, villeDestination);
            pstmt.setDate(3, Date.valueOf(LocalDate.parse(dateDepart))); // Conversion de LocalDate en Date

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                UUID contratId = UUID.fromString(rs.getString("contrat_id"));
                TypeTransport typeTransport = TypeTransport.valueOf(rs.getString("type_transport")); // Assurez-vous que c'est le bon type
                BigDecimal prixAchat = rs.getBigDecimal("prix_achat");
                BigDecimal prixVente = rs.getBigDecimal("prix_vente");
                LocalDate dateVente = rs.getDate("date_vente").toLocalDate();
                StatutBillet statutBillet = StatutBillet.valueOf(rs.getString("statut_billet")); // Assurez-vous que c'est le bon type
                String villeDepartDb = rs.getString("ville_depart");
                String villeDestinationDb = rs.getString("ville_destination");
                LocalDate dateDepartDb = rs.getDate("date_depart").toLocalDate();
                LocalTime horaire = rs.getTime("horaire").toLocalTime();
                Duration duree = Duration.ofMinutes(rs.getLong("duree")); // Conversion à partir de la durée stockée en minutes

                Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, dateVente, statutBillet,
                        villeDepartDb, villeDestinationDb, dateDepartDb, horaire, duree);
                billets.add(billet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billets;
    }


}
