/*

package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Billet {
    private UUID id;
    private UUID partenaireId;  // Nouvel attribut pour stocker l'identifiant du partenaire
    private TypeTransport typeTransport;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private Date dateVente;
    private StatutBillet statutBillet;

    // Constructeur
    public Billet(UUID id, UUID partenaireId, TypeTransport typeTransport, BigDecimal prixAchat, BigDecimal prixVente, Date dateVente, StatutBillet statutBillet) {
        this.id = id;
        this.partenaireId = partenaireId;  // Initialisation du nouvel attribut
        this.typeTransport = typeTransport;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.dateVente = dateVente;
        this.statutBillet = statutBillet;
    }

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPartenaireId() {
        return partenaireId;
    }

    public void setPartenaireId(UUID partenaireId) {
        this.partenaireId = partenaireId;
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
}


 */


package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

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
