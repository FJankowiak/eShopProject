package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;


@Service("adminService")
@Transactional //POUR RENDRE LES METHODES DE LA CLASSE TRANSACTIONNELLES
public class AdminServiceImpl implements IAdminService{
	
	
	//TRANSFORMATION DE L'ASSOCIATION UML EN JAVA
	@Autowired
	private IAdminDao adminDao;
	
	//SETTER  POUR L'INJECTION DE DEPENDANCE

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	

	//METHODE CONNEXION

	@Override
	public Admin isExist(Admin a) {
		
		return adminDao.isExist(a);
	}

}
