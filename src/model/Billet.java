package model;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;



public class Billet {
    private UUID id;
    private TypeTransport typeTransport;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private Timestamp dateVente;
    private StatutBillet statutBillet;
    private UUID contratId;
    private int trajetId;
    private java.sql.Date dateDepart;
    private java.sql.Time horaire;

    // Nouveaux attributs
    private BigDecimal duree;
    private String nomCompagnie;
    private String villeDepart;
    private String villeDestination;

    // Constructeur complet
//    public Billet(UUID id, TypeTransport typeTransport, BigDecimal prixAchat, BigDecimal prixVente,
//                  Timestamp dateVente, StatutBillet statutBillet, UUID contratId, int trajetId,
//                  java.sql.Date dateDepart, java.sql.Time horaire) {
//        this.id = id;
//        this.typeTransport = typeTransport;
//        this.prixAchat = prixAchat;
//        this.prixVente = prixVente;
//        this.dateVente = dateVente;
//        this.statutBillet = statutBillet;
//        this.contratId = contratId;
//        this.trajetId = trajetId;
//        this.dateDepart = dateDepart;
//        this.horaire = horaire;
//    }


    public Billet(UUID id, TypeTransport typeTransport, BigDecimal prixAchat, BigDecimal prixVente,
                  Timestamp dateVente, StatutBillet statutBillet, UUID contratId, int trajetId,
                  java.sql.Date dateDepart, java.sql.Time horaire, BigDecimal duree,
                  String nomCompagnie, String villeDepart, String villeDestination) {
        this.id = id;
        this.typeTransport = typeTransport;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.dateVente = dateVente;
        this.statutBillet = statutBillet;
        this.contratId = contratId;
        this.trajetId = trajetId;
        this.dateDepart = dateDepart;
        this.horaire = horaire;
        this.duree = duree;
        this.nomCompagnie = nomCompagnie;
        this.villeDepart = villeDepart;
        this.villeDestination = villeDestination;
    }

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Timestamp getDateVente() {
        return dateVente;
    }

    public void setDateVente(Timestamp dateVente) {
        this.dateVente = dateVente;
    }

    public StatutBillet getStatutBillet() {
        return statutBillet;
    }

    public void setStatutBillet(StatutBillet statutBillet) {
        this.statutBillet = statutBillet;
    }

    public UUID getContratId() {
        return contratId;
    }

    public void setContratId(UUID contratId) {
        this.contratId = contratId;
    }

    public int getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(int trajetId) {
        this.trajetId = trajetId;
    }

    public java.sql.Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(java.sql.Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public java.sql.Time getHoraire() {
        return horaire;
    }

    public void setHoraire(java.sql.Time horaire) {
        this.horaire = horaire;
    }

    public BigDecimal getDuree() {
        return duree;
    }

    public void setDuree(BigDecimal duree) {
        this.duree = duree;
    }

    public String getNomCompagnie() {
        return nomCompagnie;
    }

    public void setNomCompagnie(String nomCompagnie) {
        this.nomCompagnie = nomCompagnie;
    }

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

    // Méthode toString pour afficher les informations du billet
    @Override
    public String toString() {
        return "Billet{" +
                "id=" + id +
                ", typeTransport=" + typeTransport +
                ", prixAchat=" + prixAchat +
                ", prixVente=" + prixVente +
                ", dateVente=" + dateVente +
                ", statutBillet=" + statutBillet +
                ", contratId=" + contratId +
                ", trajetId=" + trajetId +
                ", dateDepart=" + dateDepart +
                ", horaire=" + horaire +
                '}';
    }
}
