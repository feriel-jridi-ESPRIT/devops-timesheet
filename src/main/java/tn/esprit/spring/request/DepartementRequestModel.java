package tn.esprit.spring.request;

import tn.esprit.spring.entities.Departement;
;
import tn.esprit.spring.entities.Mission;

import java.util.List;

public class DepartementRequestModel {
    private static final long serialVersionUID = -357738161698377833L;

    private int id;
    private String name;
    private List<EmployeRequestModel> employes;
    private List<Mission> missions;
    private EntrepriseRequestModel entreprise;


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

    public List<EmployeRequestModel> getEmployes() {
        return employes;
    }

    public void setEmployes(List<EmployeRequestModel> employes) {
        this.employes = employes;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public EntrepriseRequestModel getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(EntrepriseRequestModel entreprise) {
        this.entreprise = entreprise;
    }

    public Departement convert(){
        return new Departement(name);
    }
}
