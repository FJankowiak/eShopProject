package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.LigneCommande;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@Autowired
	private SessionFactory sf;

	Session s;

	// SETTER POUR L'INJECTION DE DEPENDANCE
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	

	@Override
	public LigneCommande ajouterLC(LigneCommande lc) {
		
		s=sf.getCurrentSession();
		
		s.save(lc);
		
		return null;
	}


	@Override
	public int modifierLC(LigneCommande lc) {
		
		// Requete HQL
		String req="UPDATE LigneCommande lc SET lc.quantite=:pQuantite,lc.prix=:pPrix, lc.produit=:pProd, lc.commande=:pCom WHERE lc.id_ligne=:pIdlc";
		
		// Création session
		s=sf.getCurrentSession();
		
		// Creation query
		Query query=s.createQuery(req);
		
		// Passage des params
		query.setParameter("pQuantite", lc.getQuantite());
		query.setParameter("pPrix", lc.getPrix());
		query.setParameter("pProd", lc.getProduit());
		query.setParameter("pCom", lc.getCommande());
		query.setParameter("pIdcl", lc.getId_ligne());
		
		// Exploitation des résultats
		return query.executeUpdate();
	}

	@Override
	public List<LigneCommande> getLigneCommande() {
		
		// Requete HQL
		String req="FROM LigneCommande lc";
				
		// Création session
		s=sf.getCurrentSession();
		
		// Creation query
		Query query=s.createQuery(req);
		
		return query.list();
	}

	@Override
	public LigneCommande isExist(LigneCommande lc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void viderLC(LigneCommande lc) {

		
		//Requête HQL
		 String req7="DELETE FROM LigneCommande lc WHERE lc.id_ligneCom=:pId";
		
		// Ouvrir une session
		s = sf.getCurrentSession();

		// Création du query
		Query query = s.createQuery(req7);
		
		// Passage des params
		query.setParameter("pId", lc.getId_ligne());

		// Exploitation des résultats
		query.executeUpdate();

	}

}
