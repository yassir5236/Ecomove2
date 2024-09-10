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
    public Optional<Reservation> getReservationById(UUID id) {
        return reservationDAO.findReservationById(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    @Override
    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    @Override
    public boolean deleteReservation(UUID id) {
        return reservationDAO.deleteReservation(id);
    }

    @Override
    public List<Reservation> getReservationsByClientId(UUID clientId) {
        return reservationDAO.findReservationsByClientId(clientId);
    }
}
