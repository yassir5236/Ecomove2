package service;

import dao.ReservationDAO;
import model.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationService {
    private final ReservationDAO reservationDAO = new ReservationDAO();

    // Ajouter une nouvelle réservation
    public void addReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
    }

    // Trouver une réservation par son ID
    public Optional<Reservation> getReservationById(UUID id) {
        return reservationDAO.findReservationById(id);
    }

    // Obtenir toutes les réservations
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    // Mettre à jour une réservation
    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    // Supprimer une réservation par son ID
    public boolean deleteReservation(UUID id) {
        return reservationDAO.deleteReservation(id);
    }

    // Ajouter la méthode getReservationsByClientId
    public List<Reservation> getReservationsByClientId(UUID clientId) {
        return reservationDAO.findReservationsByClientId(clientId);
    }
}
