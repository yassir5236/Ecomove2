package service.interfaces;


import model.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IReservationService {
    void addReservation(Reservation reservation);
    Optional<Reservation> getReservationById(UUID id);
    List<Reservation> getAllReservations();
    void updateReservation(Reservation reservation);
    boolean deleteReservation(UUID id);
    List<Reservation> getReservationsByClientId(UUID clientId);
}

