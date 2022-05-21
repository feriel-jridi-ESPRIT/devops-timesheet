package tn.esprit.spring.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
@SpringBootTest
public class EmployeServiceImplTest {
    private static final Logger LOG = LogManager.getLogger(EmployeServiceImplTest.class);
    EmployeServiceImplTest emplMock = mock(EmployeServiceImplTest.class);

    @Autowired
    IEmployeService iempServ;
    @Autowired
    IEntrepriseService ientSer;
    @MockBean
    EmployeRepository empRepoistory;
    @Autowired
    ContratRepository contratRepoistory;
    private Departement departement;
    private Contrat contrat;
    private Employe employe;
    public Employe createEmploye(){
        employe = new Employe();
       employe.setPrenom("Feriel");
       employe.setNom("Jridi");
       employe.setEmail("Feriel.jridi@esprit.tn");
       employe.setPassword("pass");
       employe.setActif(true);
       employe.setRole(Role.INGENIEUR);
       employe.setId(iempServ.addOrUpdateEmploye(employe));
       return employe;
    }

    public Departement createDepartement(){
        departement = new Departement();
        departement.setName("Operation");
        return departement;
    }

    public Contrat createContrat(){
        contrat = new Contrat();
        return contrat;
    }

    @Test
    public void addOrUpdateEmployeeTest(){
        LOG.info("Start Method addOrUpdateEmployeeTest");
    Employe e = createEmploye();
    assertTrue(e.getId() == iempServ.addOrUpdateEmploye(employe));
        LOG.info("Done for Method addOrUpdateEmployeeTest");
    }

    @Test
    public void getAllEmployeesTest(){
        LOG.info("Start Method getAllEmployeesTest");
        List<Employe> employees = new ArrayList<>();
        assertEquals(iempServ.getAllEmployes(),employees);
    }




}
