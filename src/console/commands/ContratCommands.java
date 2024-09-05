package console.commands;


import model.Contrat;
import model.StatutContrat;
import dao.ContratDAO;

import java.util.Scanner;
import java.util.UUID;
import java.util.List;

import java.sql.Date;
import java.math.BigDecimal;
import java.util.InputMismatchException;













public class ContratCommands {
    private final ContratDAO contratDAO = new ContratDAO();
    private final Scanner scanner = new Scanner(System.in);


    public void ajouterContrat() {
        System.out.println("=== Ajouter un contrat ===");

        UUID id = UUID.randomUUID();
        UUID partenaireId = null;

        // Validation de l'UUID du partenaire
        while (partenaireId == null) {
            System.out.print("Entrez l'ID du partenaire : ");
            String partenaireIdStr = scanner.nextLine();
            try {
                partenaireId = UUID.fromString(partenaireIdStr);
            } catch (IllegalArgumentException e) {
                System.out.println("ID de partenaire invalide. Veuillez réessayer.");
            }
        }

        Date dateDebut = null;
        // Validation de la date de début
        while (dateDebut == null) {
            System.out.print("Entrez la date de début (YYYY-MM-DD) : ");
            String dateDebutStr = scanner.nextLine();
            try {
                dateDebut = Date.valueOf(dateDebutStr);
                if (dateDebut.before(new java.util.Date())) {
                    System.out.println("La date de début ne peut pas être antérieure à la date actuelle.");
                    dateDebut = null;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Format de date invalide. Veuillez utiliser le format YYYY-MM-DD.");
            }
        }

        Date dateFin = null;
        // Validation de la date de fin (optionnelle)
        while (true) {
            System.out.print("Entrez la date de fin (YYYY-MM-DD) (laisser vide si indéfinie) : ");
            String dateFinStr = scanner.nextLine();
            if (dateFinStr.isEmpty()) {
                break;
            }
            try {
                dateFin = Date.valueOf(dateFinStr);
                if (dateFin != null && dateFin.before(dateDebut)) {
                    System.out.println("La date de fin ne peut pas être antérieure à la date de début.");
                    dateFin = null;
                } else {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Format de date invalide. Veuillez utiliser le format YYYY-MM-DD.");
            }
        }

        BigDecimal tarifSpecial = null;
        // Validation du tarif spécial
        while (tarifSpecial == null) {
            System.out.print("Entrez le tarif spécial : ");
            try {
                tarifSpecial = BigDecimal.valueOf(scanner.nextDouble());
                if (tarifSpecial.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Le tarif spécial ne peut pas être négatif.");
                    tarifSpecial = null;
                }
            } catch (InputMismatchException e) {
                System.out.println("Le tarif doit être un nombre valide.");
                scanner.nextLine(); // Consomme la ligne incorrecte
            }
        }
        scanner.nextLine(); // Consomme la nouvelle ligne

        System.out.print("Entrez les conditions de l'accord : ");
        String conditionsAccord = scanner.nextLine();

        boolean renouvelable = false;
        // Validation de la valeur renouvelable
        while (true) {
            System.out.print("Le contrat est-il renouvelable ? (true/false) : ");
            String renouvelableStr = scanner.nextLine();
            if (renouvelableStr.equalsIgnoreCase("true") || renouvelableStr.equalsIgnoreCase("false")) {
                renouvelable = Boolean.parseBoolean(renouvelableStr);
                break;
            } else {
                System.out.println("Veuillez entrer 'true' ou 'false'.");
            }
        }

        StatutContrat statutContrat = null;
        // Validation du statut du contrat
        while (statutContrat == null) {
            System.out.print("Entrez le statut du contrat (EN_COURS, TERMINE, SUSPENDU) : ");
            String statutContratStr = scanner.nextLine();
            try {
                statutContrat = StatutContrat.valueOf(statutContratStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Statut du contrat invalide. Veuillez entrer l'une des valeurs suivantes : EN_COURS, TERMINE, SUSPENDU.");
            }
        }

        Contrat contrat = new Contrat(id, dateDebut, dateFin, tarifSpecial, conditionsAccord, renouvelable, statutContrat);
        contrat.setPartenaireId(partenaireId); // Associe le contrat à un partenaire

        contratDAO.addContrat(contrat);

        System.out.println("Contrat ajouté avec succès.");
    }

    public void afficherTousLesContrats() {
        System.out.println("=== Liste des contrats ===");
        List<Contrat> contrats = contratDAO.getAllContrats();

        for (Contrat contrat : contrats) {
            System.out.println(contrat);
        }
    }

    public void mettreAJourContrat() {
        System.out.println("=== Mettre à jour un contrat ===");

        System.out.print("Entrez l'ID du contrat à mettre à jour : ");
        String idStr = scanner.nextLine();
        UUID id = UUID.fromString(idStr);

        Contrat contrat = contratDAO.getContratById(id);
        if (contrat == null) {
            System.out.println("Contrat non trouvé.");
            return;
        }

        System.out.print("Entrez la nouvelle date de début (YYYY-MM-DD) (laisser vide pour ne pas modifier) : ");
        String dateDebutStr = scanner.nextLine();
        if (!dateDebutStr.isEmpty()) {
            Date dateDebut = Date.valueOf(dateDebutStr);
            contrat.setDateDebut(dateDebut);
        }

        System.out.print("Entrez la nouvelle date de fin (YYYY-MM-DD) (laisser vide pour ne pas modifier) : ");
        String dateFinStr = scanner.nextLine();
        if (!dateFinStr.isEmpty()) {
            Date dateFin = Date.valueOf(dateFinStr);
            contrat.setDateFin(dateFin);
        }

        System.out.print("Entrez le nouveau tarif spécial (laisser vide pour ne pas modifier) : ");
        String tarifSpecialStr = scanner.nextLine();
        if (!tarifSpecialStr.isEmpty()) {
            BigDecimal tarifSpecial = new BigDecimal(tarifSpecialStr);
            contrat.setTarifSpecial(tarifSpecial);
        }

        System.out.print("Entrez les nouvelles conditions de l'accord (laisser vide pour ne pas modifier) : ");
        String conditionsAccord = scanner.nextLine();
        if (!conditionsAccord.isEmpty()) {
            contrat.setConditionsAccord(conditionsAccord);
        }

        System.out.print("Le contrat est-il renouvelable ? (true/false, laisser vide pour ne pas modifier) : ");
        String renouvelableStr = scanner.nextLine();
        if (!renouvelableStr.isEmpty()) {
            boolean renouvelable = Boolean.parseBoolean(renouvelableStr);
            contrat.setRenouvelable(renouvelable);
        }

        System.out.print("Entrez le nouveau statut du contrat (laisser vide pour ne pas modifier) : ");
        String statutContratStr = scanner.nextLine();
        if (!statutContratStr.isEmpty()) {
            StatutContrat statutContrat = StatutContrat.valueOf(statutContratStr);
            contrat.setStatutContrat(statutContrat);
        }

        contratDAO.updateContrat(contrat);
        System.out.println("Contrat mis à jour avec succès.");
    }

    public void supprimerContrat() {
        System.out.println("=== Supprimer un contrat ===");

        System.out.print("Entrez l'ID du contrat à supprimer : ");
        String idStr = scanner.nextLine();
        UUID id = UUID.fromString(idStr);

        contratDAO.deleteContrat(id);
        System.out.println("Contrat supprimé avec succès.");
    }
}
