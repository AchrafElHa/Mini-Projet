package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsnotes.bo.Etudiant;


public interface IEtudiant extends JpaRepository<Etudiant, Long> {

}


