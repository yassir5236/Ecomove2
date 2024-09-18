package service;

import dao.ReservationDAO;
import model.Reservation;
import service.interfaces.IReservationService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ReservationService implements IReservationService {
    private final ReservationDAO reservationDAO = new ReservationDAO();

    @Override
    public void addReservation(Reservation reservation, UUID billetId) {
        reservationDAO.addReservation(reservation,billetId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    @Override
    public Optional<Map<String,Object>> getReservationById(UUID id) {
        return reservationDAO.getDetailedReservationById(id);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    @Override
    public void cancelledReservation(UUID id) {
        reservationDAO.cancelledReservation(id);
    }

    @Override
    public List<Reservation> searchReservationByStatut(String statutReservation) {
        return reservationDAO.searchReservationByStatut(statutReservation);
    }


}
