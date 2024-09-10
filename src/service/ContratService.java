package service;

import dao.ContratDAO;
import model.Contrat;
import service.interfaces.IContratService;

import java.util.List;
import java.util.UUID;

public class ContratService implements IContratService {
    private final ContratDAO contratDAO = new ContratDAO();

    @Override
    public void addContrat(Contrat contrat) {
        contratDAO.addContrat(contrat);
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratDAO.getAllContrats();
    }

    @Override
    public Contrat getContratById(UUID id) {
        return contratDAO.getContratById(id);
    }

    @Override
    public void updateContrat(Contrat contrat) {
        contratDAO.updateContrat(contrat);
    }

    @Override
    public void deleteContrat(UUID id) {
        contratDAO.deleteContrat(id);
    }
}
