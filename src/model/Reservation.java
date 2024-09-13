package model;

import java.util.UUID;



public class Reservation {
    private UUID id;
    private UUID clientId;
    private String statutReservation; // Possible values: "Reserve", "Annule", "Confirme"

    public Reservation(UUID id, UUID clientId, String statutReservation) {
        this.id = id;
        this.clientId = clientId;
        this.statutReservation = statutReservation;
    }

    // Getters and setters
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

    public String getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(String statutReservation) {
        this.statutReservation = statutReservation;
    }
}
