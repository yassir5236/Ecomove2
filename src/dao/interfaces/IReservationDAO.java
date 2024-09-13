package dao.interfaces;

import model.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IReservationDAO {
    void addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Optional<Reservation> getReservationById(UUID id);
    void updateReservation(Reservation reservation);
    void deleteReservation(UUID id);
    List<Reservation> searchReservationByStatut(String statutReservation);
}
