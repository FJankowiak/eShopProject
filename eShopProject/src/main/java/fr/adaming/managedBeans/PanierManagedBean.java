
package fr.adaming.managedBeans;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.DocumentException;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;
import fr.adaming.service.MailClient;
import fr.adaming.service.PdfClient;

public class PanierManagedBean {
	
	// Attributs
	private Panier panier;
	private Produit produit;
	private LigneCommande ligneCmd;
	HttpSession maSession;
	private double totalPanier;
	private Categorie categorie;
	
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
	
	
	public double getTotalPanier() {
		return totalPanier;
	}

	public void setTotalPanier(double totalPanier) {
		this.totalPanier = totalPanier;
	}

	// Methodes métier
	public String ajouterLigneCommande(){
		
		boolean verif = true;
		Produit pOut = produitService.rechercherProduitById(this.produit);
		pOut.setQuantSouhaite(produit.getQuantSouhaite());
		this.ligneCmd.setProduit(pOut);
		
		// Création d'un panier dans la session
		this.panier = (Panier) maSession.getAttribute("monPanier");
		
		if (panier == null){
			// Inclusion de la liste de lignes de commande dans le panier
			panier.setListeLignesCommande(new ArrayList<>());
		
			//Récupération de la quantité de produit souhaitée : ajouté à ligne de commande
			this.ligneCmd.setQuantite(this.ligneCmd.getProduit().getQuantSouhaite());
		
			// Récupération du prix : ajouté à ligne de commande
			this.ligneCmd.setPrix(this.ligneCmd.getProduit().getPrix() * this.ligneCmd.getQuantite());
		
			// Appel de la methode ajouter ligne service
			LigneCommande ligneOut = ligneService.ajouterLC(ligneCmd);
		
			// Récupération de la nouvelle liste : ajouté au panier
			panier.getListeLignesCommande().add(ligneOut);
		
			totalPanier = ligneOut.getPrix();
		
			// Attribution d'un panier et d'un total à la session
			maSession.setAttribute("monPanier", panier);
			maSession.setAttribute("total", totalPanier);
			}else{
				for (LigneCommande lg : panier.getListeLignesCommande()) {
					if (lg.getProduit().getId() == this.produit.getId()) {
						verif = false;
					}
				}
				// Si panier et lignes de commande déja existantes
				if (verif) {
					
					// Nouvelle ligne de commande
					this.ligneCmd.setQuantite(this.ligneCmd.getProduit().getQuantSouhaite());
					this.ligneCmd.setPrix(this.ligneCmd.getProduit().getPrix() * this.ligneCmd.getQuantite());
					
					// Ajout de la nouvelle ligne de commande
					LigneCommande ligneOut = ligneService.ajouterLC(ligneCmd);
					
					// Ajout de la nouvelle liste de lignes de commandes au panier
					panier.getListeLignesCommande().add(ligneOut);
					
					// Calcule du total du panier
					totalPanier = (double) maSession.getAttribute("total") + ligneOut.getPrix();
					
					// Attribution du total panier et du panier à la session
					maSession.setAttribute("total", totalPanier);
					maSession.setAttribute("monPanier", panier);
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit déjà ajouté"));
				}
		
			}
		
		// Attribution d'une session au client
		Client clientOut = (Client) maSession.getAttribute("clientSession");
		if (clientOut != null) {
			return "ajoutPanierClient";
		} else {
			return "ajoutPanier";
		}
		
		}
	
	public String supprimerPanier(){ //après que la commande soit effectuée
		
		// Attribution d'une session au client
		Client clientOut = (Client) maSession.getAttribute("clientSession");
		if (clientOut == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veuillez vous connecter"));
			return "seconnecterClient";
		}else {
			
			// Renvoi la date du moment où est passé la commande
			Calendar cd = Calendar.getInstance();
			Date datecrea = cd.getTime();
			
			//Total de la commande de la session courante
			double totalCommande = (double) maSession.getAttribute("total");
			
			// Création d'une nouvelle commande
			Commande newCmd = new Commande(datecrea, totalCommande);
			
			// Attribution d'une session au panier
			Panier panOut = (Panier) maSession.getAttribute("monPanier");
			
			// Association de la nouvelle liste à la commande
			newCmd.setListeLignesCommandes(panOut.getListeLignesCommande());
			
			// Association du client à la commande
			newCmd.setClient(clientOut);
			
			// Appel de la methode ajouter commande de service
			Commande cOut = commandeService.addCommande(newCmd, clientOut);
			for (LigneCommande ligneCom : panOut.getListeLignesCommande()){
				
				// Récupération du produit de la ligne de commande
				Produit p=ligneCom.getProduit();
				
				// Recherche du produit de la ligne de commande
				Produit pRech=produitService.rechercherProduitById(p);
				Categorie Cat=p.getCategorie();
				
				//Détermination de la quantité de produit restant
				pRech.setQuantite(pRech.getQuantite()-ligneCom.getQuantite());
				
				
				int verifModif=produitService.updateProduit(pRech,Cat);
				if (verifModif!=0){
					
					// Création de la ligne de commande du client
					ligneCom.setCommande(cOut);
					
					// Modif de la ligne de commande du panier du client
					int verifModifL=ligneService.modifierLC(ligneCom);
				}
			}
			if (cOut != null) {
				try {
					PdfClient.Facture(cOut);
				} catch (FileNotFoundException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MailClient.MailFacture(clientOut.getEmail());
				maSession.setAttribute("total", 0);
				maSession.setAttribute("monPanier", null);
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
			}
			return "commandes";
		}
		
	}
	
	public void supprimerLigneCmd(){
		
		// Attribution d'un panier à la session
		this.panier = (Panier) maSession.getAttribute("monPanier");
		
		for (LigneCommande lc : panier.getListeLignesCommande()){
			if (lc.getId_ligne() != 0){
				this.totalPanier = (double) maSession.getAttribute("total")-lc.getPrix();
				panier.getListeLignesCommande().remove(lc);
				maSession.setAttribute("total", totalPanier);
				maSession.setAttribute("monPanier", panier);
			}
		}
		
	}

}
