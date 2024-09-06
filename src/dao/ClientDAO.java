package dao;


import model.Client;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientDAO {
    private final Connection connection = DatabaseConnection.getConnection();


    // Méthode pour ajouter un client à la base de données
    public boolean addClient(Client client) {
        String sql = "INSERT INTO clients (id, nom, prenom, email, telephone) VALUES (?, ?, ?, ?, ?)";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, client.getId());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3, client.getPrenom());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getTelephone());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du client : " + e.getMessage());
        }
        return false;
    }

    // Méthode pour rechercher un client par email
    public Optional<Client> findClientByEmail(String email) {
        String sql = "SELECT * FROM clients WHERE email = ?";
        Client client = null;

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                client = new Client(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du client : " + e.getMessage());
        }

        return Optional.ofNullable(client);
    }

    // Méthode pour récupérer tous les clients
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();

        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Client client = new Client(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone")
                );
                clients.add(client);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des clients : " + e.getMessage());
        }

        return clients;
    }

    // Méthode pour mettre à jour un client
    public boolean updateClient(Client client) {
        String sql = "UPDATE clients SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE id = ?";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getTelephone());
            preparedStatement.setObject(5, client.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du client : " + e.getMessage());
        }

        return false;
    }

    // Méthode pour supprimer un client
    public boolean deleteClient(UUID clientId) {
        String sql = "DELETE FROM clients WHERE id = ?";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, clientId);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du client : " + e.getMessage());
        }

        return false;
    }



    public Client findClientById(UUID id) {
        Client client = null;
        String query = "SELECT * FROM public.client WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                client = new Client(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }



    public List<Client> searchClients(String nameOrEmail) {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM public.client WHERE nom ILIKE ? OR email ILIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + nameOrEmail + "%");
            statement.setString(2, "%" + nameOrEmail + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = new Client(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

}