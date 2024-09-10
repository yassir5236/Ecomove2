package service;

import dao.OffrePromotionnelleDAO;
import model.OffrePromotionnelle;
import service.interfaces.IOffrePromotionnelleService;

import java.util.List;
import java.util.UUID;

public class OffrePromotionnelleService implements IOffrePromotionnelleService {
    private final OffrePromotionnelleDAO offrePromotionnelleDAO = new OffrePromotionnelleDAO();

    @Override
    public void addOffrePromotionnelle(OffrePromotionnelle offrePromotionnelle) {
        offrePromotionnelleDAO.addOffrePromotionnelle(offrePromotionnelle);
    }

    @Override
    public List<OffrePromotionnelle> getAllOffresPromotionnelles() {
        return offrePromotionnelleDAO.getAllOffresPromotionnelles();
    }

    @Override
    public OffrePromotionnelle getOffrePromotionnelleById(UUID id) {
        return offrePromotionnelleDAO.getOffreById(id);
    }

    @Override
    public void updateOffrePromotionnelle(OffrePromotionnelle offrePromotionnelle) {
        offrePromotionnelleDAO.updateOffrePromotionnelle(offrePromotionnelle);
    }

    @Override
    public void deleteOffrePromotionnelle(UUID id) {
        offrePromotionnelleDAO.deleteOffrePromotionnelle(id);
    }
}
