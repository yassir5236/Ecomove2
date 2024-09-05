package console.ui;

import console.commands.OffrePromotionnelleCommands;

import java.util.Scanner;

public class OffreUI {
    private final Scanner scanner = new Scanner(System.in);
    private final OffrePromotionnelleCommands offreCommands = new OffrePromotionnelleCommands();

    public void start() {
        while (true) {
            System.out.println("=== Menu Gestion des Offres Promotionnelles ===");
            System.out.println("1. Ajouter une offre");
            System.out.println("2. Afficher toutes les offres");
            System.out.println("3. Mettre à jour une offre");
            System.out.println("4. Supprimer une offre");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    offreCommands.ajouterOffre();
                    break;
                case 2:
                    offreCommands.afficherToutesLesOffres();
                    break;
                case 3:
                    offreCommands.mettreAJourOffre();
                    break;
                case 4:
                    offreCommands.supprimerOffre();
                    break;
                case 5:
                    System.out.println("Fermeture du gestionnaire d'offres promotionnelles.");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
}
