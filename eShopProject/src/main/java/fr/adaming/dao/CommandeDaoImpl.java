package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao{

	// injection de dépendance du collaborateur SessionFactory
	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	// déclaration des attributs
	private Session s;
	private Query q;
	private String r;
	
	@Override
	public Commande addCommande(Commande c, Client Cl) {
		
		// Ouvrir session
		s=sf.getCurrentSession();
		
		s.save(c);
		
		return null;
	}
	
	@Override
	public List<Commande> getAllCommande(Commande c, Client Cl) {
		
		// requête HQL
		r = "FROM Commande c";
		
		// ouvrir session
		s= sf.getCurrentSession();
				
		// objet de type Query pour envoyer la requête HQL
		q=s.createQuery(r);
		
		// Récup du résultat		
		return q.list();
	}

	@Override
	public Commande getCommandeById(Commande c, Client cl) {
		// requête HQL
		r = "FROM Commande c WHERE c.idCommande=:pId AND c.client.idClient=:pIdCl";
		
		// ouvrir session
		s= sf.getCurrentSession();
		
		// objet de type Query pour envoyer la requête HQL
		q=s.createQuery(r);
		
		// passage des paramètres
		q.setParameter("pId", c.getIdCommande());
		q.setParameter("pIdCl", c.getClient().getIdClient());
		
		// envoyer requête et récupérer la commande
		return (Commande) q.uniqueResult();
	}
	@Override
	public int deleteCommande(Commande c, Client cl) {
		// requête HQL
		r = "DELETE FROM Commande c WHERE c.idCommande=:pId AND c.client.idClient=:pIdCl";
		
		// ouvrir session
		s= sf.getCurrentSession();
		
		// objet de type Query pour envoyer la requête HQL
		q=s.createQuery(r);
		
		// passage des paramètres
		q.setParameter("pId", c.getIdCommande());
		q.setParameter("pIdCl", c.getClient().getIdClient());
		
		// envoyer requête
		return q.executeUpdate();
	}
	@Override
	public int updateCommande(Commande c, Client cl) {
		// requête HQL
		r = "UPDATE Commande c SET c.dateCommande=:pDate, c.listeLignesCommandes=:pListeLC WHERE c.idCommande=:pId AND c.client.idClient=:pIdCl";
		
		// ouvrir session
		s= sf.getCurrentSession();
		
		// objet de type Query pour envoyer la requête HQL
		q=s.createQuery(r);
		
		// passage des paramètres
		q.setParameter("pDate", c.getDateCommande());
		q.setParameter("pListeLC", c.getListeLignesCommandes());
		q.setParameter("pId", c.getIdCommande());
		q.setParameter("pIdCl", c.getClient().getIdClient());
		
		// envoyer requête
		return q.executeUpdate();
	}



}
