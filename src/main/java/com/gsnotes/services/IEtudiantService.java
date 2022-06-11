package com.gsnotes.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gsnotes.bo.Etudiant;

public interface IEtudiantService {
	public Etudiant getEtudiantById(Long id);
	public void save(Etudiant e);
}
