package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "adminMB")
@RequestScoped
public class AdminManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TRANSFORMATION DE L'ASSOCIATION UML EN JAVA

	@ManagedProperty(value = "#{adminService}")
	private IAdminService adminService;

	@ManagedProperty(value = "#{prodService}")
	private IProduitService produitService;

	@ManagedProperty(value = "#{categorieService}")
	private ICategorieService catService;

	// SETTER POUR L'INJECTION DE DEPENDANCE

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}
	// ATTRIBUTS TRANSFERES A LA PAGE

	private Admin admin;
	private List<Produit> listeProduit;
	private List<Categorie> listeCategorie;

	// CONSTRUCTEUR VIDE

	public AdminManagedBean() {
		this.admin = new Admin();
	}

	// GETTERS ET SETTERS

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}

	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	// METHODE

	public String seConnecter() {

		// APPEL DE LA METHODE ISEXIST DE SERVICE

		Admin adminOut = adminService.isExist(this.admin);

		if (adminOut != null) {

			// RECUPERER LA LISTE

			this.listeProduit = produitService.getlisteProduit();
			this.listeCategorie = catService.getAllCategorie();
			// AFFICHER L'ADMIN DANS LA SESSION
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adminOut);

			// AJOUTER LA LISTE DANS LA SESSION
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListe",
					this.listeProduit);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieListe",
					this.listeCategorie);

			this.admin = adminOut;

			return "tabAdmin";

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("authentification échouée"));
			return "accueil";
		}

	}

}
