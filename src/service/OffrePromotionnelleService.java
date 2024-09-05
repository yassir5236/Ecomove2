package service;

import dao.OffrePromotionnelleDAO;
import model.OffrePromotionnelle;

import java.util.List;
import java.util.UUID;

public class OffrePromotionnelleService {
    private final OffrePromotionnelleDAO offrePromotionnelleDAO = new OffrePromotionnelleDAO();

    public void addOffrePromotionnelle(OffrePromotionnelle offrePromotionnelle) {
        offrePromotionnelleDAO.addOffrePromotionnelle(offrePromotionnelle);

    }

    public List<OffrePromotionnelle> getAllOffresPromotionnelles() {
        return offrePromotionnelleDAO.getAllOffresPromotionnelles();

    }

    public OffrePromotionnelle getOffrePromotionnelleById(UUID id) {
        return offrePromotionnelleDAO.getOffreById(id);
    }

    public void updateOffrePromotionnelle(OffrePromotionnelle offrePromotionnelle) {
        offrePromotionnelleDAO.updateOffrePromotionnelle(offrePromotionnelle);
    }

    public void deleteOffrePromotionnelle(UUID id) {
        offrePromotionnelleDAO.deleteOffrePromotionnelle(id);
    }
}
