package fr.adaming.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.model.Client;
import fr.adaming.model.Voyage;
import fr.adaming.service.IClientService;

@Controller
@RequestMapping("/client")
public class ClientControllers {

	@Autowired
	IClientService clientService;

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}
	
	// ================= Ajout client =======================================
	@RequestMapping(name="/addFormClient", method=RequestMethod.GET)
	public String addFormClient(Model model){
		model.addAttribute("clientAdd", new Client());
		
		return "addClient";
	}
	
	@RequestMapping(name="/addClient", method=RequestMethod.POST)
	public String submitAddFormClient(RedirectAttributes redirectAttribute, Model model, @ModelAttribute("clientAdd") Client client){
		
		// On appelle la m�thode service
		Client clientOut = clientService.addClient(client);
		
		if(clientOut.getId() != 0){
			// Actualiser la liste
			List<Client> liste = clientService.getAllClients();
			model.addAttribute("clientList", liste);
			return "listClient";
		}else{
			redirectAttribute.addFlashAttribute("message", "Le client n'a pas �t� ajout�");
			return "redirect:addClient";
		}
	}
	
	// ================= liste client =======================================
	@RequestMapping(value = "/listeClients", method = RequestMethod.GET)
	public String listClients(Model model) {

		List<Client> liste = clientService.getAllClients();
		model.addAttribute("clientList", liste);

		return "listClients";

	}
	
	// ================= Modif client =======================================
	@RequestMapping(value = "/updateFormClient", method = RequestMethod.GET)
	public String updateFormClient(Model model) {

		model.addAttribute("clientUpdate", new Client());
		return "updateClient";
	}

	@RequestMapping(value = "/updateClient", method = RequestMethod.POST)
	public String submitUpdateFormClient(RedirectAttributes redirectAttribute, Model model,
			@ModelAttribute("clientUpadte") Client client) {

		// Appel de la m�thode service
		Client clientOut = clientService.updateClient(client);

		if (clientOut.getId() == client.getId()) {
			List<Client> liste = clientService.getAllClients();
			model.addAttribute("clientList", liste);

			return "accueilAgent";
		} else {
			// Message d'erreur si le client n'a pas �t� modifi�
			redirectAttribute.addFlashAttribute("message", "Le client n'a pas �t� modifi�");
			return "redirect:updateFormClient";
		}
	}
	
//	@RequestMapping(value = "/getClientById", method = RequestMethod.GET)
//	public String formGetById(Model model) {
//		return "getClientById";
//	}

	@RequestMapping(value = "/getClientById", method = RequestMethod.POST)
	public String submitformGetById(RedirectAttributes redirAttr, Model model,
			@RequestParam("pId") int id) {
		Client clientOut = clientService.getClientById(id);

		if (clientOut.getId() == id) {
			model.addAttribute("client", clientOut);
			redirAttr.addFlashAttribute("message", "Le client a �t� trouv� !");
			return "clientsAgent";
		} else {
			redirAttr.addFlashAttribute("message", "Aucun client n'a �t� trouv�");
			return "redirect:getClientById";
		}
	}
	
	@RequestMapping(value = "/admin/delClientByLink/{pId}", method = RequestMethod.GET)
	public String supprimVoyageLien(Model model, @PathVariable("pId") int id) {
		
		clientService.deleteClient(id);

		// Actualiser la liste
		List<Client> liste = clientService.getAllClients();
		model.addAttribute("ClientList", liste);
		
		return "clientsAgent";
	}
}
