package console.commands;

import model.Reservation;
import model.Billet;
import service.ReservationService;
import service.BilletService;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ReservationCommands {
    private final ReservationService reservationService = new ReservationService();
    private final BilletService billetService = new BilletService();

    public void searchTickets(Scanner scanner) {
        System.out.println("Entrez la ville de départ :");
        String villeDepart = scanner.nextLine();

        System.out.println("Entrez la ville de destination :");
        String villeDestination = scanner.nextLine();

        System.out.println("Entrez la date de départ (format YYYY-MM-DD) :");
        String dateDepart = scanner.nextLine();

        List<Billet> billets = billetService.searchBillets(villeDepart, villeDestination, dateDepart);

        if (billets.isEmpty()) {
            System.out.println("Aucun billet trouvé pour les critères de recherche.");  // User Story 9
        } else {
            for (Billet billet : billets) {
                System.out.println(billet);
            }
            reserveTicket(scanner, billets);  // User Story 5
        }
    }

    public void reserveTicket(Scanner scanner, List<Billet> billets) {
        System.out.println("Entrez l'ID du billet que vous souhaitez réserver :");
        UUID billetId = UUID.fromString(scanner.nextLine());

        System.out.println("Entrez l'ID de votre client :");
        UUID clientId = UUID.fromString(scanner.nextLine());

        Reservation reservation = new Reservation(UUID.randomUUID(), clientId, billetId, LocalDateTime.now());
        reservationService.addReservation(reservation);

        System.out.println("Réservation confirmée avec succès !");  // User Story 5
    }

    public void viewReservations(Scanner scanner) {
        System.out.println("Entrez l'ID du client pour consulter ses réservations :");
        UUID clientId = UUID.fromString(scanner.nextLine());

        List<Reservation> reservations = reservationService.getReservationsByClientId(clientId);

        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation trouvée.");  // User Story 6
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    public void cancelReservation(Scanner scanner) {
        System.out.println("Entrez l'ID de la réservation à annuler :");
        UUID reservationId = UUID.fromString(scanner.nextLine());

        boolean success = reservationService.deleteReservation(reservationId);
        if (success) {
            System.out.println("Réservation annulée avec succès !");  // User Story 7
        } else {
            System.out.println("Erreur lors de l'annulation de la réservation.");
        }
    }

}
