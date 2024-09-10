package service.interfaces;


import model.Partenaire;

import java.util.List;
import java.util.UUID;

public interface IPartenaireService {
    void addPartenaire(Partenaire partenaire);
    List<Partenaire> getAllPartenaires();
    Partenaire getPartenaireById(UUID id);
    void updatePartenaire(Partenaire partenaire);
    boolean deletePartenaire(UUID id);
}
