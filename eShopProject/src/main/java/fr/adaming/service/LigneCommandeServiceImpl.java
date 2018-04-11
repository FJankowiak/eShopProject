package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.LigneCommande;

@Service
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeService {
	
	//Transformation de l'association UML en JAVA
	@Autowired
	private ILigneCommandeDao lcDao;

	@Override
	public int ajouterLC(LigneCommande lc) {
		
		return lcDao.ajouterLC(lc);
	}

	@Override
	public int modifierLC(LigneCommande lc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LigneCommande> getLigneCommande() {
		
		return lcDao.getLigneCommande();
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
			
	}

}
