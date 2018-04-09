package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;

@Repository
public class ClientDaoImpl implements IClientDao{
	
	// injection de dépendance byType et par setter
	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	// déclaration des attributs
	private Session s;
	private Query q;
		
	@Override
	public Client addClient(Client c) {
		// ouvrir session
		s= sf.getCurrentSession();
		
		// persister client
		s.save(c);
		
		// récupérer client avec son nouvel id
		return c;
	}

	@Override
	public int updateClient(Client c) {
		// requête HQL
		String req = "UPDATE Client c SET c.nomClient=:pNom, c.adresse=:pAdresse, c.email=:pEmail, c.tel=:pTel, c.listeCommandes=:pListeCommandes WHERE c.idClient=:pId";
		
		// créer session
		s = sf.getCurrentSession();
		
		// création d'un objet de type Query
		q = s.createQuery(req);
		
		// passage des paramètres
		q.setParameter("pNom", c.getNomClient());
		q.setParameter("pAdresse", c.getAdresse());
		q.setParameter("pEmail", c.getEmail());
		q.setParameter("pTel", c.getTel());
		q.setParameter("pListeCommandes", c.getListeCommandes());
		q.setParameter("pId", c.getIdClient());
		
		// envoyer la requête
		return 0;
	}

	@Override
	public int deleteClient(Client c) {
		// requête HQL
		
		// créer session
		
		// création d'un objet de type Query
		
		// passage des paramètres
		
		// envoyer la requête
		return 0;
	}

	@Override
	public Client getClientById(Client c) {
		// requête HQL
		
		// créer session
		
		// création d'un objet de type Query
		
		// passage des paramètres
		
		// envoyer la requête
		return null;
	}

}
