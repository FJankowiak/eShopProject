package fr.adaming.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.adaming.model.Produit;


@Repository
public class ProduitDaoImpl implements IProduitDao {

	@Override
	public Produit addProduit(Produit prod) {
		
		return null;
	}

	@Override
	public List<Produit> getlisteProduit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProduit(Produit prod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduit(Produit prod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Produit rechercherProduit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit rechercherProduitById(Produit prod) {
		// TODO Auto-generated method stub
		return null;
	}

}
