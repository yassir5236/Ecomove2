package console.commands;


import service.TrajetService;
import model.Trajet;

import java.util.List;
import java.util.Scanner;

public class TrajetCommands {

    private final TrajetService trajetService;

    public TrajetCommands() {
        this.trajetService = new TrajetService();
    }

    // Ajouter un trajet

    public void ajouterTrajet() {
        System.out.println("=== Ajouter un trajet ===");
        Scanner scanner = new Scanner(System.in); // Ne pas utiliser try-with-resources ici

        int villeDepartId = 0;
        while (villeDepartId <= 0) {
            System.out.print("ID de la ville de départ : ");
            try {
                villeDepartId = Integer.parseInt(scanner.nextLine());
                if (villeDepartId <= 0) {
                    System.out.println("L'ID de la ville de départ doit être un entier positif.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ID de la ville de départ invalide. Veuillez entrer un entier positif.");
            }
        }

        int villeDestinationId = 0;
        while (villeDestinationId <= 0) {
            System.out.print("ID de la ville de destination : ");
            try {
                villeDestinationId = Integer.parseInt(scanner.nextLine());
                if (villeDestinationId <= 0) {
                    System.out.println("L'ID de la ville de destination doit être un entier positif.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ID de la ville de destination invalide. Veuillez entrer un entier positif.");
            }
        }

        double duree = 0;
        while (duree <= 0) {
            System.out.print("Durée (en heures) : ");
            try {
                duree = Double.parseDouble(scanner.nextLine());
                if (duree <= 0) {
                    System.out.println("La durée doit être un nombre positif.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Durée invalide. Veuillez entrer un nombre positif.");
            }
        }

        Trajet trajet = new Trajet(villeDepartId, villeDestinationId, duree);
        trajetService.addTrajet(trajet);

        System.out.println("Trajet ajouté avec succès!");
    }



    // Afficher tous les trajets
    public void afficherTousLesTrajets() {
        List<Trajet> trajets = trajetService.getAllTrajets();
        System.out.println("=== Liste des trajets ===");
        for (Trajet trajet : trajets) {
            System.out.println(trajet);
        }
    }

    // Mettre à jour un trajet
    public void mettreAJourTrajet() {
        System.out.println("=== Mettre à jour un trajet ===");
        Scanner scanner = new Scanner(System.in);

        int id = 0;
        while (id <= 0) {
            try {
                System.out.print("ID du trajet à mettre à jour : ");
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID invalide. Veuillez entrer un entier positif.");
            }
        }

        Trajet trajet = trajetService.getTrajetById(id);
        if (trajet == null) {
            System.out.println("Trajet non trouvé avec cet ID.");
            return;
        }

        int villeDepartId = 0;
        while (villeDepartId <= 0) {
            try {
                System.out.print("Nouveau ID de la ville de départ : ");
                villeDepartId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID de la ville de départ invalide. Veuillez entrer un entier positif.");
            }
        }

        int villeDestinationId = 0;
        while (villeDestinationId <= 0) {
            try {
                System.out.print("Nouveau ID de la ville de destination : ");
                villeDestinationId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID de la ville de destination invalide. Veuillez entrer un entier positif.");
            }
        }

        double duree = 0;
        while (duree <= 0) {
            try {
                System.out.print("Nouvelle durée (en heures) : ");
                duree = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Durée invalide. Veuillez entrer un nombre positif.");
            }
        }

        trajet.setVilleDepartId(villeDepartId);
        trajet.setVilleDestinationId(villeDestinationId);
        trajet.setDuree(duree);

        trajetService.updateTrajet(trajet);

        System.out.println("Trajet mis à jour avec succès!");
    }

    // Supprimer un trajet
    public void supprimerTrajet() {
        System.out.println("=== Supprimer un trajet ===");
        Scanner scanner = new Scanner(System.in);

        int id = 0;
        while (id <= 0) {
            try {
                System.out.print("ID du trajet à supprimer : ");
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID invalide. Veuillez entrer un entier positif.");
            }
        }

        trajetService.deleteTrajet(id);

        System.out.println("Trajet supprimé avec succès!");
    }
}
