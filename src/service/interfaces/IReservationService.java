package service.interfaces;


import model.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IReservationService {
    // Ajouter une nouvelle réservation
    void addReservation(Reservation reservation);

    // Récupérer toutes les réservations
    List<Reservation> getAllReservations();

    // Récupérer une réservation par son ID
    Optional<Reservation> getReservationById(UUID id);

    // Mettre à jour une réservation existante
    void updateReservation(Reservation reservation);

    // Supprimer une réservation par son ID
    void deleteReservation(UUID id);

    // Rechercher des réservations par statut
    List<Reservation> searchReservationByStatut(String statutReservation);
}

