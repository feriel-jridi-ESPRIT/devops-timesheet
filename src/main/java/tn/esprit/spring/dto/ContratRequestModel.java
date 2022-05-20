package tn.esprit.spring.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ContratRequestModel {

    private Date dateDebut;

    private String typeContrat;

    private float salaire;

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getTypeContrat() {
        return typeContrat;
    }


    public float getSalaire() {
        return salaire;
    }


    public ContratRequestModel(Date dateDebut, String typeContrat, float salaire) {
        super();
        this.dateDebut = dateDebut;
        this.typeContrat = typeContrat;
        this.salaire = salaire;
    }

    public ContratRequestModel() {
        super();
    }


}
