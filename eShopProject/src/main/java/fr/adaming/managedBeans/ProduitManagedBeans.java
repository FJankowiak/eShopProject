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
import fr.adaming.service.IProduitService;

@ManagedBean(name = "produitMB")
@RequestScoped
public class ProduitManagedBeans implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TRANFORMATION ASSOCIATION UML EN JAVA

	@ManagedProperty("#{prodService}")

	IProduitService produitService;

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	// ATTRIBUTS

	private Produit produit;
	private Admin admin;
	private Categorie cat;
	HttpSession maSession;
	List<Produit> listeprod;
	private boolean indice;

	private UploadedFile uf;

	private Long id_p;

	@PostConstruct
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	// CONSTRUCTEUR VIDE
	public ProduitManagedBeans() {
		this.produit = new Produit();
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

	public List<Produit> getListeprod() {
		return listeprod;
	}

	public void setListeprod(List<Produit> listeprod) {
		this.listeprod = listeprod;
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

	public Long getId_p() {
		return id_p;
	}

	public void setId_p(Long id_p) {
		this.id_p = id_p;
	}

	public IProduitService getProduitService() {
		return produitService;
	}

	// METHODES

	// AJOUTER UN PRODUIT

	public String ajouterProd() {

		// APPEL DE LA METHODE AJOUTER

		produit.setPhoto(this.uf.getContents());

		Produit prodOut = produitService.addProduit(produit);

		if (prodOut.getId() != 0) {

			// RECUPERER LA NOUVELLE LISTE DE PRODUIT

			List<Produit> listep = produitService.getlisteProduit();

			this.listeprod = listep;

			return "";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec ajout"));

			return "";
		}
	}

	
	//MODIFIER UN PRODUIT
	
	public String modifierProd() {

		int verif = produitService.updateProduit(produit);

		if (verif != 0) {

			List<Produit> listep = produitService.getlisteProduit();

			this.listeprod = listep;

			return "";
		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec modification"));

			return "";
		}
	}
	
	//SUPPRIMER UN PRODUIT 

	public String supprimerProd() {

		int verif = produitService.deleteProduit(produit);

		if (verif != 0) {

			List<Produit> listep = produitService.getlisteProduit();

			this.listeprod = listep;

			return "";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec suppression"));

			return "";

		}
	}

	
	//RECHERCHER UN PRODUIT PAR SON ID
	
	public void rechercherProdByid() {

		this.produit = produitService.rechercherProduitById(produit);
		if (produit != null) {

			this.indice = true;

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec ajout"));
			this.indice = false;

		}

	}
}
