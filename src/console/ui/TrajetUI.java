package console.ui;

import console.commands.TrajetCommands;

import java.util.Scanner;

public class TrajetUI {
    private final Scanner scanner = new Scanner(System.in);
    private final TrajetCommands trajetCommands = new TrajetCommands();

    public void start() {
        while (true) {
            System.out.println("=== Menu Gestion des Trajets ===");
            System.out.println("1. Ajouter un trajet");
            System.out.println("2. Afficher tous les trajets");
            System.out.println("3. Mettre à jour un trajet");
            System.out.println("4. Supprimer un trajet");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = -1;
            try {
                choix = scanner.nextInt();
                scanner.nextLine(); // Consommer la nouvelle ligne
            } catch (Exception e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Consommer l'entrée invalide
                continue;
            }

            switch (choix) {
                case 1:
                    trajetCommands.ajouterTrajet();
                    break;
                case 2:
                    trajetCommands.afficherTousLesTrajets();
                    break;
                case 3:
                    trajetCommands.mettreAJourTrajet();
                    break;
                case 4:
                    trajetCommands.supprimerTrajet();
                    break;
                case 5:
                    System.out.println("Fermeture de la gestion des trajets.");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
}
