package fr.adaming.controllers;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.model.Voyage;
import fr.adaming.service.IVoyageService;

@Controller
@Scope("session")
public class VoyageController {

	/**
	 * Injection de la couche Service
	 */
	@Autowired
	private IVoyageService voyageService;

	/**
	 * Setter pour la couche Service
	 * 
	 * @param voyageService
	 *            Couche Service
	 */
	public void setVoyageService(IVoyageService voyageService) {
		this.voyageService = voyageService;
	}

	// ==============================================================
	// ================== M�thodes
	// ==============================================================

	/**
	 * Acc�der � la liste des voyages sur la page d'accueil de l'agent
	 * 
	 * @param model
	 *            Contient la liste des voyages
	 * @return La page des voyages de l'agent
	 */
	@RequestMapping(value = "/agent/voyages", method = RequestMethod.GET)
	public String afficherListeVoyage(Model model) {

		List<Voyage> listeVoyages = voyageService.getAllVoyages();

		model.addAttribute("listeVoyages", listeVoyages);

		return "voyagesAgent";
	}

	/**
	 * Acc�der � la liste des voyages sur la page d'accueil publique
	 * 
	 * @param model
	 *            Contient la liste des voyages
	 * @return La page d'accueil publique
	 */
	@RequestMapping(value = "/listeVoyage", method = RequestMethod.GET)
	public String afficherListeVoyagePublic(Model model) {

		List<Voyage> listeVoyages = voyageService.getAllVoyages();

		model.addAttribute("listeVoyages", listeVoyages);

		return "accueil";
	}

	/**
	 * Supprimer un voyage via un lien
	 * 
	 * @param model
	 *            Contient la nouvelle liste des voyages apr�s suppression
	 * @param id
	 *            l'id du voyage � supprimer
	 * @return La page des voyages de l'agent
	 */
	@RequestMapping(value = "/agent/supprimVoyageLien/{pId}", method = RequestMethod.GET)
	public String supprimVoyageLien(Model model, @PathVariable("pId") int id) {

		voyageService.deleteVoyage(id);

		// Actualiser la liste
		List<Voyage> newListeVoyage = voyageService.getAllVoyages();
		model.addAttribute("listeVoyages", newListeVoyage);

		return "voyagesAgent";
	}

	/**
	 * Afficher le formulaire de modification d'un voyage
	 * 
	 * @return La page du formulaire de modification
	 */
	@RequestMapping(value = "/agent/formVoyageUpdate", method = RequestMethod.GET)
	public ModelAndView formVoyageUpdate() {

		return new ModelAndView("updateVoyageAgent", "voyageUpdate", new Voyage());
	}

	@RequestMapping(value = "/agent/modifViaLien", method = RequestMethod.GET)
	public String modifVoyageLien(Model model, @RequestParam("pId") int id) {

		Voyage vOut = voyageService.findVoyage(id);
		model.addAttribute("voyageUpdate", vOut);

		return "updateVoyageAgent";
	}

	@RequestMapping(value = "/agent/modifVoyage", method = RequestMethod.POST)
	public String soumettreVoyageUpdate(Model model, @ModelAttribute("voyageUpdate") Voyage v, RedirectAttributes ra) {

		// Appel de la m�thode Service
		voyageService.updateVoyage(v);
		
		//if (vOut != null) {
			// Actualiser la liste
			List<Voyage> listeVoyages = voyageService.getAllVoyages();
			model.addAttribute("listeVoyages", listeVoyages);

			return "voyagesAgent";
//		} else {
//			ra.addFlashAttribute("message", "Le voyage n'a pas �t� modifi�");
//			return "updateVoyageAgent";
//		}
	}
	
	/**
	 * R�cup�rer et afficher une image
	 * @param voyage
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/photoProd", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Voyage voyage) throws IOException {
		Voyage voy = voyageService.findVoyage(voyage.getId());
		if (voy.getImage() == null)
			return new byte[0];
		else
			return IOUtils.toByteArray(new ByteArrayInputStream(voy.getImage()));
	}

}
