package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Produit;


@Repository
public class ProduitDaoImpl implements IProduitDao {
	
	@Autowired
	private SessionFactory sf;
	
	Session s;
	
	
	//SETTER POUR L'INJECTION DE DEPENDANCE
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Produit addProduit(Produit prod) {
		//OUVRIR UNE SESSION
		s=sf.getCurrentSession();
		
		//FAIRE PERSISTER LE PRODUIT
		s.save(prod);
		
		return prod;
	}

	@Override
	public List<Produit> getlisteProduit() {
		//REQUETE HQL
		
		String req1="FROM Produit";
		
		//OUVRIR UNE SESSION 
		
		Session s=sf.getCurrentSession();
		
		//RECUPERER LA REQUETE
		
		Query query=s.createQuery(req1);
		
				
		
		return query.list();
	}

	@Override
	public int updateProduit(Produit prod) {
		
		
		//REQUETE HQL
		String req2="UPDATE Produit AS prod SET prod.designation=:pDesignation, prod.description=:pDescription, prod.prix=:pPrix, prod.quantite=:pQuantite, prod.photo=:pPhoto "
				+ "WHERE prod.id=:pId ";
		
		//OUVRIR UNE SESSION
		Session s=sf.getCurrentSession();
		
		//RECUPERER LA REQUETE
		
		Query query=s.createQuery(req2);
		
		//PASSAGE DES PARAMS
		
		query.setParameter("pDesignation",prod.getDesignation());
		query.setParameter("pDescription", prod.getDescription());
		query.setParameter("pQuantite",prod.getQuantite());
		query.setParameter("pPrix", prod.getPrix());
		query.setParameter("pPhoto", prod.getPhoto());
		query.setParameter("pProdId", prod.getId());
		
		return query.executeUpdate();
	}

	@Override
	public int deleteProduit(Produit prod) {
		//REQUETE HQL
		String req3="DELETE FROM Produit prod WHERE prod.id=pId ";
		
		//OUVRIR UNE SESSION
		
		s=sf.getCurrentSession();
		
		//RECUPERER LA REQUETE
		
		Query query=s.createQuery(req3);
		
		//PASSAGE DES PARAMS
		
		query.setParameter("pId",prod.getId());
		
		return query.executeUpdate();
	}



	@Override
	public Produit rechercherProduitById(Produit prod) {
		//REQUETE HQL
		
		String req4="FROM Produit prod WHERE prod.id=:pId";
		
		//OUVRIR SESSION 
		s=sf.getCurrentSession();
		
		//RECUPERER LA REQUETE
		
		Query query = s.createQuery(req4);
		
		//PASSAGE DES PARAMS 
		
		query.setParameter("pId",prod.getId());
		return (Produit) query.uniqueResult();
	}
	
	
	//ATTENTION CETTE METHODE EST UTILE A LA REALISATION DU PANIER 
	//RECHERCHER UN PRODUIT CETTE METHODE EST PROVISOIRE *******
	
	@Override
	public Produit rechercherProduit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
