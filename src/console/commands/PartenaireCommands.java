package console.commands;


import dao.PartenaireDAO;
import model.TypeTransport;
import model.StatutPartenaire;

import model.Partenaire;
import service.PartenaireService;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;



public class PartenaireCommands {
    private final PartenaireService partenaireService = new PartenaireService();



    public void addPartenaire(Scanner scanner) {
        System.out.println("Entrez le nom de la compagnie :");
        String nomCompagnie = scanner.nextLine();

        System.out.println("Entrez le contact commercial :");
        String contactCommercial = scanner.nextLine();

        TypeTransport typeTransport = null;
        while (typeTransport == null) {
            System.out.println("Entrez le type de transport (avion, train, bus) :");
            String typeTransportString = scanner.nextLine();
            try {
                typeTransport = TypeTransport.valueOf(typeTransportString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Type de transport incorrect. Veuillez réessayer.");
            }
        }

        System.out.println("Entrez la zone géographique :");
        String zoneGeographique = scanner.nextLine();

        System.out.println("Entrez les conditions spéciales :");
        String conditionsSpeciales = scanner.nextLine();

        StatutPartenaire statutPartenaire = null;
        while (statutPartenaire == null) {
            System.out.println("Entrez le statut du partenaire (actif, inactif, suspendu) :");
            String statutPartenaireString = scanner.nextLine();
            try {
                statutPartenaire = StatutPartenaire.valueOf(statutPartenaireString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Statut du partenaire incorrect. Veuillez réessayer.");
            }
        }

        Partenaire partenaire = new Partenaire(UUID.randomUUID(), nomCompagnie, contactCommercial, typeTransport, zoneGeographique, conditionsSpeciales, statutPartenaire, new java.util.Date());
        partenaireService.addPartenaire(partenaire);

        System.out.println("Partenaire ajouté avec succès !");
    }



    public void displayAllPartenaires() {
        List<Partenaire> partenaires = partenaireService.getAllPartenaires();
        if (partenaires.isEmpty()) {
            System.out.println("Aucun partenaire trouvé.");
            return;
        }
        for (Partenaire partenaire : partenaires) {
            System.out.println(partenaire);
        }
    }



    public void updatePartenaire(Scanner scanner) {
        System.out.println("Entrez l'ID du partenaire à mettre à jour :");
        UUID id = null;
        while (id == null) {
            try {
                id = UUID.fromString(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("ID invalide. Veuillez entrer un UUID valide.");
            }
        }

        Partenaire partenaire = partenaireService.getPartenaireById(id);
        if (partenaire == null) {
            System.out.println("Partenaire non trouvé.");
            return;
        }

        System.out.println("Entrez le nouveau nom de la compagnie (laisser vide pour ne pas modifier) :");
        String nomCompagnie = scanner.nextLine();
        if (!nomCompagnie.isEmpty()) {
            partenaire.setNomCompagnie(nomCompagnie);
        }

        System.out.println("Entrez le nouveau contact commercial (laisser vide pour ne pas modifier) :");
        String contactCommercial = scanner.nextLine();
        if (!contactCommercial.isEmpty()) {
            partenaire.setContactCommercial(contactCommercial);
        }

        System.out.println("Entrez le nouveau type de transport (laisser vide pour ne pas modifier) :");
        String typeTransportString = scanner.nextLine();
        if (!typeTransportString.isEmpty()) {
            try {
                partenaire.setTypeTransport(TypeTransport.valueOf(typeTransportString.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Type de transport incorrect. Valeur non modifiée.");
            }
        }

        System.out.println("Entrez la nouvelle zone géographique (laisser vide pour ne pas modifier) :");
        String zoneGeographique = scanner.nextLine();
        if (!zoneGeographique.isEmpty()) {
            partenaire.setZoneGeographique(zoneGeographique);
        }

        System.out.println("Entrez les nouvelles conditions spéciales (laisser vide pour ne pas modifier) :");
        String conditionsSpeciales = scanner.nextLine();
        if (!conditionsSpeciales.isEmpty()) {
            partenaire.setConditionsSpeciales(conditionsSpeciales);
        }

        System.out.println("Entrez le nouveau statut du partenaire (laisser vide pour ne pas modifier) :");
        String statutPartenaireString = scanner.nextLine();
        if (!statutPartenaireString.isEmpty()) {
            try {
                partenaire.setStatutPartenaire(StatutPartenaire.valueOf(statutPartenaireString.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Statut du partenaire incorrect. Valeur non modifiée.");
            }
        }

        partenaireService.updatePartenaire(partenaire);

        System.out.println("Partenaire mis à jour avec succès !");
    }


    public void deletePartenaire(Scanner scanner) {
        System.out.println("Entrez l'ID du partenaire à supprimer :");
        UUID id = UUID.fromString(scanner.nextLine());

        boolean success = partenaireService.deletePartenaire(id);
        if (success) {
            System.out.println("Partenaire supprimé avec succès !");
        } else {
            System.out.println("Erreur lors de la suppression du partenaire.");
        }
    }


}
