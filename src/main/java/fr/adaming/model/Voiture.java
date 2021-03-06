package fr.adaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="voitures")
public class Voiture {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_voit")
	private int id;
	
	private Categorie categorie;
	
	private Marque marque;
	
	private String modele;
	
	private int nbPlace;
	
	private double prixJour;

//	//Liaison UML en JAVA
//	@OneToOne(mappedBy="voiture")
//	@JoinColumn(name="dossier_id",referencedColumnName="id_do")
//	private Dossier dossier;

	//Constructeurs
	public Voiture() {
		super();
	}

	public Voiture(Categorie categorie, Marque marque, String modele, int nbPlace, double prixJour) {
		super();
		this.categorie = categorie;
		this.marque = marque;
		this.modele = modele;
		this.nbPlace = nbPlace;
		this.prixJour = prixJour;
	}

	public Voiture(int id, Categorie categorie, Marque marque, String modele, int nbPlace, double prixJour) {
		super();
		this.id = id;
		this.categorie = categorie;
		this.marque = marque;
		this.modele = modele;
		this.nbPlace = nbPlace;
		this.prixJour = prixJour;
	}

	//Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public double getPrixJour() {
		return prixJour;
	}

	public void setPrixJour(double prixJour) {
		this.prixJour = prixJour;
	}


	@Override
	public String toString() {
		return "Voiture [id=" + id + ", modele=" + modele + ", nbPlace=" + nbPlace + ", prixJour=" + prixJour
				 + "]";
	}
	
	

}
