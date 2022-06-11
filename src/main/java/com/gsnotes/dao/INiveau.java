package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsnotes.bo.Niveau;


public interface INiveau extends JpaRepository<Niveau, Long> {
	Niveau findByAlias(String alias);
}

