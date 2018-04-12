package fr.adaming.dao;

//import org.apache.commons.codec.binary.Base64;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
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
		query.setParameter("pId", prod.getId());
		
		return query.executeUpdate();
	}

	@Override
	public int deleteProduit(Produit prod) {
		//REQUETE HQL
		String req3="DELETE FROM Produit prod WHERE prod.id=:pId ";
		
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
	
	@Override
	public List<Produit> getAllProduits(Categorie cat) {
		// requête HQL pour récupérer la liste de produits par catégorie
		String r = "FROM Produit p WHERE p.categorie.idCategorie=:pIdCategorie";
				
		// création d'un objet de type Query pour envoyer la requête HQL
		s=sf.getCurrentSession();
		Query q = s.createQuery(r);
		
		// passage des paramètres
		q.setParameter("pIdCategorie", cat.getIdCategorie());
		
		// Récupération du résultat
		List<Produit> listeOut=q.list();
		
//		// Chargement des images
//		for(Produit prod: listeOut){
//			prod.setImage("data:image/png;base64,"+Base64.encodeBase64String(prod.getPhoto()));
//		}
		// retourner le résultat
		return listeOut;
	}

}
