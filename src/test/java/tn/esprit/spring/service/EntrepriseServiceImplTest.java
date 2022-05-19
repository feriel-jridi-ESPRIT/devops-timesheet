package tn.esprit.spring.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.controller.RestControlEntreprise;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IEntrepriseService;
import org.apache.log4j.Logger;


@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EntrepriseServiceImplTest {

    private int idDepartement;

    @Mock
    private EntrepriseRepository entrepriseRepository;
    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    EntrepriseServiceImpl entrepriseService;

    public Departement createDepartement() {
        Departement departement = new Departement();
        departement.setName("Rnd");
        return departement;
    }

        @Test
    public void testAjouterDepartement() {
        Departement departement = createDepartement();
        departement.setId(5001);
        idDepartement = entrepriseService.ajouterDepartement(departement);
        assertNotNull(idDepartement);
            log.info("fin de la méthode testAjouterDepartement()");
    }

    @Test
    public void testGetAllDepartementsNamesByEntreprise(){
        log.info("test GetAllDepartementsNamesByEntreprise");
        Entreprise returnedEnterprise = new Entreprise("Be-softilys","041548785588");
        List<Departement> departments = Arrays.asList(new Departement("Edition logiciel"),
                new Departement("Assurance"),new Departement("Service client"));
        returnedEnterprise.setDepartements(departments);
        Optional<Entreprise> entreprise = Optional.of(returnedEnterprise);

        when(entrepriseRepository.findById(1)).thenReturn(entreprise);
        List<String> listDepsByEnterprise = entrepriseService.getAllDepartementsNamesByEntreprise(1);
        assertThat(listDepsByEnterprise).containsExactly("Edition logiciel", "Assurance", "Service client");
        assertThat(listDepsByEnterprise.size()).isEqualTo(3);
        log.info("fin de la méthode GetAllDepartementsNamesByEntreprise");
    }

    @Test
    public void testDeleteDepartement() {
        log.info("test delete Departement");
        Departement departement = createDepartement();
        departement.setId(5002);
        idDepartement = departement.getId();
        entrepriseService.deleteDepartementById(idDepartement);
        verify(departementRepository,times(1)).findById(idDepartement);
        log.info("fin de la méthode testDeleteDepartement()");
    }

}
