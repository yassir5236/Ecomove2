package service.interfaces;

import model.Contrat;

import java.util.List;
import java.util.UUID;

public interface IContratService {
    void addContrat(Contrat contrat);
    List<Contrat> getAllContrats();
    Contrat getContratById(UUID id);
    void updateContrat(Contrat contrat);
    void deleteContrat(UUID id);
}
