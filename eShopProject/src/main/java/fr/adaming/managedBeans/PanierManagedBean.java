package fr.adaming.managedBeans;

import javax.servlet.http.HttpSession;

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
	private LigneCommande lignesCmd;
	HttpSession maSession;
	
	private ILigneCommandeService ligneService;
	
	private IProduitService produitService;
	
	private ICommandeService commandeService;

	// Constructeur vide
	public PanierManagedBean() {
		super();
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
		return lignesCmd;
	}

	public void setLignesCmd(LigneCommande lignesCmd) {
		this.lignesCmd = lignesCmd;
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
		
		return null;
	}
	
	public String supprimerPanier(){
		
		return null;
	}
	
	public void supprimerLigneCmd(){
		
	}

}
