package tn.esprit.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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

@Slf4j
@RunWith(SpringRunner.class)
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
    }

    @Test
    public void testGetAllDepartementsNamesByEntreprise(){
        Entreprise returnedEnterprise = new Entreprise("Be-softilys","041548785588");
        List<Departement> departments = Arrays.asList(new Departement("Edition logiciel"),
                new Departement("Assurance"),new Departement("Service client"));
        returnedEnterprise.setDepartements(departments);
        Optional<Entreprise> entreprise = Optional.of(returnedEnterprise);

        when(entrepriseRepository.findById(1)).thenReturn(entreprise);
        List<String> listDepsByEnterprise = entrepriseService.getAllDepartementsNamesByEntreprise(1);
        assertThat(listDepsByEnterprise).containsExactly("Edition logiciel", "Assurance", "Service client");
        assertThat(listDepsByEnterprise.size()).isEqualTo(3);
    }

    @Test
    public void testDeleteDepartement() {
        Departement departement = createDepartement();
        departement.setId(5002);
        idDepartement = departement.getId();
        entrepriseService.deleteDepartementById(idDepartement);
        verify(departementRepository,times(1)).findById(idDepartement);
    }

}
