package dao.interfaces;

import model.Billet;
import java.util.List;
import java.util.UUID;

public interface IBilletDAO {

    // Méthode pour ajouter un billet
    void addBillet(Billet billet);

    // Méthode pour récupérer tous les billets
    List<Billet> getAllBillets();

    // Méthode pour récupérer un billet par son ID
    Billet getBilletById(UUID id);

    // Méthode pour mettre à jour un billet existant
    void updateBillet(Billet billet);

    // Méthode pour supprimer un billet par son ID
    void deleteBillet(UUID id);

    // Méthode pour rechercher des billets en fonction de la ville de départ, ville de destination, et la date de départ
    List<Billet> searchBillets(String villeDepart, String villeDestination, String dateDepart);
}
