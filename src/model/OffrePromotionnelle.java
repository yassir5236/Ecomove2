/*package model;

import java.math.BigDecimal;
import java.sql.Date; // Import pour java.sql.Date
import java.util.UUID;

public class OffrePromotionnelle {
    private UUID id;
    private Date dateDebut;
    private Date dateFin;
    private BigDecimal tarifSpecial;
    private String conditionsAccord;
    private boolean renouvelable;
    private StatutContrat statutContrat;

    // Constructeur pour création de nouvelle offre
    public OffrePromotionnelle(Date dateDebut, Date dateFin, BigDecimal tarifSpecial,
                               String conditionsAccord, boolean renouvelable, StatutContrat statutContrat) {
        this.id = UUID.randomUUID(); // ID généré automatiquement
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tarifSpecial = tarifSpecial;
        this.conditionsAccord = conditionsAccord;
        this.renouvelable = renouvelable;
        this.statutContrat = statutContrat;
    }

    // Constructeur pour lecture depuis la base de données
    public OffrePromotionnelle(UUID id, Date dateDebut, Date dateFin, BigDecimal tarifSpecial,
                               String conditionsAccord, boolean renouvelable, StatutContrat statutContrat) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tarifSpecial = tarifSpecial;
        this.conditionsAccord = conditionsAccord;
        this.renouvelable = renouvelable;
        this.statutContrat = statutContrat;
    }

    // Getters et Setters
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
}*/


package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public class OffrePromotionnelle {
    private UUID id;
    private String nomOffre;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private TypeReduction typeReduction;
    private BigDecimal valeurReduction;
    private String conditions;
    private StatutOffre statutOffre;
    private UUID contratId;

    public OffrePromotionnelle() {
    }

    public OffrePromotionnelle(UUID id, String nomOffre, String description, Date dateDebut, Date dateFin,
                               TypeReduction typeReduction, BigDecimal valeurReduction, String conditions,
                               StatutOffre statutOffre, UUID contratId) {
        this.id = id;
        this.nomOffre = nomOffre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeReduction = typeReduction;
        this.valeurReduction = valeurReduction;
        this.conditions = conditions;
        this.statutOffre = statutOffre;
        this.contratId = contratId;
    }

    // Getters et Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomOffre() {
        return nomOffre;
    }

    public void setNomOffre(String nomOffre) {
        this.nomOffre = nomOffre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public TypeReduction getTypeReduction() {
        return typeReduction;
    }

    public void setTypeReduction(TypeReduction typeReduction) {
        this.typeReduction = typeReduction;
    }

    public BigDecimal getValeurReduction() {
        return valeurReduction;
    }

    public void setValeurReduction(BigDecimal valeurReduction) {
        this.valeurReduction = valeurReduction;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public StatutOffre getStatutOffre() {
        return statutOffre;
    }

    public void setStatutOffre(StatutOffre statutOffre) {
        this.statutOffre = statutOffre;
    }

    public UUID getContratId() {
        return contratId;
    }

    public void setContratId(UUID contratId) {
        this.contratId = contratId;
    }

    // Méthode toString() pour un affichage lisible
    @Override
    public String toString() {
        return "OffrePromotionnelle{" +
                "id=" + id +
                ", nomOffre='" + nomOffre + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", typeReduction=" + typeReduction +
                ", valeurReduction=" + valeurReduction +
                ", conditions='" + conditions + '\'' +
                ", statutOffre=" + statutOffre +
                ", contratId=" + contratId +
                '}';
    }
}

