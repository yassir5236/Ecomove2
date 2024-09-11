// TrajetService.java
package service;

import dao.TrajetDAO;
import model.Trajet;
import service.interfaces.ITrajetService;

import java.util.List;

public class TrajetService implements ITrajetService {
    private final TrajetDAO trajetDAO;

    public TrajetService() {
        this.trajetDAO = new TrajetDAO();
    }

    @Override
    public void addTrajet(Trajet trajet) {
        trajetDAO.addTrajet(trajet);
    }

    @Override
    public List<Trajet> getAllTrajets() {
        return trajetDAO.getAllTrajets();
    }

    @Override
    public Trajet getTrajetById(int id) {
        return trajetDAO.getTrajetById(id);
    }

    @Override
    public void updateTrajet(Trajet trajet) {
        trajetDAO.updateTrajet(trajet);
    }

    @Override
    public void deleteTrajet(int id) {
        trajetDAO.deleteTrajet(id);
    }
}
