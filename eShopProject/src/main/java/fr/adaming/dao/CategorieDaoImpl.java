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

	// injection de d�pendance byType et par setter
	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	// d�claration des attributs
	private Session s;
	private Query q;
	private String r;
	
	@Override
	public List<Categorie> getAllCategorie() {
		// requ�te HQL
		r = "FROM Categorie c";
		
		// ouvrir une session
		s = sf.getCurrentSession();
		
		// r�cup�rer un objet de type Query pour envoyer la requ�te HQL
		q = s.createQuery(r);
		
		// envoyer la requ�te et retourner le r�sultat (la liste de cat�gories)
		return q.list();
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		// ouvrir une session
		s = sf.getCurrentSession();
		
		// save cat�gorie (equivalent de persister de JPA)
		s.save(c);
		
		// r�cup�rer cat�gorie avec son id nouvellement attribu�
		return c;
	}

	@Override
	public int updateCategorie(Categorie c) {
		// requ�te HQL
		r = "UPDATE Categorie c SET c.nomCategorie=:pNom, c.photo=:pPhoto, c.description=:pDescription "
				+ "WHERE c.idCategorie=:pId";
		
		// ouvrir session
		s= sf.getCurrentSession();
		
		// objet de type Query
		q=s.createQuery(r);
		
		// passage des param�tres
		q.setParameter("pNom", c.getNomCategorie());
		q.setParameter("pPhoto", c.getPhoto());
		q.setParameter("pDescription", c.getDescription());
		q.setParameter("pId", c.getIdCategorie());
		
		// envoyer requ�te
		return q.executeUpdate();
	}

	@Override
	public int deleteCategorie(Categorie c) {
		// requ�te HQL
		r = "DELETE FROM Categorie c WHERE c.idCategorie=:pId";
		
		// ouvrir session
		s= sf.getCurrentSession();
		
		// objet de type Query pour envoyer la requ�te HQL
		q=s.createQuery(r);
		
		// passage des param�tres
		q.setParameter("pId", c.getIdCategorie());
		
		// envoyer requ�te
		return q.executeUpdate();
	}

	@Override
	public Categorie getCategorie(Categorie c) {
		// requ�te HQL
		r = "FROM Categorie c WHERE c.idCategorie=:pId";
		
		// ouvrir session
		s= sf.getCurrentSession();
		
		// objet de type Query pour envoyer la requ�te HQL
		q=s.createQuery(r);
		
		// passage des param�tres
		q.setParameter("pId", c.getIdCategorie());
		
		// envoyer requ�te et r�cup�rer la cat�gorie
		return (Categorie) q.uniqueResult();
	}

}
