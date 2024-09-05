package model;

import java.util.Date;
import java.util.UUID;

public class Partenaire {
    private UUID id;
    private String nomCompagnie;
    private String contactCommercial;
    private TypeTransport typeTransport;
    private String zoneGeographique;
    private String conditionsSpeciales;
    private StatutPartenaire statutPartenaire;
    private Date dateCreation;

    public Partenaire(UUID id, String nomCompagnie, String contactCommercial, TypeTransport typeTransport, String zoneGeographique, String conditionsSpeciales, StatutPartenaire statutPartenaire, Date dateCreation) {
        this.id = id;
        this.nomCompagnie = nomCompagnie;
        this.contactCommercial = contactCommercial;
        this.typeTransport = typeTransport;
        this.zoneGeographique = zoneGeographique;
        this.conditionsSpeciales = conditionsSpeciales;
        this.statutPartenaire = statutPartenaire;
        this.dateCreation = dateCreation;
    }

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomCompagnie() {
        return nomCompagnie;
    }

    public void setNomCompagnie(String nomCompagnie) {
        this.nomCompagnie = nomCompagnie;
    }

    public String getContactCommercial() {
        return contactCommercial;
    }

    public void setContactCommercial(String contactCommercial) {
        this.contactCommercial = contactCommercial;
    }

    public TypeTransport getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(TypeTransport typeTransport) {
        this.typeTransport = typeTransport;
    }

    public String getZoneGeographique() {
        return zoneGeographique;
    }

    public void setZoneGeographique(String zoneGeographique) {
        this.zoneGeographique = zoneGeographique;
    }

    public String getConditionsSpeciales() {
        return conditionsSpeciales;
    }

    public void setConditionsSpeciales(String conditionsSpeciales) {
        this.conditionsSpeciales = conditionsSpeciales;
    }

    public StatutPartenaire getStatutPartenaire() {
        return statutPartenaire;
    }

    public void setStatutPartenaire(StatutPartenaire statutPartenaire) {
        this.statutPartenaire = statutPartenaire;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Partenaire {" +
                "ID: " + id +
                ", Nom Compagnie: " + nomCompagnie +
                ", Contact Commercial: " + contactCommercial +
                ", Type de Transport: " + typeTransport +
                ", Zone Géographique: " + zoneGeographique +
                ", Conditions Spéciales: " + conditionsSpeciales +
                ", Statut Partenaire: " + statutPartenaire +
                ", Date de Création: " + dateCreation +
                '}';
    }

}
