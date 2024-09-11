package dao;

import dao.interfaces.IOffrePromotionnelleDAO;
import model.OffrePromotionnelle;
import model.StatutOffre;
import model.TypeReduction;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OffrePromotionnelleDAO implements IOffrePromotionnelleDAO {
    private final Connection connection ;

    public OffrePromotionnelleDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    @Override
    public void addOffrePromotionnelle(OffrePromotionnelle offre) {
        String query = "INSERT INTO offre_promotionnelle (id, nom_offre, description, date_debut, date_fin, type_reduction, " +
                "valeur_reduction, conditions, statut_offre, contrat_id) VALUES (?, ?, ?, ?, ?, ?::type_reduction, ?, ?, ?::statut_offre, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, offre.getId());
            statement.setString(2, offre.getNomOffre());
            statement.setString(3, offre.getDescription());
            statement.setDate(4, offre.getDateDebut());
            statement.setDate(5, offre.getDateFin());
            statement.setString(6, offre.getTypeReduction().name().toLowerCase());
            statement.setBigDecimal(7, offre.getValeurReduction());
            statement.setString(8, offre.getConditions());
            statement.setString(9, offre.getStatutOffre().name().toLowerCase());
            statement.setObject(10, offre.getContratId()); // Utilisation de contratId ici
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OffrePromotionnelle> getAllOffresPromotionnelles() {
        List<OffrePromotionnelle> offres = new ArrayList<>();
        String query = "SELECT * FROM offre_promotionnelle";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                OffrePromotionnelle offre = new OffrePromotionnelle(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("nom_offre"),
                        resultSet.getString("description"),
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        TypeReduction.valueOf(resultSet.getString("type_reduction").toUpperCase()),
                        resultSet.getBigDecimal("valeur_reduction"),
                        resultSet.getString("conditions"),
                        StatutOffre.valueOf(resultSet.getString("statut_offre").toUpperCase()),
                        UUID.fromString(resultSet.getString("contrat_id")) // Utilisation de contratId ici
                );
                offres.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offres;
    }

    @Override
    public OffrePromotionnelle getOffreById(UUID id) {
        OffrePromotionnelle offre = null;
        String query = "SELECT * FROM offre_promotionnelle WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                offre = new OffrePromotionnelle(
                        id,
                        resultSet.getString("nom_offre"),
                        resultSet.getString("description"),
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        TypeReduction.valueOf(resultSet.getString("type_reduction").toUpperCase()),
                        resultSet.getBigDecimal("valeur_reduction"),
                        resultSet.getString("conditions"),
                        StatutOffre.valueOf(resultSet.getString("statut_offre").toUpperCase()),
                        UUID.fromString(resultSet.getString("contrat_id")) // Utilisation de contratId ici
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offre;
    }

    @Override
    public void updateOffrePromotionnelle(OffrePromotionnelle offre) {
        String query = "UPDATE offre_promotionnelle SET nom_offre = ?, description = ?, date_debut = ?, date_fin = ?, " +
                "type_reduction = CAST(? AS type_reduction), valeur_reduction = ?, conditions = ?, " +
                "statut_offre = CAST(? AS statut_offre), contrat_id = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, offre.getNomOffre());
            statement.setString(2, offre.getDescription());
            statement.setDate(3, offre.getDateDebut());
            statement.setDate(4, offre.getDateFin());
            statement.setString(5, offre.getTypeReduction().name().toLowerCase());
            statement.setBigDecimal(6, offre.getValeurReduction());
            statement.setString(7, offre.getConditions());
            statement.setString(8, offre.getStatutOffre().name().toLowerCase());
            statement.setObject(9, offre.getContratId()); // Utilisation de contratId ici
            statement.setObject(10, offre.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOffrePromotionnelle(UUID id) {
        String query = "DELETE FROM offre_promotionnelle WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
