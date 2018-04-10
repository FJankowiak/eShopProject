package fr.adaming.model;

import java.util.List;

public class Panier {
	
	// Transformation de l'association UML en JAVA
	private List<LigneCommande> listeLignesCommande;

	public Panier() {
		super();
	}

	public List<LigneCommande> getListeLignesCommande() {
		return listeLignesCommande;
	}

	public void setListeLignesCommande(List<LigneCommande> listeLignesCommande) {
		this.listeLignesCommande = listeLignesCommande;
	}
	
	

}
