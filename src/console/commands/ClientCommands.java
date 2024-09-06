package console.commands;

import model.Client;
import service.ClientService;

import java.util.Scanner;
import java.util.UUID;

public class ClientCommands {
    private final ClientService clientService = new ClientService();

    // Enregistrement d'un nouveau client
    public void addClient(Scanner scanner) {
        System.out.println("=== Enregistrement d'un nouveau client ===");

        // Saisie des informations du client
        System.out.println("Entrez votre nom :");
        String nom = scanner.nextLine().trim();  // Supprime les espaces inutiles

        System.out.println("Entrez votre prénom :");
        String prenom = scanner.nextLine().trim();

        System.out.println("Entrez votre adresse e-mail :");
        String email = scanner.nextLine().trim();

        // Validation basique de l'e-mail
        while (!email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$")) {
            System.out.println("Adresse e-mail invalide. Veuillez entrer une adresse e-mail valide :");
            email = scanner.nextLine().trim();
        }

        System.out.println("Entrez votre numéro de téléphone :");
        String telephone = scanner.nextLine().trim();

        // Validation basique du numéro de téléphone (exemple pour 10 chiffres)
        while (!telephone.matches("^\\d{10}$")) {
            System.out.println("Numéro de téléphone invalide. Veuillez entrer un numéro à 10 chiffres :");
            telephone = scanner.nextLine().trim();
        }

        // Création et enregistrement du nouveau client
        Client client = new Client(UUID.randomUUID(), nom, prenom, email, telephone);
        clientService.addClient(client);

        System.out.println("Client enregistré avec succès ! Vous pouvez maintenant rechercher et réserver des billets.");
    }

    // Méthode pour authentifier un client existant par e-mail
    public Client authenticateClientByEmail(String email) {
        return clientService.getClientByNameOrEmail(email);
    }

    // Méthode pour mettre à jour les informations d'un client
    public boolean updateClientInfo(Client client, Scanner scanner) {
        System.out.println("=== Mise à jour des informations ===");

        System.out.println("Entrez votre nouvelle adresse e-mail (laisser vide pour ne pas modifier) :");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) {
            client.setEmail(email);
        }

        System.out.println("Entrez votre nouveau numéro de téléphone (laisser vide pour ne pas modifier) :");
        String telephone = scanner.nextLine().trim();
        if (!telephone.isEmpty()) {
            client.setTelephone(telephone);
        }

        // Appel à la méthode de ClientService qui renvoie un boolean
        return clientService.updateClient(client);
    }
}
