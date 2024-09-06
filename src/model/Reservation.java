package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private UUID clientId;
    private UUID billetId;
    private LocalDateTime dateReservation; // Utilisation de LocalDateTime

    public Reservation(UUID id, UUID clientId, UUID billetId, LocalDateTime dateReservation) {
        this.id = id;
        this.clientId = clientId;
        this.billetId = billetId;
        this.dateReservation = dateReservation;
    }



    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getBilletId() {
        return billetId;
    }

    public void setBilletId(UUID billetId) {
        this.billetId = billetId;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }
}