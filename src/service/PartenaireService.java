package service;

import dao.PartenaireDAO;
import model.Partenaire;
import service.interfaces.IPartenaireService;

import java.util.List;
import java.util.UUID;

public class PartenaireService implements IPartenaireService {
    private final PartenaireDAO partenaireDAO = new PartenaireDAO();

    @Override
    public void addPartenaire(Partenaire partenaire) {
        partenaireDAO.ajouterPartenaire(partenaire);
    }

    @Override
    public List<Partenaire> getAllPartenaires() {
        return partenaireDAO.lireTousLesPartenaires();
    }

    @Override
    public Partenaire getPartenaireById(UUID id) {
        return partenaireDAO.lirePartenaire(id);
    }

    @Override
    public void updatePartenaire(Partenaire partenaire) {
        partenaireDAO.modifierPartenaire(partenaire);
    }

    @Override
    public boolean deletePartenaire(UUID id) {
        try {
            partenaireDAO.supprimerPartenaire(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
