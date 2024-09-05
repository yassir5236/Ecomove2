package console.commands;

import model.OffrePromotionnelle;
import dao.OffrePromotionnelleDAO;
import model.TypeReduction;
import model.StatutOffre;

import java.util.Scanner;
import java.util.UUID;
import java.sql.Date;
import java.math.BigDecimal;
import java.util.List;

import java.time.LocalDate;


public class OffrePromotionnelleCommands {
    private final OffrePromotionnelleDAO offreDAO = new OffrePromotionnelleDAO();
    private final Scanner scanner = new Scanner(System.in);




    public void ajouterOffre() {
        System.out.println("=== Ajouter une offre promotionnelle ===");

        UUID id = UUID.randomUUID();
        UUID contratId = null;

        while (contratId == null) {
            try {
                System.out.print("Entrez l'ID du contrat associé : ");
                String contratIdStr = scanner.nextLine();
                contratId = UUID.fromString(contratIdStr);
            } catch (IllegalArgumentException e) {
                System.out.println("ID de contrat invalide. Veuillez réessayer.");
            }
        }

        System.out.print("Entrez le nom de l'offre : ");
        String nomOffre = scanner.nextLine();

        System.out.print("Entrez la description de l'offre : ");
        String description = scanner.nextLine();

        Date dateDebut = null;
        while (dateDebut == null) {
            try {
                System.out.print("Entrez la date de début (YYYY-MM-DD) : ");
                String dateDebutStr = scanner.nextLine();
                dateDebut = Date.valueOf(dateDebutStr);
                if (dateDebut.toLocalDate().isBefore(LocalDate.now())) {
                    System.out.println("La date de début ne peut pas être antérieure à aujourd'hui. Veuillez réessayer.");
                    dateDebut = null;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Format de date invalide. Veuillez réessayer.");
            }
        }

        Date dateFin = null;
        while (dateFin == null) {
            try {
                System.out.print("Entrez la date de fin (YYYY-MM-DD) : ");
                String dateFinStr = scanner.nextLine();
                dateFin = Date.valueOf(dateFinStr);
                if (dateFin.toLocalDate().isBefore(dateDebut.toLocalDate())) {
                    System.out.println("La date de fin ne peut pas être antérieure à la date de début. Veuillez réessayer.");
                    dateFin = null;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Format de date invalide. Veuillez réessayer.");
            }
        }

        TypeReduction typeReduction = null;
        while (typeReduction == null) {
            try {
                System.out.print("Entrez le type de réduction (POURCENTAGE, MONTANT_FIXE) : ");
                String typeReductionStr = scanner.nextLine();
                typeReduction = TypeReduction.valueOf(typeReductionStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Type de réduction invalide. Veuillez réessayer.");
            }
        }

        BigDecimal valeurReduction = null;
        while (valeurReduction == null) {
            try {
                System.out.print("Entrez la valeur de la réduction : ");
                valeurReduction = new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valeur de réduction invalide. Veuillez réessayer.");
            }
        }

        System.out.print("Entrez les conditions de l'offre : ");
        String conditions = scanner.nextLine();

        StatutOffre statutOffre = null;
        while (statutOffre == null) {
            try {
                System.out.print("Entrez le statut de l'offre (ACTIVE, EXPIREE) : ");
                String statutOffreStr = scanner.nextLine();
                statutOffre = StatutOffre.valueOf(statutOffreStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Statut d'offre invalide. Veuillez réessayer.");
            }
        }

        OffrePromotionnelle offre = new OffrePromotionnelle(id, nomOffre, description, dateDebut, dateFin,
                typeReduction, valeurReduction, conditions, statutOffre, contratId);

        offreDAO.addOffrePromotionnelle(offre);

        System.out.println("Offre promotionnelle ajoutée avec succès.");
    }

    public void afficherToutesLesOffres() {
        System.out.println("=== Liste des offres promotionnelles ===");
        List<OffrePromotionnelle> offres = offreDAO.getAllOffresPromotionnelles();

        for (OffrePromotionnelle offre : offres) {
            System.out.println(offre);
        }
    }



    public void mettreAJourOffre() {
        System.out.println("=== Mettre à jour une offre promotionnelle ===");

        UUID id = null;
        while (id == null) {
            try {
                System.out.print("Entrez l'ID de l'offre à mettre à jour : ");
                String idStr = scanner.nextLine();
                id = UUID.fromString(idStr);
            } catch (IllegalArgumentException e) {
                System.out.println("ID invalide. Veuillez entrer un UUID valide.");
            }
        }

        OffrePromotionnelle offre = offreDAO.getOffreById(id);
        if (offre == null) {
            System.out.println("Offre non trouvée.");
            return;
        }

        System.out.print("Entrez le nouveau nom de l'offre (laisser vide pour ne pas modifier) : ");
        String nomOffre = scanner.nextLine();
        if (!nomOffre.isEmpty()) {
            offre.setNomOffre(nomOffre);
        }

        System.out.print("Entrez la nouvelle description de l'offre (laisser vide pour ne pas modifier) : ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            offre.setDescription(description);
        }

        String dateDebutStr;
        Date dateDebut = null;
        do {
            System.out.print("Entrez la nouvelle date de début (YYYY-MM-DD) (laisser vide pour ne pas modifier) : ");
            dateDebutStr = scanner.nextLine();
            if (!dateDebutStr.isEmpty()) {
                try {
                    dateDebut = Date.valueOf(dateDebutStr);
                    if (dateDebut.toLocalDate().isBefore(LocalDate.now())) {
                        System.out.println("La date de début ne peut pas être antérieure à aujourd'hui. Veuillez réessayer.");
                        dateDebut = null;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Format de date invalide. Veuillez réessayer.");
                }
            }
        } while (dateDebutStr.isEmpty() && dateDebut == null);

        if (dateDebut != null) {
            offre.setDateDebut(dateDebut);
        }

        String dateFinStr;
        Date dateFin = null;
        do {
            System.out.print("Entrez la nouvelle date de fin (YYYY-MM-DD) (laisser vide pour ne pas modifier) : ");
            dateFinStr = scanner.nextLine();
            if (!dateFinStr.isEmpty()) {
                try {
                    dateFin = Date.valueOf(dateFinStr);
                    if (dateFin.toLocalDate().isBefore(dateDebut != null ? dateDebut.toLocalDate() : offre.getDateDebut().toLocalDate())) {
                        System.out.println("La date de fin ne peut pas être antérieure à la date de début. Veuillez réessayer.");
                        dateFin = null;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Format de date invalide. Veuillez réessayer.");
                }
            }
        } while (dateFinStr.isEmpty() && dateFin == null);

        if (dateFin != null) {
            offre.setDateFin(dateFin);
        }

        String typeReductionStr;
        TypeReduction typeReduction = null;
        do {
            System.out.print("Entrez le nouveau type de réduction (POURCENTAGE, MONTANT_FIXE) (laisser vide pour ne pas modifier) : ");
            typeReductionStr = scanner.nextLine();
            if (!typeReductionStr.isEmpty()) {
                try {
                    typeReduction = TypeReduction.valueOf(typeReductionStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Type de réduction invalide. Veuillez réessayer.");
                }
            }
        } while (typeReductionStr.isEmpty() && typeReduction == null);

        if (typeReduction != null) {
            offre.setTypeReduction(typeReduction);
        }

        String valeurReductionStr;
        BigDecimal valeurReduction = null;
        do {
            System.out.print("Entrez la nouvelle valeur de la réduction (laisser vide pour ne pas modifier) : ");
            valeurReductionStr = scanner.nextLine();
            if (!valeurReductionStr.isEmpty()) {
                try {
                    valeurReduction = new BigDecimal(valeurReductionStr);
                } catch (NumberFormatException e) {
                    System.out.println("Valeur de réduction invalide. Veuillez réessayer.");
                }
            }
        } while (valeurReductionStr.isEmpty() && valeurReduction == null);

        if (valeurReduction != null) {
            offre.setValeurReduction(valeurReduction);
        }

        System.out.print("Entrez les nouvelles conditions de l'offre (laisser vide pour ne pas modifier) : ");
        String conditions = scanner.nextLine();
        if (!conditions.isEmpty()) {
            offre.setConditions(conditions);
        }

        String statutOffreStr;
        StatutOffre statutOffre = null;
        do {
            System.out.print("Entrez le nouveau statut de l'offre (ACTIVE, EXPIREE) (laisser vide pour ne pas modifier) : ");
            statutOffreStr = scanner.nextLine();
            if (!statutOffreStr.isEmpty()) {
                try {
                    statutOffre = StatutOffre.valueOf(statutOffreStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Statut d'offre invalide. Veuillez réessayer.");
                }
            }
        } while (statutOffreStr.isEmpty() && statutOffre == null);

        if (statutOffre != null) {
            offre.setStatutOffre(statutOffre);
        }

        offreDAO.updateOffrePromotionnelle(offre);
        System.out.println("Offre promotionnelle mise à jour avec succès.");
    }

    public void supprimerOffre() {
        System.out.println("=== Supprimer une offre promotionnelle ===");

        System.out.print("Entrez l'ID de l'offre à supprimer : ");
        String idStr = scanner.nextLine();
        UUID id = UUID.fromString(idStr);

        offreDAO.deleteOffrePromotionnelle(id);
        System.out.println("Offre promotionnelle supprimée avec succès.");
    }
}
