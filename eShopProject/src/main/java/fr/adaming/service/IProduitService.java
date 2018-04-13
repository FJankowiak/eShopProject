package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitService {
	
		// AJOUTER UN PRODUIT

		public Produit addProduit(Produit prod, Categorie categorie);

		// CONSULTER TOUS LES PRODUITS

		List<Produit> getlisteProduit();

		// MODIIFER UN PRODUIT

		public int updateProduit(Produit prod, Categorie categorie);

		// SUPPRIMER UN PRODUIT

		public int deleteProduit(Produit prod);

		// RECHERCHER UN PRODUIT

		public Produit rechercherProduit(Long id);

		// RECHERCHER PRODUIT BY ID
		public Produit rechercherProduitById(Produit prod);
		
		// consulter les produits d'une catégorie
		public List<Produit> getAllProduits(Categorie categorie);
		
		// rechercher par mots clés
		public List<Produit> getParMotCle(String motCle);

}
