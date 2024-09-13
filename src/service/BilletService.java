package service;

import dao.BilletDAO;
import model.Billet;
import service.interfaces.IBilletService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BilletService implements IBilletService {
    private final BilletDAO billetDAO;
    public BilletService() {
        this.billetDAO = new BilletDAO();
    }

    @Override
    public void addBillet(Billet billet) {
        billetDAO.addBillet(billet);
    }

    @Override
    public List<Billet> getAllBillets() {
        return billetDAO.getAllBillets();
    }

    @Override
    public Billet getBilletById(UUID id) {
        return billetDAO.getBilletById(id);
    }

    @Override
    public void updateBillet(Billet billet) {
        billetDAO.updateBillet(billet);
    }

    @Override
    public void deleteBillet(UUID id) {
        billetDAO.deleteBillet(id);
    }


    @Override
    public List<Billet> searchBillets(String villeDepart, String villeDestination, LocalDate dateDepart) {
        return billetDAO.searchBillets(villeDepart, villeDestination, dateDepart);
    }
}
