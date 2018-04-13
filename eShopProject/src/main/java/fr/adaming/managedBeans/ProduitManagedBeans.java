package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "produitMB")
@RequestScoped
public class ProduitManagedBeans implements Serializable {

	private static final long serialVersionUID = 1L;

	// TRANFORMATION ASSOCIATION UML EN JAVA

	@ManagedProperty("#{prodService}")

	IProduitService produitService;

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	
	@ManagedProperty("#{categorieService}")
	ICategorieService catService;

	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	// ATTRIBUTS

	private Produit produit;
	private Admin admin;
	private Categorie cat;
	HttpSession maSession;
	private List<Produit> listeproduits;
	private boolean indice;

	private UploadedFile uf;

	

	@PostConstruct
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	// CONSTRUCTEUR VIDE
	public ProduitManagedBeans() {
		this.produit = new Produit();
		this.cat = new Categorie();
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	public List<Produit> getListeproduits() {
		return listeproduits;
	}

	public void setListeproduits(List<Produit> listeproduits) {
		this.listeproduits = listeproduits;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}


	public IProduitService getProduitService() {
		return produitService;
	}

	// METHODES
	
	// méthode rechercher produits par catégorie
	
	public String rechercherByCategorie() {
		this.cat = catService.getCategorie(cat);
		listeproduits = produitService.getAllProduits(cat);
		if (listeproduits != null) {
			return "test";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue, produit(s) introuvable(s)."));
			return "";
		}
	}

	// méthode afficher la liste de tous les produits
	
		public String afficherProduits() {
			listeproduits = produitService.getlisteProduit();
			if (listeproduits != null) {
				return "test";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Une erreur est survenue, liste des produits introuvable."));
				return "";
			}
		}
	
	// AJOUTER UN PRODUIT

	public String ajouterProd() {
		
		produit.setPhoto(this.uf.getContents());
		
		// APPEL DE LA METHODE AJOUTER

		Produit prodOut = produitService.addProduit(produit, cat);

		if (prodOut.getId() != 0) {
			
	

			// RECUPERER LA NOUVELLE LISTE DE PRODUIT

			List<Produit> listep = produitService.getlisteProduit();

			this.listeproduits = listep;

			return "tabAdmin";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec ajout"));

			return "";
		}
	}

	
	//MODIFIER UN PRODUIT
	
	public String modifierProd() {

		int verif = produitService.updateProduit(produit, cat);

		if (verif != 0) {

			List<Produit> listep = produitService.getlisteProduit();

			this.listeproduits = listep;

			return "tabAdmin";
		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec modification"));

			return "";
		}
	}
	
	public void getByIdEvent() {
		this.produit = produitService.rechercherProduitById(produit);
	}
	
	//SUPPRIMER UN PRODUIT 

	public String supprimerProd() {

		int verif = produitService.deleteProduit(produit);

		if (verif != 0) {

			List<Produit> listep = produitService.getlisteProduit();

			this.listeproduits = listep;

			return "test";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec suppression"));

			return "";

		}
	}

	
	//RECHERCHER UN PRODUIT PAR SON ID
	
	public String rechercherProdByid() {

		this.produit = produitService.rechercherProduitById(produit);
		if (produit != null) {

			this.indice = true;

			return "test";
		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec ajout"));
			this.indice = false;
			return "";
		}

	}
}
