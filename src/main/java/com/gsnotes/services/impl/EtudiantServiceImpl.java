package com.gsnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnotes.bo.Compte;
import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Role;
import com.gsnotes.bo.Utilisateur;
import com.gsnotes.dao.ICompteDao;
import com.gsnotes.dao.IEtudiant;
import com.gsnotes.dao.IRoleDao;
import com.gsnotes.dao.IUtilisateurDao;
import com.gsnotes.services.ICompteService;
import com.gsnotes.services.IEtudiantService;
@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiantService{
		@Autowired
		private IEtudiant userDao;


		@Override
		public Etudiant getEtudiantById(Long id) {
			
			return userDao.getById(id);
		}


		@Override
		public void save(Etudiant e) {
			userDao.save(e);
			
		}
		

		
}
