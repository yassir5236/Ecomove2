

/*package console.ui;


import java.util.Scanner;

import java.util.Scanner;

public class MainConsole {

    private final PartenaireUI partenaireUI;
    private final ContratUI contratUI;
    private final OffreUI offreUI;
    private final BilletUI billetUI;

    public MainConsole() {
        this.partenaireUI = new PartenaireUI();
        this.contratUI = new ContratUI();
        this.offreUI = new OffreUI();
        this.billetUI = new BilletUI();
    }
    int choix;

    public void start() {
        Scanner scanner = new Scanner(System.in);


        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Gestion des Partenaires");
            System.out.println("2. Gestion des Contrats");
            System.out.println("3. Gestion des Offres Promotionnelles");
            System.out.println("4. Gestion des Billets");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine(); // Consommer la nouvelle ligne

                switch (choix) {
                    case 1:
                        partenaireUI.start(scanner);
                        break;
                    case 2:
                        contratUI.start(scanner);
                        break;
                    case 3:
                        offreUI.start();
                        break;
                    case 4:
                        billetUI.start();
                        break;
                    case 5:
                        System.out.println("Au revoir!");
                        break;
                    default:
                        System.out.println("Option invalide. Essayez encore.");
                }
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Consomme l'entrée incorrecte
            }
        } while (choix != 5);

        scanner.close();
    }

    public static void main(String[] args) {
        MainConsole mainConsole = new MainConsole();
        mainConsole.start();
    }
}
*/


package console.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainConsole {

    private final PartenaireUI partenaireUI;
    private final ContratUI contratUI;
    private final OffreUI offreUI;
    private final BilletUI billetUI;
    private final Scanner scanner;

    public MainConsole() {
        this.partenaireUI = new PartenaireUI();
        this.contratUI = new ContratUI();
        this.offreUI = new OffreUI();
        this.billetUI = new BilletUI();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choix;

        do {
            afficherTitreMenuPrincipal();
            afficherMenuPrincipal();
            choix = lireChoixUtilisateur();

            traiterChoix(choix);

        } while (choix != 5);

        scanner.close();
    }

    private void afficherTitreMenuPrincipal() {
        System.out.println("\n" + "=".repeat(30));
        System.out.println("=".repeat(30));
        System.out.printf(" %s%n", center("MENU PRINCIPAL", 30));
        System.out.println("=".repeat(30));
        System.out.println("=".repeat(30) + "\n");
    }

    private void afficherMenuPrincipal() {
        System.out.println("1. Gestion des Partenaires");
        System.out.println("2. Gestion des Contrats");
        System.out.println("3. Gestion des Offres Promotionnelles");
        System.out.println("4. Gestion des Billets");
        System.out.println("5. Quitter");
        System.out.print("Choisissez une option : ");
    }

    private int lireChoixUtilisateur() {
        int choix = -1;
        try {
            choix = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            scanner.nextLine();
        }
        return choix;
    }

    private void traiterChoix(int choix) {
        switch (choix) {
            case 1:
                partenaireUI.start(scanner);
                break;
            case 2:
                contratUI.start(scanner);
                break;
            case 3:
                offreUI.start();
                break;
            case 4:
                billetUI.start();
                break;
            case 5:
                System.out.println("Au revoir!");
                break;
            default:
                System.out.println("Option invalide. Essayez encore.");
        }
    }

    public static String center(String text, int width) {
        int padding = width - text.length();
        if (padding > 0) {
            int padStart = padding / 2;
            int padEnd = padding - padStart;
            return " ".repeat(padStart) + text + " ".repeat(padEnd);
        }
        return text;
    }

    public static void main(String[] args) {
        MainConsole mainConsole = new MainConsole();
        mainConsole.start();
    }
}




