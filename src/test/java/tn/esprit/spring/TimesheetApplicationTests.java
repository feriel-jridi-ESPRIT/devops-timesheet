package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.ITimesheetService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@SpringBootTest
class TimesheetApplicationTests {
	@Autowired
	ITimesheetService timeSheetService ;
	private  int idMission = 1;
	private final int idEmploye=1;
	 final int idDepartment=1;
	 int idvalidator ;


	@Autowired
	IEmployeService employeService;

	@Autowired
	MissionRepository missionRepository;

	@Autowired
	TimesheetRepository timesheetRepository;

	public Mission createMission(){
		Mission mission = new Mission();
		mission.setName("This is the mission name");
		mission.setId(idMission);
		mission.setDescription("This is the descripton ");
		return mission;
	}

	@Test
     void testAjouterMission(){
		log.info("test add mission");
		Mission mission = createMission();
		idMission = timeSheetService.ajouterMission(mission);
		assertEquals(mission.getId(), idMission);
		log.info("method testAjouterEmploye ends here");
	}


	@Test
	  void testValiderTimesheet(){
		log.info("testValiderTimesheet begins");
		Calendar calendar =  new GregorianCalendar(2020,Calendar.FEBRUARY,1);
		Calendar calendar2 =  new GregorianCalendar(2021,Calendar.FEBRUARY,1);
		Date date =  calendar.getTime();
		Date date2 =  calendar2.getTime();
		Employe validator = new Employe("valdNom", "validatorP","validator@islem.com",true,Role.CHEF_DEPARTEMENT);
		Departement dept = new Departement("dept1");
		List<Departement> depts = new ArrayList<>();
		depts.add(dept);
		validator.setDepartements(depts);
		idvalidator = employeService.addOrUpdateEmploye(validator);
		timeSheetService.validerTimesheet(idMission,idEmploye,date,date2,idvalidator);
		log.info("testValiderTimesheet ends");
	}

}
