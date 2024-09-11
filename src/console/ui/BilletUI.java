package console.ui;

import console.commands.BilletCommands;

import java.util.Scanner;

public class BilletUI {

    private final BilletCommands billetCommands;

    public BilletUI() {
        this.billetCommands = new BilletCommands();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("=== Menu Gestion des Billets ===");
            System.out.println("1. Ajouter un billet");
            System.out.println("2. Afficher tous les billets");
            System.out.println("3. Mettre à jour un billet");
            System.out.println("4. Supprimer un billet");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next(); // Consomme l'entrée incorrecte
            }
            choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne après l'entrée

            switch (choix) {
                case 1:
                    billetCommands.ajouterBillet();
                    break;
                case 2:
                    billetCommands.afficherTousLesBillets();
                    break;
                case 3:
                    billetCommands.mettreAJourBillet();
                    break;
                case 4:
                    billetCommands.supprimerBillet();
                    break;
                case 5:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Option invalide. Essayez encore.");
                    break;
            }
        } while (choix != 5);

        scanner.close();
    }
}
