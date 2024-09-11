package dao.interfaces;


import model.Trajet;

import java.util.List;

public interface ITrajetDAO {
    void addTrajet(Trajet trajet);
    Trajet getTrajetById(int id);
    List<Trajet> getAllTrajets();
    void updateTrajet(Trajet trajet);
    void deleteTrajet(int id);
}
