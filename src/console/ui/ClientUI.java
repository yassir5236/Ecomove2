package console.ui;

import console.commands.ClientCommands;

import java.util.Scanner;

public class ClientUI {
    private final ClientCommands clientCommands = new ClientCommands();

    public void start(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("=== Menu Client ===");
            System.out.println("1. Enregistrer un nouveau client");
            System.out.println("2. Connexion d'un client existant");
            System.out.println("3. Mettre à jour les informations personnelles");
            System.out.println("4. Retour au menu principal");
            System.out.print("Choisissez une option : ");

            if (scanner.hasNextInt()) {
                int choix = scanner.nextInt();
                scanner.nextLine();  // Consomme la nouvelle ligne

                switch (choix) {
                    case 1:
                        clientCommands.addClient(scanner);  // Enregistrement d'un nouveau client
                        break;
                    case 2:
                        clientCommands.authenticateClient(scanner);  // Connexion d'un client existant
                        break;
                    case 3:
                        clientCommands.updateClientInfo(scanner);  // Mise à jour des informations personnelles
                        break;
                    case 4:
                        running = false;
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
