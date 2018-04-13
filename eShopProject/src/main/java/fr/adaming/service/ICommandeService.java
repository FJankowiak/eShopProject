package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeService {
	
	public Commande addCommande(Commande c, Client Cl);
	
	public List<Commande> getAllCommande(Commande c, Client Cl);
	
	public Commande getCommandeById(Commande c, Client cl);
	
	public int deleteCommande(Commande c, Client cl);
	
	public int updateCommande(Commande c, Client cl);

}
