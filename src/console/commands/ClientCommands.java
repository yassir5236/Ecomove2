package console.commands;

import model.Client;
import service.ClientService;

import java.util.Scanner;
import java.util.UUID;

public class ClientCommands {
    private final ClientService clientService = new ClientService();

    public void addClient(Scanner scanner) {
        System.out.println("Entrez votre nom :");
        String nom = scanner.nextLine();

        System.out.println("Entrez votre prénom :");
        String prenom = scanner.nextLine();

        System.out.println("Entrez votre adresse e-mail :");
        String email = scanner.nextLine();

        System.out.println("Entrez votre numéro de téléphone :");
        String telephone = scanner.nextLine();

        Client client = new Client(UUID.randomUUID(), nom, prenom, email, telephone);
        clientService.addClient(client);

        System.out.println("Client enregistré avec succès !");  // User Story 1
    }

    public void authenticateClient(Scanner scanner) {
        System.out.println("Entrez votre nom ou adresse e-mail :");
        String identifier = scanner.nextLine();

        Client client = clientService.getClientByNameOrEmail(identifier);
        if (client != null) {
            System.out.println("Bienvenue, " + client.getPrenom() + " !");  // User Story 2
        } else {
            System.out.println("Client non trouvé. Veuillez vérifier vos informations.");
        }
    }

    public void updateClientInfo(Scanner scanner) {
        System.out.println("Entrez l'ID du client :");
        UUID clientId = UUID.fromString(scanner.nextLine());

        Client client = clientService.getClientById(clientId);
        if (client == null) {
            System.out.println("Client non trouvé.");
            return;
        }

        System.out.println("Entrez votre nouvelle adresse e-mail (laisser vide pour ne pas modifier) :");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            client.setEmail(email);
        }

        System.out.println("Entrez votre nouveau numéro de téléphone (laisser vide pour ne pas modifier) :");
        String telephone = scanner.nextLine();
        if (!telephone.isEmpty()) {
            client.setTelephone(telephone);
        }

        clientService.updateClient(client);
        System.out.println("Informations mises à jour avec succès !");  // User Story 8
    }
}
