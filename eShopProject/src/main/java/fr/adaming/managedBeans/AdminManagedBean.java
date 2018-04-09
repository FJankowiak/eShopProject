package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdminService;
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

	// PLACER ICI********* LA TRANS DE CATEGORIE*******

	// SETTER POUR L'INJECTION DE DEPENDANCE

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	// ATTRIBUTS TRANSFERES A LA PAGE

	private Admin admin;
	private List<Produit> listeProduit;

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

			// this.listeProduit=produitService.getlisteProduit(adminOut);
			
			//AFFICHER L'ADMIN DANS LA SESSION
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("etudiantListe", this.listeProduit);
			
			//AJOUTER LA LISTE DANS LA SESSION
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adminOut);
			
			this.admin=adminOut;
			
			return "";

		} else {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("authentification échouée"));
			return "";
		}

	}

}
