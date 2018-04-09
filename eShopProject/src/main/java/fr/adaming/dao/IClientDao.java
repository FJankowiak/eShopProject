package fr.adaming.dao;

import fr.adaming.model.Client;

public interface IClientDao {
	
	public Client addClient(Client c);
	
	public int updateClient(Client c);
	
	public int deleteClient(Client c);
	
	public Client getClientById(Client c);

}
