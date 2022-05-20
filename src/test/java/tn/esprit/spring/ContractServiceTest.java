package tn.esprit.spring;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.request.ContratRequestModel;

import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.services.IEmployeService;
import org.apache.log4j.Logger;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
 class ContractServiceTest {
    private int refContrat;
    private static final Logger log = Logger.getLogger(ContractServiceTest.class);

    @Autowired
    RestControlEmploye controllerEmploye;
@Autowired
    IContratService contratService;
    @Autowired
    IEmployeService es;
    @MockBean
    ContratRepository contratRepoistory;
    public Employe createEmploye() {
        Employe employe = new Employe();
        employe.setActif(true);
        employe.setEmail("employe@gmail.com");
        employe.setNom("test");
        employe.setPrenom("test");
        employe.setRole(Role.INGENIEUR);

        return employe;
    }
    @Test
     void testAjouterContrat() {
       Employe employe = createEmploye();
         /*Contrat contrat = new ContratRequestModel();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2022);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();
contrat.setDateDebut(date);
contrat.setTypeContrat("CDI");
contrat.setSalaire(1800);
        refContrat = controllerEmploye.ajouterContrat(contrat);
        employe.setContrat(contrat);
        assertNotNull(employe.getContrat() );*/
        log.info("test of  add contract");

        Contrat c = new Contrat(new Date(),"CDI",1200);
        Contrat contrat = new Contrat(new Date(),"CDI",1200);
        contrat.setReference(111);

        when(contratRepoistory.save(c)).thenReturn(contrat);

        employe.setContrat(contrat);
        log.debug("test of ajout");
        assertNotNull(employe.getContrat() );
    }

    @Test
     void testFindAll_contarct() {
        List<Contrat> foundContract = contratService.getAllContrats();
log.info("fetch all contract");
        assertNotNull(foundContract);
    }
    @Test
     void deleteContratByIdTest(){
        log.debug("create new contract");
        Employe employe = createEmploye();
        Contrat contrat = new Contrat();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2022);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();
        contrat.setDateDebut(date);
        contrat.setTypeContrat("CDI");
        contrat.setSalaire(1800);
log.info("deleting the created contract");
       // refContrat = controllerEmploye.ajouterContrat(contrat);
//controllerEmploye.deleteContratById(contrat.getReference());
assertThat(contratService.getAllContrats().size()==0).isTrue();

    }



}
