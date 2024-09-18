package dao.interfaces;

import model.Reservation;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface IReservationDAO {
    void addReservation(Reservation reservation,UUID billetId);
    List<Reservation> getAllReservations();
    Optional<Map<String, Object>> getDetailedReservationById(UUID id);
    void updateReservation(Reservation reservation);
    void deleteReservation(UUID id);
    List<Reservation> searchReservationByStatut(String statutReservation);
}
