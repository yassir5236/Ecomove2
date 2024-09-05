/*package console.ui;

import java.util.Scanner;
import console.commands.*;

public class PartenaireUI {
    private final PartenaireCommands partenaireCommands = new PartenaireCommands();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Ajouter un partenaire");
            System.out.println("2. Afficher tous les partenaires");
            System.out.println("3. Mettre à jour un partenaire");
            System.out.println("4. Supprimer un partenaire");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    partenaireCommands.addPartenaire(scanner);
                    break;
                case 2:
                    partenaireCommands.displayAllPartenaires();
                    break;
                case 3:
                    partenaireCommands.updatePartenaire(scanner);
                    break;
                case 4:
                    partenaireCommands.deletePartenaire(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }

        scanner.close();
    }
}
*/
package console.ui;

import java.util.Scanner;
import console.commands.*;

public class PartenaireUI {
    private final PartenaireCommands partenaireCommands = new PartenaireCommands();

    public void start(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("=== Menu Partenaire ===");
            System.out.println("1. Ajouter un partenaire");
            System.out.println("2. Afficher tous les partenaires");
            System.out.println("3. Mettre à jour un partenaire");
            System.out.println("4. Supprimer un partenaire");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            if (scanner.hasNextInt()) {
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        partenaireCommands.addPartenaire(scanner);
                        break;
                    case 2:
                        partenaireCommands.displayAllPartenaires();
                        break;
                    case 3:
                        partenaireCommands.updatePartenaire(scanner);
                        break;
                    case 4:
                        partenaireCommands.deletePartenaire(scanner);
                        break;
                    case 5:
                        running = false;
                        System.out.println("Au revoir !");
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine();
            }
        }
    }
}
