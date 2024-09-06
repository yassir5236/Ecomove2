package service;

import dao.BilletDAO;
import model.Billet;

import java.util.List;
import java.util.UUID;

public class BilletService {
    private final BilletDAO billetDAO = new BilletDAO();

    public void addBillet(Billet billet) {
        billetDAO.addBillet(billet);
    }

    public List<Billet> getAllBillets() {
        return billetDAO.getAllBillets();
    }

    public Billet getBilletById(UUID id) {
        return billetDAO.getBilletById(id);
    }

    public void updateBillet(Billet billet) {
        billetDAO.updateBillet(billet);
    }

    public void deleteBillet(UUID id) {
        billetDAO.deleteBillet(id);
    }

    // Nouvelle méthode pour rechercher des billets
    public List<Billet> searchBillets(String villeDepart, String villeDestination, String dateDepart) {
        return billetDAO.searchBillets(villeDepart, villeDestination, dateDepart);
    }
}
