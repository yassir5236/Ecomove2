package console.ui;


import console.commands.ContratCommands;

import java.util.Scanner;



public class ContratUI {
    private final Scanner scanner = new Scanner(System.in);
    private final ContratCommands contratCommands = new ContratCommands();

    public void start(Scanner scanner) {
        while (true) {
            System.out.println("=== Menu Gestion des Contrats ===");
            System.out.println("1. Ajouter un contrat");
            System.out.println("2. Afficher tous les contrats");
            System.out.println("3. Mettre à jour un contrat");
            System.out.println("4. Supprimer un contrat");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    contratCommands.ajouterContrat();
                    break;
                case 2:
                    contratCommands.afficherTousLesContrats();
                    break;
                case 3:
                    contratCommands.mettreAJourContrat();
                    break;
                case 4:
                    contratCommands.supprimerContrat();
                    break;
                case 5:
                    System.out.println("Fermeture du gestionnaire de contrats.");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
}
