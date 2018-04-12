package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao{

	// injection de dépendance byType et par setter
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
	public List<Categorie> getAllCategorie() {
		// requête HQL
		r = "FROM Categorie c";
		
		// ouvrir une session
		s = sf.getCurrentSession();
		
		// récupérer un objet de type Query pour envoyer la requête HQL
		q = s.createQuery(r);
		
		// envoyer la requête et retourner le résultat (la liste de catégories)
		return q.list();
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		// ouvrir une session
		s = sf.getCurrentSession();
		
		// save catégorie (equivalent de persister de JPA)
		s.save(c);
		
		// récupérer catégorie avec son id nouvellement attribué
		return c;
	}

	@Override
	public int updateCategorie(Categorie c) {
		// requête HQL
		r = "UPDATE Categorie c SET c.nomCategorie=:pNom, c.photo=:pPhoto, c.description=:pDescription "
				+ "WHERE c.idCategorie=:pId";
		
		// ouvrir session
		s= sf.getCurrentSession();
		
		// objet de type Query
		q=s.createQuery(r);
		
		// passage des paramètres
		q.setParameter("pNom", c.getNomCategorie());
		q.setParameter("pPhoto", c.getPhoto());
		q.setParameter("pDescription", c.getDescription());
		q.setParameter("pId", c.getIdCategorie());
		
		// envoyer requête
		return q.executeUpdate();
	}

	@Override
	public int deleteCategorie(Categorie c) {
		// requête HQL
		r = "DELETE FROM Categorie c WHERE c.idCategorie=:pId";
		
		// ouvrir session
		s= sf.getCurrentSession();
		
		// objet de type Query pour envoyer la requête HQL
		q=s.createQuery(r);
		
		// passage des paramètres
		q.setParameter("pId", c.getIdCategorie());
		
		// envoyer requête
		return q.executeUpdate();
	}

	@Override
	public Categorie getCategorie(Categorie c) {
		// requête HQL
		r = "FROM Categorie c WHERE c.idCategorie=:pId";
		
		// ouvrir session
		s= sf.getCurrentSession();
		
		// objet de type Query pour envoyer la requête HQL
		q=s.createQuery(r);
		
		// passage des paramètres
		q.setParameter("pId", c.getIdCategorie());
		
		// envoyer requête et récupérer la catégorie
		return (Categorie) q.uniqueResult();
	}

}
