package console.commands;

import model.Reservation;
import model.Billet;
import service.ReservationService;
import service.BilletService;
import java.time.LocalDateTime;

import java.util.*;

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
            System.out.print("Enter billet ID for reservation : ");

            String billetIdInput = scanner.nextLine();
            billetId = UUID.fromString(billetIdInput);

            System.out.print("Enter client ID for the person who going to make the reservation : ");
            String clientIdInput = scanner.nextLine();
            clientId = UUID.fromString(clientIdInput);



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


   public boolean  displayReservation() {
       UUID id;
       System.out.println("Enter client id ");
       String idInput = scanner.nextLine();
       id = UUID.fromString(idInput);
       ReservationService reservationService = new ReservationService();

       Optional<Map<String, Object>> reservationOpt = reservationService.getReservationById(id);

       if (reservationOpt.isPresent()) {
           Map<String, Object> reservation = reservationOpt.get();
           System.out.println("==============================");
           System.out.println(" Détails de la Réservation ");
           System.out.println("==============================");
           System.out.println("Reservation ID  : " + reservation.get("reservation_id"));

           System.out.println("Nom du client : " + reservation.get("client_nom"));
           System.out.println("ID du billet : " + reservation.get("billet_id"));
           System.out.println("Date de départ : " + reservation.get("date_depart"));
           System.out.println("Statut de la réservation : " + reservation.get("statut_reservation"));
           System.out.println("Ville de départ : " + reservation.get("ville_depart"));
           System.out.println("Ville de destination : " + reservation.get("ville_destination"));
           System.out.println("Durée du trajet : " + reservation.get("duree_trajet") + " heures");
           System.out.println("==============================");
           return true;
       } else {
           System.out.println("Aucune réservation trouvée pour cet ID.");
           return false;
       }

   }


       public void cancelledReservation() {
           System.out.println("Do you want to cancelled  the reservation? (yes/no)");

           String response = scanner.nextLine().trim().toLowerCase();

           if ("yes".equals(response)) {
               System.out.println("Enter the reservation ID (UUID format): ");
               String idInput = scanner.nextLine().trim();

               try {
                   UUID id = UUID.fromString(idInput);
                   ReservationService reservationService = new ReservationService();
                   reservationService.cancelledReservation(id);
//                   System.out.println("Reservation deleted successfully.");
               } catch (IllegalArgumentException e) {
                   System.out.println("Invalid UUID format. Please enter a valid UUID.");
               }
           } else {
               System.out.println("Operation cancelled.");
           }
       }


   }







