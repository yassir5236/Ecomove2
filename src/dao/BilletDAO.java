package dao;

import java.time.LocalDate;


import dao.interfaces.IBilletDAO;
import model.Billet;
import model.TypeTransport;
import model.StatutBillet;
import util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BilletDAO implements IBilletDAO {
    private final Connection connection ;

    public BilletDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addBillet(Billet billet) {
        String query = "INSERT INTO billet (id, contrat_id, trajet_id, type_transport, prix_achat, prix_vente, date_vente, statut_billet, date_depart, horaire) " +
                "VALUES (?, ?, ?, ?::type_transport, ?, ?, ?, ?::statut_billet, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, billet.getId());
            statement.setObject(2, billet.getContratId());
            statement.setInt(3, billet.getTrajetId());
            statement.setString(4, billet.getTypeTransport().name().toLowerCase());
            statement.setBigDecimal(5, billet.getPrixAchat());
            statement.setBigDecimal(6, billet.getPrixVente());
            statement.setTimestamp(7, billet.getDateVente());
            statement.setString(8, billet.getStatutBillet().name().toLowerCase());
            statement.setDate(9, billet.getDateDepart());
            statement.setTime(10, billet.getHoraire());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Billet> getAllBillets() {
        List<Billet> billets = new ArrayList<>();
        String query = "SELECT * FROM billet";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject("id");
                UUID contratId = (UUID) resultSet.getObject("contrat_id");
                int trajetId = resultSet.getInt("trajet_id");
                TypeTransport typeTransport = TypeTransport.valueOf(resultSet.getString("type_transport").toUpperCase());
                BigDecimal prixAchat = resultSet.getBigDecimal("prix_achat");
                BigDecimal prixVente = resultSet.getBigDecimal("prix_vente");
                Timestamp dateVente = resultSet.getTimestamp("date_vente");
                StatutBillet statutBillet = StatutBillet.valueOf(resultSet.getString("statut_billet").toUpperCase());
                java.sql.Date dateDepart = resultSet.getDate("date_depart");
                java.sql.Time horaire = resultSet.getTime("horaire");

                Billet billet = new Billet(id, typeTransport, prixAchat, prixVente, dateVente, statutBillet, contratId, trajetId, dateDepart, horaire);
                billets.add(billet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billets;
    }

    @Override
    public Billet getBilletById(UUID id) {
        Billet billet = null;
        String query = "SELECT * FROM billet WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                billet = new Billet(
                        id,
                        TypeTransport.valueOf(resultSet.getString("type_transport").toUpperCase()),
                        resultSet.getBigDecimal("prix_achat"),
                        resultSet.getBigDecimal("prix_vente"),
                        resultSet.getTimestamp("date_vente"),
                        StatutBillet.valueOf(resultSet.getString("statut_billet").toUpperCase()),
                        (UUID) resultSet.getObject("contrat_id"),
                        resultSet.getInt("trajet_id"),
                        resultSet.getDate("date_depart"),
                        resultSet.getTime("horaire")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billet;
    }

    @Override
    public void updateBillet(Billet billet) {
        String query = "UPDATE billet SET contrat_id = ?, trajet_id = ?, type_transport = CAST(? AS type_transport), prix_achat = ?, prix_vente = ?, date_vente = ?, statut_billet = CAST(? AS statut_billet), date_depart = ?, horaire = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, billet.getContratId());
            statement.setInt(2, billet.getTrajetId());
            statement.setString(3, billet.getTypeTransport().name().toLowerCase());
            statement.setBigDecimal(4, billet.getPrixAchat());
            statement.setBigDecimal(5, billet.getPrixVente());
            statement.setTimestamp(6, billet.getDateVente());
            statement.setString(7, billet.getStatutBillet().name().toLowerCase());
            statement.setDate(8, billet.getDateDepart());
            statement.setTime(9, billet.getHoraire());
            statement.setObject(10, billet.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBillet(UUID id) {
        String query = "DELETE FROM billet WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public List<Billet> searchBillets(String villeDepart, String villeDestination, String dateDepart) {
//        List<Billet> billets = new ArrayList<>();
//        String sql = "SELECT * FROM billet WHERE ville_depart = ? AND ville_destination = ? AND date_depart = ?";
//
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setString(1, villeDepart);
//            pstmt.setString(2, villeDestination);
//            pstmt.setDate(3, Date.valueOf(LocalDate.parse(dateDepart))); // Conversion de LocalDate en Date
//
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                UUID id = UUID.fromString(rs.getString("id"));
//                UUID contratId = UUID.fromString(rs.getString("contrat_id"));
//                int trajetId = rs.getInt("trajet_id");
//                TypeTransport typeTransport = TypeTransport.valueOf(rs.getString("type_transport"));
//                BigDecimal prixAchat = rs.getBigDecimal("prix_achat");
//                BigDecimal prixVente = rs.getBigDecimal("prix_vente");
//                Timestamp dateVente = rs.getTimestamp("date_vente");
//                StatutBillet statutBillet = StatutBillet.valueOf(rs.getString("statut_billet"));
//                java.sql.Date dateDepartDb = rs.getDate("date_depart");
//                java.sql.Time horaire = rs.getTime("horaire");
//
//                Billet billet = new Billet(id, typeTransport, prixAchat, prixVente, dateVente, statutBillet, contratId, trajetId, dateDepartDb, horaire);
//                billets.add(billet);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return billets;
//    }


    @Override
    public List<Billet> searchBillets(String villeDepart, String villeDestination, String dateDepart) {
        List<Billet> billets = new ArrayList<>();
        String sql = "SELECT b.* FROM billet b " +
                "JOIN trajet t ON b.trajet_id = t.id " +
                "JOIN ville v_depart ON t.ville_depart_id = v_depart.id " +
                "JOIN ville v_destination ON t.ville_destination_id = v_destination.id " +
                "WHERE v_depart.nom = ? AND v_destination.nom = ? AND b.date_depart = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, villeDepart);
            pstmt.setString(2, villeDestination);
            pstmt.setDate(3, Date.valueOf(LocalDate.parse(dateDepart)));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Récupération des informations du billet
                Billet billet = new Billet(
                        UUID.fromString(rs.getString("id")),
                        TypeTransport.valueOf(rs.getString("type_transport").toUpperCase()),  // Convertir en majuscules
                        rs.getBigDecimal("prix_achat"),
                        rs.getBigDecimal("prix_vente"),
                        rs.getTimestamp("date_vente"),
                        StatutBillet.valueOf(rs.getString("statut_billet").toUpperCase()),
                        UUID.fromString(rs.getString("contrat_id")),
                        rs.getInt("trajet_id"),
                        rs.getDate("date_depart"),
                        rs.getTime("horaire")
                );
                billets.add(billet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billets;
    }

}
