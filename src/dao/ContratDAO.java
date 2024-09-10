package dao;

import dao.interfaces.IContratDAO;
import model.Contrat;
import model.StatutContrat;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContratDAO implements IContratDAO {
    private final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void addContrat(Contrat contrat) {
        String query = "INSERT INTO contrat (id, date_debut, date_fin, tarif_special, conditions_accord, renouvelable, statut_contrat, partenaire_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?::statut_contrat, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, contrat.getId());
            statement.setDate(2, new Date(contrat.getDateDebut().getTime()));

            if (contrat.getDateFin() != null) {
                statement.setDate(3, new Date(contrat.getDateFin().getTime()));
            } else {
                statement.setNull(3, Types.DATE); // Insère NULL dans la base de données
            }

            statement.setBigDecimal(4, contrat.getTarifSpecial());
            statement.setString(5, contrat.getConditionsAccord());
            statement.setBoolean(6, contrat.isRenouvelable());
            statement.setString(7, contrat.getStatutContrat().name().toLowerCase());
            statement.setObject(8, contrat.getPartenaireId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contrat> getAllContrats() {
        List<Contrat> contrats = new ArrayList<>();
        String query = "SELECT * FROM contrat";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Contrat contrat = new Contrat(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        resultSet.getBigDecimal("tarif_special"),
                        resultSet.getString("conditions_accord"),
                        resultSet.getBoolean("renouvelable"),
                        StatutContrat.valueOf(resultSet.getString("statut_contrat").toUpperCase())
                );

                String partenaireIdStr = resultSet.getString("partenaire_id");
                if (partenaireIdStr != null) {
                    UUID partenaireId = UUID.fromString(partenaireIdStr);
                    contrat.setPartenaireId(partenaireId);
                }

                contrats.add(contrat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contrats;
    }

    @Override
    public Contrat getContratById(UUID id) {
        Contrat contrat = null;
        String query = "SELECT * FROM contrat WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                contrat = new Contrat(
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        resultSet.getBigDecimal("tarif_special"),
                        resultSet.getString("conditions_accord"),
                        resultSet.getBoolean("renouvelable"),
                        StatutContrat.valueOf(resultSet.getString("statut_contrat").toUpperCase())
                );
                contrat.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contrat;
    }

    @Override
    public void updateContrat(Contrat contrat) {
        String query = "UPDATE contrat SET date_debut = ?, date_fin = ?, tarif_special = ?, conditions_accord = ?, renouvelable = ?, " +
                "statut_contrat = CAST(? AS statut_contrat) WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, new Date(contrat.getDateDebut().getTime()));
            if (contrat.getDateFin() != null) {
                statement.setDate(2, new Date(contrat.getDateFin().getTime()));
            } else {
                statement.setNull(2, Types.DATE);
            }
            statement.setBigDecimal(3, contrat.getTarifSpecial());
            statement.setString(4, contrat.getConditionsAccord());
            statement.setBoolean(5, contrat.isRenouvelable());
            statement.setString(6, contrat.getStatutContrat().name().toLowerCase());
            statement.setObject(7, contrat.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteContrat(UUID id) {
        String query = "DELETE FROM contrat WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
