package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	private static final Logger log = Logger.getLogger(EmployeServiceImpl.class);

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	@Override
	public Employe authenticate(String login, String password) {
		return employeRepository.getEmployeByEmailAndPassword(login, password);
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Optional<Employe> employeOpt = employeRepository.findById(employeId);
if (employeOpt.isPresent()){
		Employe employe = employeOpt.get();
		employe.setEmail(email);
		employeRepository.save(employe);}

	}

	@Transactional
	public void affecterEmployeADepartement(int employeId, int depId) {
		Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		if (depManagedEntity != null && employeManagedEntity != null) {
			List<Employe> employes = depManagedEntity.getEmployes();
			if(employes == null){

				employes = new ArrayList<>();
				employes.add(employeManagedEntity);
				depManagedEntity.setEmployes(employes);
			}else{

				depManagedEntity.getEmployes().add(employeManagedEntity);

			}
		}

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		log.info("Désaffecter un employé du département");
		Optional<Departement> dep = deptRepoistory.findById(depId);

		if (dep.isPresent()) {
			int employeNb = dep.get().getEmployes().size();
			for (int index = 0; index < employeNb; index++) {
				if (dep.get().getEmployes().get(index).getId() == employeId) {
					log.info("L'id d'employé fournit doit être existant");
					dep.get().getEmployes().remove(index);
					break;// a revoir
				}
			}
			log.info("La méthode desaffecterEmployeDuDepartement est terminée avec succés");
		} else {
			log.error("Departement inexistant");
		}
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		log.info("Lancement de la méthode affecterContratAEmploye");
		Optional<Contrat> contratManagedEntityOpt = contratRepoistory.findById(contratId);
		Optional<Employe> employeManagedEntityOpt = employeRepository.findById(employeId);

		if (contratManagedEntityOpt.isPresent()) {
			if (employeManagedEntityOpt.isPresent()) {
				Contrat contrat = contratManagedEntityOpt.get();
				Employe employe = employeManagedEntityOpt.get();
				contrat.setEmploye(employe);
				contratRepoistory.save(contrat);
				log.info("La méthode affecterContratAEmploye est términé avec succés");
			} else {
				log.error("Employe inexistant");
			}
		} else {
			log.error("Contrat inexistant");
		}

	}

	public String getEmployePrenomById(int employeId) {
		try {
			Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
			if (employeManagedEntity.isPresent()) {
				return employeManagedEntity.get().getPrenom();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	 
	public void deleteEmployeById(int employeId)
	{
		try {
			Optional<Employe> employeOpt = employeRepository.findById(employeId);

			// Desaffecter l'employe de tous les departements
			// c'est le bout master qui permet de mettre a jour
			// la table d'association
			if (employeOpt.isPresent()) {
				Employe employe = employeOpt.get();
				for (Departement dep : employe.getDepartements()) {
					dep.getEmployes().remove(employe);
				}
				employeRepository.delete(employe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteContratById(int contratId) {
		try {
			Optional<Contrat> contratManagedEntityOpt = contratRepoistory.findById(contratId);
			if (contratManagedEntityOpt.isPresent()) {
				contratRepoistory.delete(contratManagedEntityOpt.get());
			}
		} catch (Exception e) {
e.printStackTrace();		}


	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}
