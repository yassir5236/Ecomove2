package dao.interfaces;



import model.OffrePromotionnelle;
import java.util.List;
import java.util.UUID;

public interface IOffrePromotionnelleDAO {
    void addOffrePromotionnelle(OffrePromotionnelle offre);
    List<OffrePromotionnelle> getAllOffresPromotionnelles();
    OffrePromotionnelle getOffreById(UUID id);
    void updateOffrePromotionnelle(OffrePromotionnelle offre);
    void deleteOffrePromotionnelle(UUID id);
}