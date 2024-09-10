package service.interfaces;


import model.Client;

import java.util.List;
import java.util.UUID;

public interface IClientService {
    void addClient(Client client);
    Client getClientById(UUID id);
    List<Client> getAllClients();
    boolean updateClient(Client client);
    void deleteClient(UUID id);
    Client getClientByNameOrEmail(String nameOrEmail);
}

