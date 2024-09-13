package service;

import dao.ReservationDAO;
import model.Reservation;
import service.interfaces.IReservationService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationService implements IReservationService {
    private final ReservationDAO reservationDAO = new ReservationDAO();

    @Override
    public void addReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    @Override
    public Optional<Reservation> getReservationById(UUID id) {
        return reservationDAO.getReservationById(id);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    @Override
    public void deleteReservation(UUID id) {
        reservationDAO.deleteReservation(id);
    }

    @Override
    public List<Reservation> searchReservationByStatut(String statutReservation) {
        return reservationDAO.searchReservationByStatut(statutReservation);
    }
}
