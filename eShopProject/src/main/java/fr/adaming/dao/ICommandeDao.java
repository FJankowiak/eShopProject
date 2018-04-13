package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeDao {
	
	public Commande addCommande(Commande c, Client Cl);
	
	public List<Commande> getAllCommande(Commande c, Client Cl);
	
	public Commande getCommandeById(Commande c, Client cl);
	
	public int deleteCommande(Commande c, Client cl);
	
	public int updateCommande(Commande c, Client cl);

}
