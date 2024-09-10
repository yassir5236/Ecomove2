package service.interfaces;


import model.Billet;

import java.util.List;
import java.util.UUID;

public interface IBilletService {
    void addBillet(Billet billet);
    List<Billet> getAllBillets();
    Billet getBilletById(UUID id);
    void updateBillet(Billet billet);
    void deleteBillet(UUID id);
    List<Billet> searchBillets(String villeDepart, String villeDestination, String dateDepart);
}

