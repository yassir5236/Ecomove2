package service;


import dao.ContratDAO;
import model.Contrat;

import java.util.List;
import java.util.UUID;





public class ContratService {
    private final ContratDAO contratDAO = new ContratDAO();

    public void addContrat(Contrat contrat) {
        contratDAO.addContrat(contrat);
    }

    public List<Contrat> getAllContrats() {
        return contratDAO.getAllContrats();
    }

    public Contrat getContratById(UUID id) {
        return contratDAO.getContratById(id);
    }

    public void updateContrat(Contrat contrat) {
        contratDAO.updateContrat(contrat);
    }

    public void deleteContrat(UUID id) {
        contratDAO.deleteContrat(id);
    }
}
