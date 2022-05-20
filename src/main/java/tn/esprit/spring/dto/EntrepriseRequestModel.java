package tn.esprit.spring.dto;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseRequestModel {

    /**
     *
     */
    private static final long serialVersionUID = 3152690779535828408L;

    private int id;
    private String name;
    private String raisonSocial;
    private List<Departement> departements = new ArrayList<>();



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public List<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }

public Entreprise convert() {
        return new Entreprise(name, raisonSocial);
}
}
