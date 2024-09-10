package service.interfaces;


import model.OffrePromotionnelle;

import java.util.List;
import java.util.UUID;

public interface IOffrePromotionnelleService {
    void addOffrePromotionnelle(OffrePromotionnelle offrePromotionnelle);
    List<OffrePromotionnelle> getAllOffresPromotionnelles();
    OffrePromotionnelle getOffrePromotionnelleById(UUID id);
    void updateOffrePromotionnelle(OffrePromotionnelle offrePromotionnelle);
    void deleteOffrePromotionnelle(UUID id);
}
