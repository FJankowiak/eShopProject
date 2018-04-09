package fr.adaming.dao;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeDao {
	
	public Commande getCommandeById(Commande c, Client cl);
	
	public int deleteCommande(Commande c, Client cl);
	
	public int updateCommande(Commande c, Client cl);

}
