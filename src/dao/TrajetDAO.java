package dao;



import dao.interfaces.ITrajetDAO;
import model.Trajet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrajetDAO implements ITrajetDAO {
    private final Connection connection;

    public TrajetDAO() {
        // Assurez-vous de remplacer les paramètres par ceux de votre base de données
        String url = "jdbc:postgresql://localhost:5432/your_database";
        String user = "your_username";
        String password = "your_password";
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    @Override
    public void addTrajet(Trajet trajet) {
        String query = "INSERT INTO trajet (id, ville_depart_id, ville_destination_id, duree) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, trajet.getId());
            stmt.setInt(2, trajet.getVilleDepartId());
            stmt.setInt(3, trajet.getVilleDestinationId());
            stmt.setDouble(4, trajet.getDuree());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du trajet", e);
        }
    }

    @Override
    public Trajet getTrajetById(int id) {
        String query = "SELECT * FROM trajet WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Trajet(
                        rs.getInt("id"),
                        rs.getInt("ville_depart_id"),
                        rs.getInt("ville_destination_id"),
                        rs.getDouble("duree")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du trajet", e);
        }
    }

    @Override
    public List<Trajet> getAllTrajets() {
        String query = "SELECT * FROM trajet";
        List<Trajet> trajets = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                trajets.add(new Trajet(
                        rs.getInt("id"),
                        rs.getInt("ville_depart_id"),
                        rs.getInt("ville_destination_id"),
                        rs.getDouble("duree")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de tous les trajets", e);
        }
        return trajets;
    }

    @Override
    public void updateTrajet(Trajet trajet) {
        String query = "UPDATE trajet SET ville_depart_id = ?, ville_destination_id = ?, duree = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, trajet.getVilleDepartId());
            stmt.setInt(2, trajet.getVilleDestinationId());
            stmt.setDouble(3, trajet.getDuree());
            stmt.setInt(4, trajet.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du trajet", e);
        }
    }

    @Override
    public void deleteTrajet(int id) {
        String query = "DELETE FROM trajet WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du trajet", e);
        }
    }
}
