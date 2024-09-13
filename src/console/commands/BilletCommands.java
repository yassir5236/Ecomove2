package console.commands;

import model.Billet;
import model.TypeTransport;
import model.StatutBillet;
import service.BilletService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BilletCommands {

    private final BilletService billetService;

    public BilletCommands() {
        this.billetService = new BilletService(); // Utilisation du service BilletService
    }

    // Méthode pour ajouter un billet
    public void ajouterBillet() {
        System.out.println("=== Ajouter un billet ===");
        Scanner scanner = new Scanner(System.in);

        UUID id = UUID.randomUUID();

        UUID contratId = null;
        while (contratId == null) {
            try {
                System.out.print("Entrez l'ID du contrat associé : ");
                contratId = UUID.fromString(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("ID du contrat invalide. Veuillez entrer un UUID valide.");
            }
        }

        TypeTransport typeTransport = null;
        while (typeTransport == null) {
            try {
                System.out.print("Type de transport (AVION, TRAIN, BUS) : ");
                typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Type de transport invalide. Veuillez choisir entre AVION, TRAIN, BUS.");
            }
        }

        BigDecimal prixAchat = null;
        while (prixAchat == null) {
            try {
                System.out.print("Prix d'achat : ");
                prixAchat = new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Prix d'achat invalide. Veuillez entrer un montant valide.");
            }
        }

        BigDecimal prixVente = null;
        while (prixVente == null) {
            try {
                System.out.print("Prix de vente : ");
                prixVente = new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Prix de vente invalide. Veuillez entrer un montant valide.");
            }
        }

        Timestamp dateVente = null;
        while (dateVente == null) {
            try {
                System.out.print("Date de vente (YYYY-MM-DD HH:MM) : ");
                String dateVenteInput = scanner.nextLine();
                dateVente = Timestamp.valueOf(LocalDateTime.parse(dateVenteInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD HH:MM.");
            }
        }

        StatutBillet statutBillet = null;
        while (statutBillet == null) {
            try {
                System.out.print("Statut du billet (VENDU, ANNULE, EN_ATTENTE) : ");
                statutBillet = StatutBillet.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Statut du billet invalide. Veuillez choisir entre VENDU, ANNULE, EN_ATTENTE.");
            }
        }

        System.out.print("Trajet ID : ");
        int trajetId = Integer.parseInt(scanner.nextLine());

        LocalDate dateDepart = null;
        while (dateDepart == null) {
            try {
                System.out.print("Date de départ (YYYY-MM-DD) : ");
                dateDepart = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD.");
            }
        }

        LocalTime horaire = null;
        while (horaire == null) {
            try {
                System.out.print("Horaire (HH:MM) : ");
                horaire = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de l'heure est incorrect. Veuillez entrer une heure au format HH:MM.");
            }
        }

        Billet billet = new Billet(id, typeTransport, prixAchat, prixVente, dateVente, statutBillet, contratId, trajetId, Date.valueOf(dateDepart), Time.valueOf(horaire) , null,null,null,null);
        billetService.addBillet(billet);

        System.out.println("Billet ajouté avec succès!");
    }

    // Méthode pour afficher tous les billets
    public void afficherTousLesBillets() {
        List<Billet> billets = billetService.getAllBillets();
        System.out.println("=== Liste des billets ===");
        for (Billet billet : billets) {
            System.out.println(billet);
        }
    }

    // Méthode pour mettre à jour un billet
    public void mettreAJourBillet() {
        System.out.println("=== Mettre à jour un billet ===");
        Scanner scanner = new Scanner(System.in);

        UUID id = null;
        while (id == null) {
            try {
                System.out.print("ID du billet à mettre à jour (UUID) : ");
                id = UUID.fromString(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("ID de billet invalide. Veuillez entrer un UUID valide.");
            }
        }

        Billet billetExist = billetService.getBilletById(id);
        if (billetExist == null) {
            System.out.println("Aucun billet trouvé avec cet ID.");
            return;
        }

        UUID contratId = null;
        while (contratId == null) {
            try {
                System.out.print("Entrez l'ID du contrat associé : ");
                contratId = UUID.fromString(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("ID du contrat invalide. Veuillez entrer un UUID valide.");
            }
        }

        TypeTransport typeTransport = null;
        while (typeTransport == null) {
            try {
                System.out.print("Nouveau type de transport (AVION, TRAIN, BUS) : ");
                typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Type de transport invalide. Veuillez choisir entre AVION, TRAIN, BUS.");
            }
        }

        BigDecimal prixAchat = null;
        while (prixAchat == null) {
            try {
                System.out.print("Nouveau prix d'achat : ");
                prixAchat = new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Prix d'achat invalide. Veuillez entrer un montant valide.");
            }
        }

        BigDecimal prixVente = null;
        while (prixVente == null) {
            try {
                System.out.print("Nouveau prix de vente : ");
                prixVente = new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Prix de vente invalide. Veuillez entrer un montant valide.");
            }
        }

        Timestamp dateVente = null;
        while (dateVente == null) {
            try {
                System.out.print("Nouvelle date de vente (YYYY-MM-DD HH:MM) : ");
                dateVente = Timestamp.valueOf(LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD HH:MM.");
            }
        }

        StatutBillet statutBillet = null;
        while (statutBillet == null) {
            try {
                System.out.print("Nouveau statut du billet (VENDU, ANNULE, EN_ATTENTE) : ");
                statutBillet = StatutBillet.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Statut du billet invalide. Veuillez choisir entre VENDU, ANNULE, EN_ATTENTE.");
            }
        }

        System.out.print("Nouveau trajet ID : ");
        int trajetId = Integer.parseInt(scanner.nextLine());

        LocalDate dateDepart = null;
        while (dateDepart == null) {
            try {
                System.out.print("Nouvelle date de départ (YYYY-MM-DD) : ");
                dateDepart = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD.");
            }
        }

        LocalTime horaire = null;
        while (horaire == null) {
            try {
                System.out.print("Nouvel horaire (HH:MM) : ");
                horaire = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de l'heure est incorrect. Veuillez entrer une heure au format HH:MM.");
            }
        }

        Billet billet = new Billet(id, typeTransport, prixAchat, prixVente, dateVente, statutBillet, contratId, trajetId, Date.valueOf(dateDepart), Time.valueOf(horaire),null,null,null,null);
        billetService.updateBillet(billet);

        System.out.println("Billet mis à jour avec succès!");
    }

    // Méthode pour supprimer un billet
    public void supprimerBillet() {
        System.out.println("=== Supprimer un billet ===");
        Scanner scanner = new Scanner(System.in);

        UUID id = null;
        while (id == null) {
            try {
                System.out.print("Entrez l'ID du billet à supprimer (UUID) : ");
                id = UUID.fromString(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("ID de billet invalide. Veuillez entrer un UUID valide.");
            }
        }

        billetService.deleteBillet(id); // Appel du service pour supprimer le billet
        System.out.println("Billet supprimé avec succès!");
    }

    // Méthode pour rechercher des billets
    public void rechercherBillets() {
        System.out.println("=== Rechercher des billets ===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ville de départ : ");
        String villeDepart = scanner.nextLine();

        System.out.print("Ville de destination : ");
        String villeDestination = scanner.nextLine();

        System.out.print("Date de départ (YYYY-MM-DD) : ");
        String dateDepartStr = scanner.nextLine();

        // Convertir la chaîne en LocalDate
        LocalDate dateDepart;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateDepart = LocalDate.parse(dateDepartStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Format de date invalide. Utilisez YYYY-MM-DD.");
            return; // Sortir de la méthode si la date est invalide
        }

        List<Billet> billets = billetService.searchBillets(villeDepart, villeDestination, dateDepart);
        System.out.println("=== Résultats de la recherche ===");
        for (Billet billet : billets) {
            System.out.println(billet);
        }
    }



}
