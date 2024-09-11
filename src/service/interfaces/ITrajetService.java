package service.interfaces;
// ITrajetService.java

import model.Trajet;

import java.util.List;

public interface ITrajetService {
    void addTrajet(Trajet trajet);
    List<Trajet> getAllTrajets();
    Trajet getTrajetById(int id);
    void updateTrajet(Trajet trajet);
    void deleteTrajet(int id);
}
