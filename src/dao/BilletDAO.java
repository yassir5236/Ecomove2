

package dao;

import model.Billet;
import model.Contrat;
import model.TypeTransport;
import model.StatutBillet;
import util.DatabaseConnection;
import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BilletDAO {
    private final Connection connection = DatabaseConnection.getConnection();




    public void addBillet(Billet billet) {
        String query = "INSERT INTO billet (id, contrat_id, type_transport, prix_achat, prix_vente, date_vente, statut_billet) " +
                "VALUES (?, ?, ?::type_transport, ?, ?, ?, ?::statut_billet)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, billet.getId());
            statement.setObject(2, billet.getContratId());  // Insertion du contrat_id
            statement.setString(3, billet.getTypeTransport().name().toLowerCase());
            statement.setBigDecimal(4, billet.getPrixAchat());
            statement.setBigDecimal(5, billet.getPrixVente());
            statement.setTimestamp(6, new Timestamp(billet.getDateVente().getTime()));  // Conversion correcte
            statement.setString(7, billet.getStatutBillet().name().toLowerCase());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Billet> getAllBillets() {
        List<Billet> billets = new ArrayList<>();
        String query = "SELECT * FROM billet";

        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                UUID id = resultSet.getString("id") != null ? UUID.fromString(resultSet.getString("id")) : null;
                UUID contratId = resultSet.getString("contrat_id") != null ? UUID.fromString(resultSet.getString("contrat_id")) : null;

                TypeTransport typeTransport = resultSet.getString("type_transport") != null ?
                        TypeTransport.valueOf(resultSet.getString("type_transport").toUpperCase()) : null;

                BigDecimal prixAchat = resultSet.getBigDecimal("prix_achat");
                BigDecimal prixVente = resultSet.getBigDecimal("prix_vente");

                Date dateVente = resultSet.getTimestamp("date_vente") != null ?
                        new Date(resultSet.getTimestamp("date_vente").getTime()) : null;

                StatutBillet statutBillet = resultSet.getString("statut_billet") != null ?
                        StatutBillet.valueOf(resultSet.getString("statut_billet").toUpperCase()) : null;

                Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, dateVente, statutBillet);
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

        try (
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                billet = new Billet(
                        id,
                        UUID.fromString(resultSet.getString("partenaire_id")),  // Extraction du partenaire_id
                        TypeTransport.valueOf(resultSet.getString("type_transport")),
                        resultSet.getBigDecimal("prix_achat"),
                        resultSet.getBigDecimal("prix_vente"),
                        resultSet.getTimestamp("date_vente"),  // Convertir en Date si nécessaire
                        StatutBillet.valueOf(resultSet.getString("statut_billet").toUpperCase())
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billet;
    }

    public void updateBillet(Billet billet) {
        String query = "UPDATE billet SET contrat_id = ?, type_transport = CAST(? AS type_transport), prix_achat = ?, prix_vente = ?, date_vente = ?, statut_billet = CAST(? AS statut_billet) WHERE id = ?";

        try (
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, billet.getContratId());  // Mise à jour du contratId
            statement.setString(2, billet.getTypeTransport().name().toLowerCase());
            statement.setBigDecimal(3, billet.getPrixAchat());
            statement.setBigDecimal(4, billet.getPrixVente());
            statement.setTimestamp(5, new Timestamp(billet.getDateVente().getTime()));  // Conversion correcte
            statement.setString(6, billet.getStatutBillet().name().toLowerCase());
            statement.setObject(7, billet.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBillet(UUID id) {
        String query = "DELETE FROM billet WHERE id = ?";

        try (
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
