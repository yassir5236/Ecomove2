package service;

import dao.ClientDAO;
import model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientService {
    private final ClientDAO clientDAO = new ClientDAO();

    // Ajouter un nouveau client
    public void addClient(Client client) {
        clientDAO.addClient(client);
    }

    // Rechercher un client par son ID
    public Client getClientById(UUID id) {
        return clientDAO.findClientById(id); // Assurez-vous que ClientDAO renvoie Client directement
    }

    // Rechercher tous les clients
    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    // Mettre à jour les informations d'un client
    public boolean updateClient(Client client) {
        return clientDAO.updateClient(client);
    }

    // Supprimer un client par son ID
    public void deleteClient(UUID id) {
        clientDAO.deleteClient(id);
    }

    /* public List<Client> searchClients(String nameOrEmail) {
        return clientDAO.searchClients(nameOrEmail);
    }*/


    // Dans ClientService.java
    public Client getClientByNameOrEmail(String nameOrEmail) {
        List<Client> clients = clientDAO.searchClients(nameOrEmail);
        return clients.isEmpty() ? null : clients.get(0); // Retourne le premier client trouvé ou null
    }
}
