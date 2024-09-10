package dao.interfaces;


import model.Contrat;
import java.util.List;
import java.util.UUID;

public interface IContratDAO {
    // Ajouter un nouveau contrat
    void addContrat(Contrat contrat);

    // Récupérer un contrat par son ID
    Contrat getContratById(UUID id);

    // Récupérer tous les contrats
    List<Contrat> getAllContrats();

    // Mettre à jour un contrat existant
    void updateContrat(Contrat contrat);

    // Supprimer un contrat par son ID
    void deleteContrat(UUID id);
}
