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

public class BilletCommands {

    private final BilletDAO billetDAO;

    public BilletCommands() {
        this.billetDAO = new BilletDAO();
    }



   /* public void ajouterBillet() {
        Scanner scanner = new Scanner(System.in);

        UUID id = UUID.randomUUID();

        System.out.print("Entrez l'ID du contrat associé : ");
        String contratIdStr = scanner.nextLine();
        UUID contratId = UUID.fromString(contratIdStr);

        System.out.print("Type de transport (avion, train, bus) : ");
        TypeTransport typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Prix d'achat : ");
        BigDecimal prixAchat = scanner.nextBigDecimal();

        System.out.print("Prix de vente : ");
        BigDecimal prixVente = scanner.nextBigDecimal();

        scanner.nextLine(); // Consommer la nouvelle ligne restante après nextBigDecimal()

        System.out.print("Date de vente (YYYY-MM-DD HH:MM:SS) : ");
        String dateVenteInput = scanner.nextLine();

        LocalDateTime dateVente = null;
        try {
            dateVente = LocalDateTime.parse(dateVenteInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD HH:MM:SS.");
            return ;
        }

        System.out.print("Statut du billet (vendu, annulé, en attente) : ");
        StatutBillet statutBillet = StatutBillet.valueOf(scanner.nextLine().toUpperCase());

        // Notez que vous passez ici deux UUID au constructeur
        Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, Date.valueOf(dateVente.toLocalDate()), statutBillet);
        billetDAO.addBillet(billet);

        System.out.println("Billet ajouté avec succès!");
    }

    */

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

        LocalDateTime dateVente = null;
        while (dateVente == null) {
            try {
                System.out.print("Date de vente (YYYY-MM-DD HH:MM:SS) : ");
                String dateVenteInput = scanner.nextLine();
                dateVente = LocalDateTime.parse(dateVenteInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (dateVente.isBefore(LocalDateTime.now())) {
                    System.out.println("La date de vente ne peut pas être antérieure à la date et l'heure actuelles. Veuillez réessayer.");
                    dateVente = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD HH:MM:SS.");
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

        Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, Date.valueOf(dateVente.toLocalDate()), statutBillet);
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

   /* public void mettreAJourBillet() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID du billet à mettre à jour (UUID) : ");
        UUID id = UUID.fromString(scanner.nextLine());

        System.out.print("Entrez l'ID du contrat associé : ");
        String contratIdStr = scanner.nextLine();
        UUID contratId = UUID.fromString(contratIdStr);

        System.out.print("Nouveau type de transport (avion, train, bus) : ");
        TypeTransport typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Nouveau prix d'achat : ");
        BigDecimal prixAchat = scanner.nextBigDecimal();

        System.out.print("Nouveau prix de vente : ");
        BigDecimal prixVente = scanner.nextBigDecimal();

        scanner.nextLine();  // Consommer la nouvelle ligne restante après nextBigDecimal()

        System.out.print("Nouvelle date de vente (YYYY-MM-DD HH:MM:SS) : ");
        String dateVenteInput = scanner.nextLine();
        LocalDateTime dateVente = null;

        try {
            dateVente = LocalDateTime.parse(dateVenteInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD HH:MM:SS.");
            return;  // Arrêter l'exécution si la date n'est pas correcte
        }

        System.out.print("Nouveau statut du billet (vendu, annulé, en attente) : ");
        StatutBillet statutBillet = StatutBillet.valueOf(scanner.nextLine().toUpperCase());

        // Notez que vous passez ici les deux UUID au constructeur
        Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, Date.valueOf(dateVente.toLocalDate()), statutBillet);
        billetDAO.updateBillet(billet);

        System.out.println("Billet mis à jour avec succès!");
    }
*/

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

        LocalDateTime dateVente = null;
        while (dateVente == null) {
            try {
                System.out.print("Nouvelle date de vente (YYYY-MM-DD HH:MM:SS) : ");
                String dateVenteInput = scanner.nextLine();
                dateVente = LocalDateTime.parse(dateVenteInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (dateVente.isBefore(LocalDateTime.now())) {
                    System.out.println("La date de vente ne peut pas être antérieure à la date et l'heure actuelles. Veuillez réessayer.");
                    dateVente = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Veuillez entrer une date au format YYYY-MM-DD HH:MM:SS.");
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

        Billet billet = new Billet(id, contratId, typeTransport, prixAchat, prixVente, Date.valueOf(dateVente.toLocalDate()), statutBillet);
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




