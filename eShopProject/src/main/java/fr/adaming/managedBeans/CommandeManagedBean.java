package fr.adaming.managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.MailClient;

@ManagedBean(name="cmdMB")
@RequestScoped
public class CommandeManagedBean {
	
	// Transformation de l'association UML en JAVA
	@Autowired
	private ICommandeService commandeService;
	
	// Déclaration des attributs envoyés à la page
	private Commande commande;
	private Client client;
	private List<Commande> listeCommande;
	private boolean indice;
	private HttpSession maSession;
	
	// Constructeurs
	public CommandeManagedBean() {
		super();
	}

	// Getters et setters
	public ICommandeService getCommandeService() {
		return commandeService;
	}

	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Commande> getListeCommande() {
		return listeCommande;
	}

	public void setListeCommande(List<Commande> listeCommande) {
		this.listeCommande = listeCommande;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}
	
	
	// Methodes métiers
	
	public String ajouterCommande(){
		
		// Ajout d'une commande
		
		Commande comOut = commandeService.addCommande(commande, client);
		
		if (comOut != null){
			
			//Mise à jour liste commande du client
			List<Commande> liste = commandeService.getAllCommande(this.commande, this.client);
			MailClient.MailFacture(client.getEmail());
			
			//Mise à jour de la liste dans la session
			maSession.setAttribute("commandesListe", liste);
			
			return "accueilCl";
			
		}else{
			
			
			return "ajoutCmd";
		}
		
		
	}
	
	public String rechercherCommandeId(){
		
	
		this.commande=commandeService.getCommandeById(commande, client);
		this.indice=true;
		
		return "rechCmd";
		
	}

}
