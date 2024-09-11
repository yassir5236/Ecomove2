package dao;

import dao.interfaces.IPartenaireDAO;
import model.Partenaire;
import model.TypeTransport;
import model.StatutPartenaire;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartenaireDAO implements IPartenaireDAO {
    private final Connection conn ;

    public PartenaireDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    @Override
    public void ajouterPartenaire(Partenaire partenaire) {
        String sql = "INSERT INTO partenaire (id, nom_compagnie, contact_commercial, type_transport, zone_geographique, conditions_speciales, statut_partenaire, date_creation) " +
                "VALUES (?, ?, ?, ?::type_transport, ?, ?, ?::statut_partenaire, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, partenaire.getId());
            pstmt.setString(2, partenaire.getNomCompagnie());
            pstmt.setString(3, partenaire.getContactCommercial());
            pstmt.setString(4, partenaire.getTypeTransport().name().toLowerCase());
            pstmt.setString(5, partenaire.getZoneGeographique());
            pstmt.setString(6, partenaire.getConditionsSpeciales());
            pstmt.setString(7, partenaire.getStatutPartenaire().name().toLowerCase());

            // Convertir java.util.Date en java.sql.Date
            java.util.Date utilDate = partenaire.getDateCreation();
            Date sqlDate = new Date(utilDate.getTime());
            pstmt.setDate(8, sqlDate);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifierPartenaire(Partenaire partenaire) {
        String sql = "UPDATE partenaire SET nom_compagnie = ?, contact_commercial = ?, type_transport = CAST(? AS type_transport), " +
                "zone_geographique = ?, conditions_speciales = ?, statut_partenaire = CAST(? AS statut_partenaire), date_creation = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, partenaire.getNomCompagnie());
            pstmt.setString(2, partenaire.getContactCommercial());
            pstmt.setString(3, partenaire.getTypeTransport().name().toLowerCase());
            pstmt.setString(4, partenaire.getZoneGeographique());
            pstmt.setString(5, partenaire.getConditionsSpeciales());
            pstmt.setString(6, partenaire.getStatutPartenaire().name().toLowerCase());
            pstmt.setDate(7, new Date(partenaire.getDateCreation().getTime()));
            pstmt.setObject(8, partenaire.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerPartenaire(UUID id) {
        String sql = "DELETE FROM partenaire WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression du partenaire.", e);
        }
    }

    @Override
    public Partenaire lirePartenaire(UUID id) {
        String sql = "SELECT * FROM partenaire WHERE id = ?";
        Partenaire partenaire = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Date sqlDate = rs.getDate("date_creation");
                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());

                partenaire = new Partenaire(
                        (UUID) rs.getObject("id"),
                        rs.getString("nom_compagnie"),
                        rs.getString("contact_commercial"),
                        TypeTransport.valueOf(rs.getString("type_transport").toUpperCase()),
                        rs.getString("zone_geographique"),
                        rs.getString("conditions_speciales"),
                        StatutPartenaire.valueOf(rs.getString("statut_partenaire").toUpperCase()),
                        utilDate
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partenaire;
    }

    @Override
    public List<Partenaire> lireTousLesPartenaires() {
        List<Partenaire> partenaires = new ArrayList<>();
        String sql = "SELECT * FROM partenaire";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Date sqlDate = rs.getDate("date_creation");
                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());

                Partenaire partenaire = new Partenaire(
                        (UUID) rs.getObject("id"),
                        rs.getString("nom_compagnie"),
                        rs.getString("contact_commercial"),
                        TypeTransport.valueOf(rs.getString("type_transport").toUpperCase()),
                        rs.getString("zone_geographique"),
                        rs.getString("conditions_speciales"),
                        StatutPartenaire.valueOf(rs.getString("statut_partenaire").toUpperCase()),
                        utilDate
                );
                partenaires.add(partenaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partenaires;
    }
}
