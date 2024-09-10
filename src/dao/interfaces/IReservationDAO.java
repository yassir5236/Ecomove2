package dao.interfaces;

import model.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IReservationDAO {
    boolean addReservation(Reservation reservation);
    Optional<Reservation> findReservationById(UUID reservationId);
    List<Reservation> getAllReservations();
    boolean updateReservation(Reservation reservation);
    boolean deleteReservation(UUID reservationId);
    List<Reservation> findReservationsByClientId(UUID clientId);
}
