
package fr.adaming.managedBeans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

public class PanierManagedBean {
	
	// Attributs
	private Panier panier;
	private Produit produit;
	private LigneCommande ligneCmd;
	HttpSession maSession;
	
	// Transformation de l'association UML en JAVA
	@Autowired
	private ILigneCommandeService ligneService;
	@Autowired
	private IProduitService produitService;
	@Autowired
	private ICommandeService commandeService;

	// Constructeur vide
	public PanierManagedBean() {
		super();
		this.produit = new Produit();
		this.ligneCmd = new LigneCommande();
		this.panier = new Panier();
	}
	
	@PostConstruct
	public void init() {

		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	// Getters et setters
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public LigneCommande getLignesCmd() {
		return ligneCmd;
	}

	public void setLignesCmd(LigneCommande lignesCmd) {
		this.ligneCmd = lignesCmd;
	}

	public ILigneCommandeService getLigneService() {
		return ligneService;
	}

	public void setLigneService(ILigneCommandeService ligneService) {
		this.ligneService = ligneService;
	}

	public IProduitService getProduitService() {
		return produitService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	public ICommandeService getCommandeService() {
		return commandeService;
	}

	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
	}
	
	// Methodes métier
	public String ajouterLigneCommande(){
		
		boolean verif = true;
		Produit pOut = produitService.rechercherProduitById(this.produit);
		
		// Inclusion de la liste de lignes de commande dans le panier
		panier.setListeLignesCommande(new ArrayList<>());
		
		//Récupération de la quantité de produit souhaitée : ajouté à ligne de commande
		this.ligneCmd.setQuantite(this.ligneCmd.getProduit().getQuantite());
		
		// Récupération du prix : ajouté à ligne de commande
		this.ligneCmd.setPrix(this.ligneCmd.getProduit().getPrix() * this.ligneCmd.getQuantite());
		
		return null;
	}
	
	public String supprimerPanier(){
		
		return null;
	}
	
	public void supprimerLigneCmd(){
		
	}

}
