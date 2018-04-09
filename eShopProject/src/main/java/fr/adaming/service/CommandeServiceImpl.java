package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Service("commandeService")
@Transactional // toutes les méthodes de la classe sont transactionnelles
public class CommandeServiceImpl implements ICommandeService{

	// transformation de l'association simple uml en java
	@Autowired
	private ICommandeDao commandeDao;
	
	//setter pour l'injection de dépendance
	public void setCommandeDao(ICommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}
	
	@Override
	public Commande getCommandeById(Commande c, Client cl) {
		c.setClient(cl);
		return commandeDao.getCommandeById(c, cl);
	}

	@Override
	public int deleteCommande(Commande c, Client cl) {
		c.setClient(cl);
		return commandeDao.deleteCommande(c, cl);
	}

	@Override
	public int updateCommande(Commande c, Client cl) {
		c.setClient(cl);
		return commandeDao.updateCommande(c, cl);
	}

}
