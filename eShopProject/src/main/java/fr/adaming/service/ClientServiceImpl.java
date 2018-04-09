package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Service("clientService")
@Transactional // toutes les méthodes de la classe sont transactionnelles
public class ClientServiceImpl implements IClientService{

	// transformation de l'association simple uml en java
	@Autowired
	private IClientDao clientDao;
	
	//setter pour l'injection de dépendance
	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}	
	
	// méthodes métiers
	@Override
	public Client addClient(Client c) {
		return clientDao.addClient(c);
	}

	@Override
	public int updateClient(Client c) {
		return clientDao.updateClient(c);
	}

	@Override
	public int deleteClient(Client c) {
		return clientDao.deleteClient(c);
	}

	@Override
	public Client getClientById(Client c) {
		return clientDao.getClientById(c);
	}

}
