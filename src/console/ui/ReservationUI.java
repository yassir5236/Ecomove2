package console.ui;
import console.commands.ReservationCommands;

import java.util.Scanner;

public class ReservationUI {
    private final ReservationCommands reservationCommands = new ReservationCommands();
    private final ClientUI clientUI = new ClientUI();  // Intégration du menu client

    public void start(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Gestion des clients");
            System.out.println("2. Recherche de billets");
            System.out.println("3. Consulter mes réservations");
            System.out.println("4. Annuler une réservation");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            if (scanner.hasNextInt()) {
                int choix = scanner.nextInt();
                scanner.nextLine();  // Consomme la nouvelle ligne

                switch (choix) {
                    case 1:
                        clientUI.start(scanner);  // Accès au menu client
                        break;
                    case 2:
                        reservationCommands.searchTickets(scanner);
                        break;
                    case 3:
                        reservationCommands.viewReservations(scanner);
                        break;
                    case 4:
                        reservationCommands.cancelReservation(scanner);
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
                scanner.nextLine();  // Consomme l'entrée invalide
            }
        }
    }
}
