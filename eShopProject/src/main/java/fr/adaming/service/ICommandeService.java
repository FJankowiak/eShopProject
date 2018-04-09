package fr.adaming.service;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeService {
	
	public Commande getCommandeById(Commande c, Client cl);
	
	public int deleteCommande(Commande c, Client cl);
	
	public int updateCommande(Commande c, Client cl);

}
