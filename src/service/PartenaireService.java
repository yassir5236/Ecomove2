package service;

import dao.PartenaireDAO;
import model.Partenaire;

import java.util.List;
import java.util.UUID;

public class PartenaireService {
    private final PartenaireDAO partenaireDAO = new PartenaireDAO();

    public void addPartenaire(Partenaire partenaire) {
        partenaireDAO.ajouterPartenaire(partenaire);
    }

    public List<Partenaire> getAllPartenaires() {
        return partenaireDAO.lireTousLesPartenaires();
    }

    public Partenaire getPartenaireById(UUID id) {
        return partenaireDAO.lirePartenaire(id);
    }

    public void updatePartenaire(Partenaire partenaire) {
        partenaireDAO.modifierPartenaire(partenaire);
    }

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
