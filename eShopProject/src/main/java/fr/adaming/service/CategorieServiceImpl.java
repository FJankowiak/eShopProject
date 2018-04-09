package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Service("categorieService")
@Transactional // toutes les méthodes de la classe sont transactional
public class CategorieServiceImpl implements ICategorieService{
	
	// transformation de l'association simple uml en java
	@Autowired
	private ICategorieDao categorieDao;
	
	// setter pour l'injection de dépendance
	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		return categorieDao.getAllCategorie();
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		return categorieDao.addCategorie(c);
	}

	@Override
	public int updateCategorie(Categorie c) {
		return categorieDao.updateCategorie(c);
	}

	@Override
	public int deleteCategorie(Categorie c) {
		return categorieDao.deleteCategorie(c);
	}

	@Override
	public Categorie getCategorie(Categorie c) {
		return categorieDao.getCategorie(c);
	}

}
