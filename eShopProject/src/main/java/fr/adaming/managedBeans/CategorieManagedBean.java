package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "categorieMB")
@RequestScoped
public class CategorieManagedBean {

	// transformation uml en java
	@ManagedProperty(value = "#{categorieService}")
	private ICategorieService catService;

	// getters et setters pour l'injection de dépendance
	public ICategorieService getCatService() {
		return catService;
	}

	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}

	// attributs du managedBean
	private Categorie categorie;

	private UploadedFile uf;

	private List<Categorie> listeCategories;

	// constucteur vide
	public CategorieManagedBean() {
		this.categorie = new Categorie();
	}

	// getters et setters
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	@PostConstruct
	public void init() {
		listeCategories = catService.getAllCategorie();
	}

	// méthodes métiers

	public String ajouterCategorie() {
		categorie.setPhoto(this.uf.getContents());
		Categorie catOut = catService.addCategorie(categorie);
		if (catOut.getIdCategorie() != 0) {

			return "tabAdmin";
		} else {
			return "";
		}
	}

	public String supprimerCategorie() {
		int verif = catService.deleteCategorie(categorie);
		if (verif != 0) {
			return "tabAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur, catégorie non supprimé."));
			return "";
		}
	}

	public String modifierCategorie() {
		categorie.setPhoto(this.uf.getContents());
		int verif = catService.updateCategorie(categorie);
		if (verif != 0) {
			return "tabAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur, catégorie non supprimé."));
			return "";
		}
	}

	public String rechercherCategorie() {
		categorie = catService.getCategorie(categorie);
		if (categorie != null) {
			return "accueil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur, catégorie introuvable."));
			return "";
		}
	}

	public String afficherToutCategorie() {
		listeCategories = catService.getAllCategorie();
		if (listeCategories != null) {
			return "accueil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erreur, liste des catégories introuvable."));
			return "";
		}
	}

}
