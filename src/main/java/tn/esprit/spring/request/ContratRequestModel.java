package tn.esprit.spring.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ContratRequestModel {

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    private String typeContrat;

    private float salaire;
}
