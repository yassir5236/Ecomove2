package console.ui;

import console.commands.ClientCommands;
import model.Client;

import java.util.Scanner;

public class ClientUI {
    private final ClientCommands clientCommands = new ClientCommands();

    public void start(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("=== Menu Client ===");
            System.out.println("1. Gérer votre compte");
            System.out.println("2. Retour au menu principal");
            System.out.print("Choisissez une option : ");

            if (scanner.hasNextInt()) {
                int choix = scanner.nextInt();
                scanner.nextLine();  // Consomme la nouvelle ligne

                switch (choix) {
                    case 1:
                        handleAccountManagement(scanner);
                        break;
                    case 2:
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

    private void handleAccountManagement(Scanner scanner) {
        System.out.println("=== Gestion de Compte ===");
        System.out.println("Avez-vous un compte ?");
        System.out.println("1. Oui");
        System.out.println("2. Non");
        System.out.print("Choisissez une option : ");

        if (scanner.hasNextInt()) {
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    // Connexion d'un client existant
                    System.out.println("Entrez votre adresse e-mail :");
                    String email = scanner.nextLine().trim();
                    Client client = clientCommands.authenticateClientByEmail(email);
                    if (client != null) {
                        System.out.println("Bienvenue, " + client.getPrenom() + " !");
                        boolean updated = clientCommands.updateClientInfo(client, scanner);
                        if (updated) {
                            System.out.println("Informations mises à jour avec succès !");
                        } else {
                            System.out.println("Erreur lors de la mise à jour des informations.");
                        }
                    } else {
                        System.out.println("Client non trouvé. Veuillez vérifier votre adresse e-mail.");
                    }
                    break;
                case 2:
                    // Demander si l'utilisateur souhaite créer un compte
                    System.out.println("Souhaitez-vous créer un compte ?");
                    System.out.println("1. Oui");
                    System.out.println("2. Non");
                    System.out.print("Choisissez une option : ");

                    if (scanner.hasNextInt()) {
                        int createAccountChoice = scanner.nextInt();
                        scanner.nextLine();  // Consomme la nouvelle ligne

                        switch (createAccountChoice) {
                            case 1:
                                // Enregistrement d'un nouveau client
                                clientCommands.addClient(scanner);
                                break;
                            case 2:
                                System.out.println("Retour au menu principal.");
                                break;
                            default:
                                System.out.println("Option invalide. Veuillez réessayer.");
                        }
                    } else {
                        System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                        scanner.nextLine();  // Consomme l'entrée invalide
                    }
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
