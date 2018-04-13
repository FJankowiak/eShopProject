package fr.adaming.service;

import java.util.List;

import fr.adaming.model.LigneCommande;

public interface ILigneCommandeService {
	
	public LigneCommande ajouterLC(LigneCommande lc);	
	
	public int modifierLC(LigneCommande lc);
	
	public List<LigneCommande> getLigneCommande();
	
	public void viderLC(LigneCommande lc);

}
