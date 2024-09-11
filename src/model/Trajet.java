package model;

public class Trajet {
    private int id;
    private int villeDepartId;
    private int villeDestinationId;
    private double duree; // Durée en heures

    // Constructeur avec ID
    public Trajet(int id, int villeDepartId, int villeDestinationId, double duree) {
        this.id = id;
        this.villeDepartId = villeDepartId;
        this.villeDestinationId = villeDestinationId;
        this.duree = duree;
    }

    // Constructeur sans ID
    public Trajet(int villeDepartId, int villeDestinationId, double duree) {
        this.villeDepartId = villeDepartId;
        this.villeDestinationId = villeDestinationId;
        this.duree = duree;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVilleDepartId() {
        return villeDepartId;
    }

    public void setVilleDepartId(int villeDepartId) {
        this.villeDepartId = villeDepartId;
    }

    public int getVilleDestinationId() {
        return villeDestinationId;
    }

    public void setVilleDestinationId(int villeDestinationId) {
        this.villeDestinationId = villeDestinationId;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    // Méthode toString pour afficher les informations du trajet
    @Override
    public String toString() {
        return "Trajet{" +
                "id=" + id +
                ", villeDepartId=" + villeDepartId +
                ", villeDestinationId=" + villeDestinationId +
                ", duree=" + duree +
                '}';
    }
}
