package service;

import dao.ClientDAO;
import model.Client;
import service.interfaces.IClientService;

import java.util.List;
import java.util.UUID;

public class ClientService implements IClientService {
    private final ClientDAO clientDAO = new ClientDAO();

    @Override
    public void addClient(Client client) {
        clientDAO.addClient(client);
    }

    @Override
    public Client getClientById(UUID id) {
        return clientDAO.findClientById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    @Override
    public boolean updateClient(Client client) {
        return clientDAO.updateClient(client);
    }

    @Override
    public void deleteClient(UUID id) {
        clientDAO.deleteClient(id);
    }

    @Override
    public Client getClientByNameOrEmail(String nameOrEmail) {
        List<Client> clients = clientDAO.searchClients(nameOrEmail);
        return clients.isEmpty() ? null : clients.get(0); // Retourne le premier client trouvé ou null
    }
}
