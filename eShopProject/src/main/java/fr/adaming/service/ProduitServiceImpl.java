package fr.adaming.service;

import java.util.ArrayList;
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
	public Produit addProduit(Produit p, Categorie categorie) {
		p.setCategorie(categorie);
		return produitDao.addProduit(p);
	}

	@Override
	public List<Produit> getlisteProduit() {
		return produitDao.getlisteProduit();
	}

	@Override
	public int updateProduit(Produit p, Categorie categorie) {
		p.setCategorie(categorie);
		return produitDao.updateProduit(p);
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

	@Override
	public List<Produit> getParMotCle(String motCle) {
		// récupérer la liste des produits :
		List<Produit> listePr=produitDao.getlisteProduit();
		
		//initialiser la liste récupérer avec les mots clés :
		List<Produit> listeRech = new ArrayList<Produit>();
		
		for(Produit pr : listePr) {
			if(pr.getDesignation().contains(motCle) || pr.getDescription().contains(motCle)) {
			listeRech.add(pr);
			}
		}
		return listeRech;
		}

}
