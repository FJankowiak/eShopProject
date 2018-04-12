package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitDao {

	// AJOUTER UN PRODUIT

	public Produit addProduit(Produit prod);

	// CONSULTER TOUS LES PRODUITS

	List<Produit> getlisteProduit();

	// MODIIFER UN PRODUIT

	public int updateProduit(Produit prod);

	// SUPPRIMER UN PRODUIT

	public int deleteProduit(Produit prod);

	// RECHERCHER PRODUIT BY ID
	public Produit rechercherProduitById(Produit prod);
	
	// consulter les produits d'une catégorie
	public List<Produit> getAllProduits(Categorie cat);

}
