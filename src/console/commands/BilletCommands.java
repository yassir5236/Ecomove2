package console.commands;

import dao.BilletDAO;
import model.Billet;
import model.TypeTransport;
import model.StatutBillet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;


public class BilletCommands {

    private final BilletDAO billetDAO;

    public BilletCommands() {
        this.billetDAO = new BilletDAO();
    }





    public void ajouterBillet() {
        System.out.println("=== Ajouter un billet ===");
        Scanner scanner = new Scanner(System.in);

        UUID id = UUID.randomUUID();

        UUID contratId = null;
        while (contratId == null) {
            try {
                System.out.print("Entrez l'ID du contrat associé : ");
                String contratIdStr = scanner.nextLine();
                contratId = UUID.fromString(contratIdStr);
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

        LocalDate dateVente = null;
        while (dateVente == null) {
            try {
                System.out.print("Date de vente (YYYY-MM-DD) : ");
                String dateVenteInput = scanner.nextLine();
                dateVente = LocalDate.parse(dateVenteInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (dateVente.isBefore(LocalDate.now())) {
                    System.out.println("La date de vente ne peut pas être antérieure à la date actuelle. Veuillez réessayer.");
                    dateVente = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD.");
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

        String villeDepart = "";
        while (villeDepart.isEmpty()) {
            System.out.print("Ville de départ : ");
            villeDepart = scanner.nextLine();
        }

        String villeDestination = "";
        while (villeDestination.isEmpty()) {
            System.out.print("Ville de destination : ");
            villeDestination = scanner.nextLine();
        }

        LocalDate dateDepart = null;
        while (dateDepart == null) {
            try {
                System.out.print("Date de départ (YYYY-MM-DD) : ");
                String dateDepartInput = scanner.nextLine();
                dateDepart = LocalDate.parse(dateDepartInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD.");
            }
        }

        LocalTime horaire = null;
        while (horaire == null) {
            try {
                System.out.print("Horaire (HH:MM) : ");
                String horaireInput = scanner.nextLine();
                horaire = LocalTime.parse(horaireInput, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de l'heure est incorrect. Veuillez entrer une heure au format HH:MM.");
            }
        }

        Duration duree = null;
        while (duree == null) {
            try {
                System.out.print("Durée (en heures) : ");
                String dureeInput = scanner.nextLine();
                duree = Duration.ofHours(Long.parseLong(dureeInput));
            } catch (NumberFormatException e) {
                System.out.println("Durée invalide. Veuillez entrer une durée en heures.");
            }
        }

        Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, dateVente, statutBillet, villeDepart, villeDestination, dateDepart, horaire, duree);
        billetDAO.addBillet(billet);

        System.out.println("Billet ajouté avec succès!");
    }




    public void afficherTousLesBillets() {
        List<Billet> billets = billetDAO.getAllBillets();
        System.out.println("=== Liste des billets ===");
        for (Billet billet : billets) {
            System.out.println(billet);
        }
    }


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

        UUID contratId = null;
        while (contratId == null) {
            try {
                System.out.print("Entrez l'ID du contrat associé : ");
                String contratIdStr = scanner.nextLine();
                contratId = UUID.fromString(contratIdStr);
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

        LocalDate dateVente = null;
        while (dateVente == null) {
            try {
                System.out.print("Nouvelle date de vente (YYYY-MM-DD) : ");
                String dateVenteInput = scanner.nextLine();
                dateVente = LocalDate.parse(dateVenteInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (dateVente.isBefore(LocalDate.now())) {
                    System.out.println("La date de vente ne peut pas être antérieure à la date actuelle. Veuillez réessayer.");
                    dateVente = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD.");
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

        String villeDepart = "";
        while (villeDepart.isEmpty()) {
            System.out.print("Nouvelle ville de départ : ");
            villeDepart = scanner.nextLine();
        }

        String villeDestination = "";
        while (villeDestination.isEmpty()) {
            System.out.print("Nouvelle ville de destination : ");
            villeDestination = scanner.nextLine();
        }

        LocalDate dateDepart = null;
        while (dateDepart == null) {
            try {
                System.out.print("Nouvelle date de départ (YYYY-MM-DD) : ");
                String dateDepartInput = scanner.nextLine();
                dateDepart = LocalDate.parse(dateDepartInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD.");
            }
        }

        LocalTime horaire = null;
        while (horaire == null) {
            try {
                System.out.print("Nouvel horaire (HH:MM) : ");
                String horaireInput = scanner.nextLine();
                horaire = LocalTime.parse(horaireInput, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de l'heure est incorrect. Veuillez entrer une heure au format HH:MM.");
            }
        }

        Duration duree = null;
        while (duree == null) {
            try {
                System.out.print("Nouvelle durée (en heures) : ");
                String dureeInput = scanner.nextLine();
                duree = Duration.ofHours(Long.parseLong(dureeInput));
            } catch (NumberFormatException e) {
                System.out.println("Durée invalide. Veuillez entrer une durée en heures.");
            }
        }

        Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, dateVente, statutBillet, villeDepart, villeDestination, dateDepart, horaire, duree);
        billetDAO.updateBillet(billet);

        System.out.println("Billet mis à jour avec succès!");
    }


    public void supprimerBillet() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID du billet à supprimer (UUID) : ");
        UUID id = UUID.fromString(scanner.nextLine());

        billetDAO.deleteBillet(id);

        System.out.println("Billet supprimé avec succès!");
    }
}




