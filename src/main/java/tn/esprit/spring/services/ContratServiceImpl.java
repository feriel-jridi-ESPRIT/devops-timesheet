package tn.esprit.spring.services;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {

	private static final Logger log = Logger.getLogger(ContratServiceImpl.class);
	@Autowired
	ContratRepository contratRepository;


	public List<Contrat> getAllContrats() {
		log.info("fetching all contracts from the database ...");
		return (List<Contrat>) contratRepository.findAll();
	}

}
