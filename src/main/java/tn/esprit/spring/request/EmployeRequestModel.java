package tn.esprit.spring.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.entities.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
public class EmployeRequestModel {

    private String prenom;

    private String nom;

    private String email;

    private boolean isActif;

    @Enumerated(EnumType.STRING)
    private Role role;
}
