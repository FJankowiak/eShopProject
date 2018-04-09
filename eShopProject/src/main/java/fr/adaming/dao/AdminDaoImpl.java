package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;


@Repository
public class AdminDaoImpl implements IAdminDao {
	
	@Autowired
	private SessionFactory sf;

	
	// LE SETTER POUR L'INJECTION DE DEPENDANCE
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Admin isExist(Admin a) {
		
		//REQUETE HQL
		
		String req="FROM Admin AS a WHERE a.mail=:pMail AND a.mdp=:pMdp ";
		
		//OUVRIR UNE SESSION
		
		Session s=sf.getCurrentSession();
		
		//RECUPERER LA REQUETE
		
		Query query=s.createQuery(req);
		
		//PASSAGE DES PARAMS
		
		query.setParameter("pMail",a.getMail());
		query.setParameter("pMdp",a.getMdp());
		
				
		
		return (Admin) query.uniqueResult();
	}

}
