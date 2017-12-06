package fr.adaming.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.adaming.model.*;
import fr.adaming.service.IClientService;
import fr.adaming.service.IDossierService;
import fr.adaming.service.IVoyageService;


@Controller
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired
	private IVoyageService voyageService;
	
	@Autowired
	private IDossierService dossierService;
	
	@Autowired
	private IClientService clientService;

	//page d'accueil de l'agent
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView mainPage(ModelAndView modelView){
		// TODO une methode getDossiersByEtat (afficher les en attente en page d'accueil)
		// une methode getUpcomingVoyages (filtrage par date/nombre de jours)
		modelView.setViewName("homeAgent");
		return modelView;
	}
	
	//page de gestion des voyages (affichage des en cours)
	@RequestMapping(value="/voyages", method=RequestMethod.GET)
	public ModelAndView voyagesPage(ModelAndView modelView){
		// TODO une methode getUpcomingVoyages
		List<Voyage> listeVoyages = voyageService.getAllVoyages();
		modelView.addObject("listeVoyages", listeVoyages);
		
		modelView.setViewName("voyagesAgent");
		return modelView;
	}
	
	@RequestMapping(value="/dossiers", method=RequestMethod.GET)
	public ModelAndView dossiersPage(ModelAndView modelView, HttpSession session){
		
		// TODO separer dossiers par etat, classer par date
		Agent sessionAgent = (Agent) session.getAttribute("sessionAgent");
		if(sessionAgent != null){
			List<Dossier> listeDossiers = dossierService.getAllDossierByAgent(sessionAgent);
			modelView.addObject("listeDossiers", listeDossiers);
		}
		
		modelView.setViewName("dossiersAgent");
		return modelView;
	}
	
	@RequestMapping(value="/clients", method=RequestMethod.GET)
	public ModelAndView clientsPage(ModelAndView modelView, HttpSession session){
		
		Agent sessionAgent = (Agent) session.getAttribute("sessionAgent");
		if(sessionAgent!= null){
			List<Client> listeClients = clientService.getAllClients();
			// TODO RAJOUTER UNE METHODE GETCLIENTSBYAGENT
			modelView.addObject("listeClients", listeClients);
		}
		
		modelView.setViewName("clientsAgent");
		return modelView;
	}
}