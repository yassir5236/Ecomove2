package console.commands;

import model.Billet;
import model.Client;
import service.BilletService;
import service.ClientService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ClientCommands {
    private final ClientService clientService = new ClientService();
    private final BilletService billetService = new BilletService();

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

        // Appel à la méthozde de ClientService qui renvoie un boolean
        return clientService.updateClient(client);
    }


    public void searchBillets(String villeDepart, String villeDestination, String dateDepart) {
        List<Billet> billets = billetService.searchBillets(villeDepart, villeDestination, LocalDate.parse(dateDepart));

        if (billets.isEmpty()) {
            System.out.println("Aucun billet trouvé pour les critères spécifiés.");
        } else {
            System.out.println("=== Billets trouvés ===");
            for (Billet billet : billets) {
                System.out.println("ID du billet: " + billet.getId());
                System.out.println("Type de transport: " + billet.getTypeTransport());
                System.out.println("Prix d'achat: " + billet.getPrixAchat());
                System.out.println("Prix de vente: " + billet.getPrixVente());
                System.out.println("Date de vente: " + billet.getDateVente());
                System.out.println("Statut du billet: " + billet.getStatutBillet());
                System.out.println("Nom de la compagnie: " + billet.getNomCompagnie());
                System.out.println("Ville de départ: " + billet.getVilleDepart());
                System.out.println("Ville de destination: " + billet.getVilleDestination());
                System.out.println("Date de départ: " + billet.getDateDepart());
                System.out.println("Horaire: " + billet.getHoraire());
                System.out.println("Durée du trajet: " + billet.getDuree() + " heures");
                System.out.println("-----------------------------------");
            }
        }
    }


}
