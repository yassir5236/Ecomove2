package model;

import java.math.BigDecimal;
import java.sql.Date; // Import pour java.sql.Date
import java.util.UUID;

public class Contrat {
    private UUID id;
    private Date dateDebut;
    private Date dateFin;
    private BigDecimal tarifSpecial;
    private String conditionsAccord;
    private boolean renouvelable;
    private StatutContrat statutContrat;
    private UUID partenaireId;

    public Contrat(Date dateDebut, Date dateFin, BigDecimal tarifSpecial,
                   String conditionsAccord, boolean renouvelable, StatutContrat statutContrat) {
        this.id = UUID.randomUUID();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tarifSpecial = tarifSpecial;
        this.conditionsAccord = conditionsAccord;
        this.renouvelable = renouvelable;
        this.statutContrat = statutContrat;
    }

    public Contrat(UUID id, Date dateDebut, Date dateFin, BigDecimal tarifSpecial,
                   String conditionsAccord, boolean renouvelable, StatutContrat statutContrat) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tarifSpecial = tarifSpecial;
        this.conditionsAccord = conditionsAccord;
        this.renouvelable = renouvelable;
        this.statutContrat = statutContrat;
        this.partenaireId = partenaireId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getTarifSpecial() {
        return tarifSpecial;
    }

    public void setTarifSpecial(BigDecimal tarifSpecial) {
        this.tarifSpecial = tarifSpecial;
    }

    public String getConditionsAccord() {
        return conditionsAccord;
    }

    public void setConditionsAccord(String conditionsAccord) {
        this.conditionsAccord = conditionsAccord;
    }

    public boolean isRenouvelable() {
        return renouvelable;
    }

    public void setRenouvelable(boolean renouvelable) {
        this.renouvelable = renouvelable;
    }

    public StatutContrat getStatutContrat() {
        return statutContrat;
    }

    public void setStatutContrat(StatutContrat statutContrat) {
        this.statutContrat = statutContrat;
    }

    public UUID getPartenaireId() {
        return partenaireId;
    }

    public void setPartenaireId(UUID partenaireId) {
        this.partenaireId = partenaireId;
    }

    @Override
    public String toString() {
        return "Contrat {" +
                "ID = " + id +
                ", Date de début = " + dateDebut +
                ", Date de fin = " + (dateFin != null ? dateFin : "Indéfinie") +
                ", Tarif spécial = " + tarifSpecial +
                ", Conditions de l'accord = '" + conditionsAccord + '\'' +
                ", Renouvelable = " + (renouvelable ? "Oui" : "Non") +
                ", Statut du contrat = " + statutContrat +
                ", PartenaireID = " + partenaireId +
                '}';
    }


}
