

package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
/*
public class Billet {
    private UUID id;
    private UUID contratId;
    private TypeTransport typeTransport;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private Date dateVente;
    private StatutBillet statutBillet;

    public Billet(UUID id, UUID contratId, TypeTransport typeTransport, BigDecimal prixAchat, BigDecimal prixVente, Date dateVente, StatutBillet statutBillet) {
        this.id = id;
        this.contratId = contratId;
        this.typeTransport = typeTransport;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.dateVente = dateVente;
        this.statutBillet = statutBillet;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getContratId() {
        return contratId;
    }

    public void setContratId(UUID contratId) {
        this.contratId = contratId;
    }

    public TypeTransport getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(TypeTransport typeTransport) {
        this.typeTransport = typeTransport;
    }

    public BigDecimal getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }

    public BigDecimal getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(BigDecimal prixVente) {
        this.prixVente = prixVente;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public StatutBillet getStatutBillet() {
        return statutBillet;
    }

    public void setStatutBillet(StatutBillet statutBillet) {
        this.statutBillet = statutBillet;
    }


    @Override
    public String toString() {
        return "Billet{" +
                "id=" + id +
                ", contratId=" + contratId +
                ", typeTransport=" + typeTransport +
                ", prixAchat=" + prixAchat +
                ", prixVente=" + prixVente +
                ", dateVente=" + dateVente +
                ", statutBillet=" + statutBillet +
                '}';
    }

}

 */



import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;




public class Billet {
    private UUID id;
    private UUID contratId;
    private TypeTransport typeTransport;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private LocalDate dateVente;
    private StatutBillet statutBillet;
    private String villeDepart;        // Nouvelle colonne
    private String villeDestination;   // Nouvelle colonne
    private LocalDate dateDepart;      // Nouvelle colonne
    private LocalTime horaire;         // Nouvelle colonne
    private Duration duree;            // Nouvelle colonne

    // Constructeur
    public Billet(UUID id, UUID contratId, TypeTransport typeTransport, BigDecimal prixAchat, BigDecimal prixVente, LocalDate dateVente, StatutBillet statutBillet,
                  String villeDepart, String villeDestination, LocalDate dateDepart, LocalTime horaire, Duration duree) {
        this.id = id;
        this.contratId = contratId;
        this.typeTransport = typeTransport;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.dateVente = dateVente;
        this.statutBillet = statutBillet;
        this.villeDepart = villeDepart;
        this.villeDestination = villeDestination;
        this.dateDepart = dateDepart;
        this.horaire = horaire;
        this.duree = duree;
    }

    // Getters et Setters pour les nouveaux attributs
    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleDestination() {
        return villeDestination;
    }

    public void setVilleDestination(String villeDestination) {
        this.villeDestination = villeDestination;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalTime getHoraire() {
        return horaire;
    }

    public void setHoraire(LocalTime horaire) {
        this.horaire = horaire;
    }

    public Duration getDuree() {
        return duree;
    }

    public void setDuree(Duration duree) {
        this.duree = duree;
    }

    // Méthode toString mise à jour
    @Override
    public String toString() {
        return "Billet{" +
                "id=" + id +
                ", contratId=" + contratId +
                ", typeTransport=" + typeTransport +
                ", prixAchat=" + prixAchat +
                ", prixVente=" + prixVente +
                ", dateVente=" + dateVente +
                ", statutBillet=" + statutBillet +
                ", villeDepart='" + villeDepart + '\'' +
                ", villeDestination='" + villeDestination + '\'' +
                ", dateDepart=" + dateDepart +
                ", horaire=" + horaire +
                ", duree=" + duree +
                '}';
    }
}




