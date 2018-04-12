package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Service("prodService")
@Transactional // POUR RENDRE LES METHODES DE LA CLASSE TRANSACTIONNELLES

public class ProduitServiceImpl implements IProduitService {

	// TRANSFORMATION DE L'ASSOCIATION UML EN JAVA

	@Autowired
	private IProduitDao produitDao;

	@Override
	public Produit addProduit(Produit prod) {

		return produitDao.addProduit(prod);
	}

	@Override
	public List<Produit> getlisteProduit() {
		return produitDao.getlisteProduit();
	}

	@Override
	public int updateProduit(Produit prod) {
	
		return produitDao.updateProduit(prod);
	}

	@Override
	public int deleteProduit(Produit prod) {
		
		return produitDao.deleteProduit(prod);
	}
	
	@Override
	public Produit rechercherProduitById(Produit prod) {
		
		return produitDao.rechercherProduitById(prod);
	}
	
	@Override
	public Produit rechercherProduit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> getAllProduits(Categorie categorie) {
		return produitDao.getAllProduits(categorie);
	}

}
