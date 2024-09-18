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

    private final Scanner scanner;

    public ReservationCommands (){
        this.scanner = new Scanner(System.in);
    }



    public void add() {
        UUID clientId;
        UUID reservationId;
        UUID billetId;
        reservationId = UUID.randomUUID(); // Générer un nouvel ID UUID pour la réservation

        try {
            System.out.print("Enter billet ID: ");
            String billetIdInput = scanner.nextLine(); // Lire l'entrée comme une chaîne
            billetId = UUID.fromString(billetIdInput); // Convertir en UUID

            System.out.print("Enter client ID: ");
            String clientIdInput = scanner.nextLine();
            clientId = UUID.fromString(clientIdInput); // Convertir en UUID

            // Vous devez vérifier que l'UUID du billet et du client existent dans la base de données
            // Par exemple :
//            if (!isBilletExists(billetId)) {
//                System.out.println("Billet ID does not exist.");
//                return;
//            }

//            if (!isClientExists(clientId)) {
//                System.out.println("Client ID does not exist.");
//                return;
//            }

            // Demander le statut de réservation
            System.out.print("Enter reservation status (Reserve, Annule, Confirme): ");
            String statutInput = scanner.nextLine();
            if (!statutInput.equals("Reserve") && !statutInput.equals("Annule") && !statutInput.equals("Confirme")) {
                System.out.println("Invalid reservation status.");
                return;
            }

            // Créer une nouvelle réservation
            Reservation reservation = new Reservation(reservationId, clientId, statutInput);

            // Appeler le service de réservation pour ajouter la réservation
            ReservationService reservationService = new ReservationService();
            reservationService.addReservation(reservation, billetId);

            System.out.println("Reservation added successfully.");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format.");
        }
    }

    // Méthodes de vérification des existences (à implémenter)
//    private boolean isBilletExists(UUID billetId) {
//        // Vérifiez dans la base de données si le billet existe
//        // Exemple d'implémentation :
//        return billetDAO.billetExists(billetId);
//    }

//    private boolean isClientExists(UUID clientId) {
//        // Vérifiez dans la base de données si le client existe
//        // Exemple d'implémentation :
//        return clientDAO.clientExists(clientId);
//    }



}
