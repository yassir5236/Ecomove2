package dao.interfaces;

import model.Client;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClientDAO {
    boolean addClient(Client client);
    Optional<Client> findClientByEmail(String email);
    List<Client> getAllClients();
    boolean updateClient(Client client);
    boolean deleteClient(UUID clientId);
    Client findClientById(UUID id);
    List<Client> searchClients(String nameOrEmail);
}