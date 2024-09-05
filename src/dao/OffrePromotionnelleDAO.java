/*package dao;

import model.OffrePromotionnelle;
import model.StatutContrat; // Assurez-vous que vous utilisez le bon Enum
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OffrePromotionnelleDAO {

    public void ajouterOffre(OffrePromotionnelle offre) {
        String sql = "INSERT INTO offre_promotionnelle (id, date_debut, date_fin, tarif_special, conditions_acord, renouvelable, statut_contrat) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, offre.getId());
            pstmt.setDate(2, offre.getDateDebut());
            pstmt.setDate(3, offre.getDateFin());
            pstmt.setBigDecimal(4, offre.getTarifSpecial());
            pstmt.setString(5, offre.getConditionsAccord());
            pstmt.setBoolean(6, offre.isRenouvelable());
            pstmt.setString(7, offre.getStatutContrat().name());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierOffre(OffrePromotionnelle offre) {
        String sql = "UPDATE offre_promotionnelle SET date_debut = ?, date_fin = ?, tarif_special = ?, conditions_acord = ?, renouvelable = ?, statut_contrat = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, offre.getDateDebut());
            pstmt.setDate(2, offre.getDateFin());
            pstmt.setBigDecimal(3, offre.getTarifSpecial());
            pstmt.setString(4, offre.getConditionsAccord());
            pstmt.setBoolean(5, offre.isRenouvelable());
            pstmt.setString(6, offre.getStatutContrat().name());
            pstmt.setObject(7, offre.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerOffre(UUID id) {
        String sql = "DELETE FROM offre_promotionnelle WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OffrePromotionnelle lireOffre(UUID id) {
        String sql = "SELECT * FROM offre_promotionnelle WHERE id = ?";
        OffrePromotionnelle offre = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                offre = new OffrePromotionnelle(
                        (UUID) rs.getObject("id"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getBigDecimal("tarif_special"),
                        rs.getString("conditions_acord"),
                        rs.getBoolean("renouvelable"),
                        StatutContrat.valueOf(rs.getString("statut_contrat"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offre;
    }

    public List<OffrePromotionnelle> lireToutesLesOffres() {
        String sql = "SELECT * FROM offre_promotionnelle";
        List<OffrePromotionnelle> offres = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OffrePromotionnelle offre = new OffrePromotionnelle(
                        (UUID) rs.getObject("id"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getBigDecimal("tarif_special"),
                        rs.getString("conditions_acord"),
                        rs.getBoolean("renouvelable"),
                        StatutContrat.valueOf(rs.getString("statut_contrat"))
                );
                offres.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offres;
    }
}


*/




package dao;

import model.OffrePromotionnelle;
import model.StatutOffre;
import model.TypeReduction;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OffrePromotionnelleDAO {

    private final Connection connection = DatabaseConnection.getConnection();

    public void addOffrePromotionnelle(OffrePromotionnelle offre) {
        String query = "INSERT INTO offre_promotionnelle (id, nom_offre, description, date_debut, date_fin, type_reduction, " +
                "valeur_reduction, conditions, statut_offre, contrat_id) VALUES (?, ?, ?, ?, ?, ?::type_reduction, ?, ?, ?::statut_offre, ?)";


        try (
             PreparedStatement statement = connection.prepareStatement(query)) {

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

    public List<OffrePromotionnelle> getAllOffresPromotionnelles() {
        List<OffrePromotionnelle> offres = new ArrayList<>();
        String query = "SELECT * FROM offre_promotionnelle";

        try (
             Statement statement = connection.createStatement();
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

    public OffrePromotionnelle getOffreById(UUID id) {
        OffrePromotionnelle offre = null;
        String query = "SELECT * FROM offre_promotionnelle WHERE id = ?";

        try (
             PreparedStatement statement = connection.prepareStatement(query)) {

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

    public void updateOffrePromotionnelle(OffrePromotionnelle offre) {
        String query = "UPDATE offre_promotionnelle SET nom_offre = ?, description = ?, date_debut = ?, date_fin = ?, " +
                "type_reduction = CAST(? AS type_reduction), valeur_reduction = ?, conditions = ?, " +
                "statut_offre = CAST(? AS statut_offre), contrat_id = ? WHERE id = ?";

        try (
             PreparedStatement statement = connection.prepareStatement(query)) {

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

    public void deleteOffrePromotionnelle(UUID id) {
        String query = "DELETE FROM offre_promotionnelle WHERE id = ?";

        try (
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

