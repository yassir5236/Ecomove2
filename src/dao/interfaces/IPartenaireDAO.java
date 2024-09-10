package dao.interfaces;

import model.Partenaire;

import java.util.List;
import java.util.UUID;

public interface IPartenaireDAO {

    // Ajouter un nouveau partenaire
    void ajouterPartenaire(Partenaire partenaire);

    // Modifier les informations d'un partenaire existant
    void modifierPartenaire(Partenaire partenaire);

    // Supprimer un partenaire par son identifiant
    void supprimerPartenaire(UUID id);

    // Lire les informations d'un partenaire par son identifiant
    Partenaire lirePartenaire(UUID id);

    // Lire tous les partenaires
    List<Partenaire> lireTousLesPartenaires();
}
