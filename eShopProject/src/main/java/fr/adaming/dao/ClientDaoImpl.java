package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;

@Repository
public class ClientDaoImpl implements IClientDao{
	
	// injection de d�pendance byType et par setter
	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	// d�claration des attributs
	private Session s;
	private Query q;
		
	@Override
	public Client addClient(Client c) {
		// ouvrir session
		s= sf.getCurrentSession();
		
		// persister client
		s.save(c);
		
		// r�cup�rer client avec son nouvel id
		return c;
	}

	@Override
	public int updateClient(Client c) {
		// requ�te HQL
		String req = "UPDATE Client c SET c.nomClient=:pNom, c.adresse=:pAdresse, c.email=:pEmail, c.tel=:pTel, c.listeCommandes=:pListeCommandes "
				+ "WHERE c.idClient=:pId";
		
		// cr�er session
		s = sf.getCurrentSession();
		
		// cr�ation d'un objet de type Query
		q = s.createQuery(req);
		
		// passage des param�tres
		q.setParameter("pNom", c.getNomClient());
		q.setParameter("pAdresse", c.getAdresse());
		q.setParameter("pEmail", c.getEmail());
		q.setParameter("pTel", c.getTel());
		q.setParameter("pListeCommandes", c.getListeCommandes());
		q.setParameter("pId", c.getIdClient());
		
		// envoyer la requ�te
		return q.executeUpdate();
	}

	@Override
	public int deleteClient(Client c) {
		// requ�te HQL
		String req = "DELETE FROM Client c WHERE c.idClient=:pId";
		
		// cr�er session
		s = sf.getCurrentSession();
		
		// cr�ation d'un objet de type Query
		q = s.createQuery(req);
		
		// passage des param�tres
		q.setParameter("pId", c.getIdClient());
		
		// envoyer la requ�te
		return q.executeUpdate();
	}

	@Override
	public Client getClientById(Client c) {
		// requ�te HQL
		String req = "FROM Client c WHERE c.idClient=:pId";
		
		// cr�er session
		s = sf.getCurrentSession();
		
		// cr�ation d'un objet de type Query
		q = s.createQuery(req);
		
		// passage des param�tres
		q.setParameter("pId", c.getIdClient());
		
		// envoyer la requ�te
		return (Client) q.uniqueResult();
	}

}
