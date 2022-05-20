package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	private static final Logger log = Logger.getLogger(EntrepriseServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		try {
			log.debug("Lancement de la méthode affecterDepartementAEntreprise");
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
			if (entrepriseManagedEntity==null) {
				log.error("Entreprise does not exist");
				return;}
			Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
			if (depManagedEntity==null) {
				log.error("Departement does not exist");
				return;}
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(depManagedEntity);
		} catch (Exception e) {
			log.error("Erreur dans la méthode affecterDepartementAEntreprise():" + e);
		} finally {

			log.info("La méthode affecterDepartementAEntreprise est términé avec succés");
		}
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		List<String> depNames = new ArrayList<>();
		if(entrepriseManagedEntity != null) {
			for (Departement dep : entrepriseManagedEntity.getDepartements()) {
				depNames.add(dep.getName());
			}
		}
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).orElseThrow(() ->new IllegalArgumentException("id not found")));
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).orElseThrow(() ->new IllegalArgumentException("id not found")));
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepoistory.findById(entrepriseId).orElse(null);
	}

}
