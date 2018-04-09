package fr.adaming.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@ManagedBean(name="clientMB")
@RequestScoped
public class ClientManagedBean {
	
	// transformation de l'association simple uml en java
	@ManagedProperty(value="#{clientService}") // fait le lien entre le context du conteneur web et celui du conteneur de spring IoC
	private IClientService clService;
	
	// getter et setter pour l'injection de dépendance
	public IClientService getClService() {
		return clService;
	}

	public void setClService(IClientService clService) {
		this.clService = clService;
	}

	// attributs du managedBean
	private Client client;
	
	// constructeur vide
	public ClientManagedBean() {
		this.client = new Client();
	}

	// getters et setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	// méthodes de managedBean
	public String ajouterClient() {
		Client cOut = clService.addClient(client);
		if(cOut.getIdClient() != 0){
			return "";
		} else {
			return "";
		}
	}
	
	public String supprimerClient() {
		int verif = clService.deleteClient(client);
		if(verif != 0){
			return "";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur, client non supprimé."));
			return "";
		}
	}
	
	public String modifierClient() {
		int verif = clService.updateClient(client);
		if(verif != 0){
		return "";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur, client non supprimé."));
			return "";
		}
	}


}
