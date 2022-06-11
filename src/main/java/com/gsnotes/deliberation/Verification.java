package com.gsnotes.deliberation;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IEtudiant;
import com.gsnotes.dao.INiveau;
import com.gsnotes.dao.I_InscriptionAnnuelleDao;
import com.gsnotes.services.ICompteService;
import com.gsnotes.services.IEtudiantService;
@Service
public class Verification {
	@Autowired
	private IEtudiantService ietudiant;
	public List<Etudiant> verifierXlx(MultipartFile file) throws IOException {//verifier les fichiers xlx
		InputStream inputStream =  new BufferedInputStream(file.getInputStream());
		Workbook workbook = new HSSFWorkbook(inputStream);
		org.apache.poi.ss.usermodel.Sheet dataSheet = workbook.getSheetAt(0);
		List<Etudiant> eliste = new ArrayList<Etudiant>();
		int annee = Integer.parseInt(dataSheet.getRow(0).getCell(1).toString().split("/")[0]);//on retient la premiere partie du format (annee/annee)
		System.out.println(annee);
		for (int i = 5; i <= dataSheet.getLastRowNum(); i++) {
		Etudiant e = ietudiant.getEtudiantById(new Long(2) );//
		System.out.println(e);
		int lastcell = dataSheet.getRow(5).getLastCellNum();//l'indice de la derniere colonne
		int rang = Integer.parseInt(dataSheet.getRow(i).getCell(lastcell).toString());//le rang
		int moyenne = Integer.parseInt(dataSheet.getRow(i).getCell(lastcell-1).toString());
		String mention ="";
		if (moyenne>=10) {
			if (moyenne<12)mention="passable";
			else if (moyenne<14)mention="Assez-bien";
			else if(moyenne<16)mention="Bien";
			else if (moyenne<18) mention="Tres-bien";
			
		}
		else {
			mention = "Non valide";
		}
		List<InscriptionAnnuelle> liste = e.getInscriptions() ;
		boolean exists = false ;
		for (InscriptionAnnuelle inscription:liste) {
			if (inscription.getAnnee()==annee) {
				exists=true ;
			}
		}
		if (!exists) {
				InscriptionAnnuelle ins = new InscriptionAnnuelle();
				ins.setAnnee(annee);
				ins.setRang(rang);
				ins.setMention(mention);
				liste.add(ins);
				e.setInscriptions(liste);
				ietudiant.save(e);

		}
		else {
			eliste.add(e);
		}
		}
		return eliste;
	}
	public List<Etudiant> verifierXlsx(MultipartFile file) throws IOException {//verifier les fichiers xlsx
		InputStream inputStream =  new BufferedInputStream(file.getInputStream());
		Workbook workbook = new XSSFWorkbook(inputStream);
		org.apache.poi.ss.usermodel.Sheet dataSheet = workbook.getSheetAt(0);
		List<Etudiant> eliste = new ArrayList<Etudiant>();
		int annee = Integer.parseInt(dataSheet.getRow(0).getCell(1).toString().split("/")[0]);//on retient la premiere partie du format (annee/annee)
		for (int i = 5; i <= dataSheet.getLastRowNum(); i++) {
		Etudiant e = ietudiant.getEtudiantById(Long.parseLong(dataSheet.getRow(i).getCell(0).toString()) );//
		int lastcell = dataSheet.getRow(5).getLastCellNum();//l'indice de la derniere colonne
		int rang = Integer.parseInt(dataSheet.getRow(i).getCell(lastcell).toString());//le rang
		int moyenne = Integer.parseInt(dataSheet.getRow(i).getCell(lastcell-1).toString());
		String mention ="";
		if (moyenne>=10) {
			if (moyenne<12)mention="passable";
			else if (moyenne<14)mention="Assez-bien";
			else if(moyenne<16)mention="Bien";
			else if (moyenne<18) mention="Tres-bien";
			
		}
		else {
			mention = "Non valide";
		}
		List<InscriptionAnnuelle> liste = e.getInscriptions() ;
		boolean exists = false ;
		for (InscriptionAnnuelle inscription:liste) {
			if (inscription.getAnnee()==annee) {
				exists=true ;
			}
		}
		if (!exists) {
				InscriptionAnnuelle ins = new InscriptionAnnuelle();
				ins.setAnnee(annee);
				ins.setRang(rang);
				ins.setMention(mention);
				liste.add(ins);
				e.setInscriptions(liste);
				ietudiant.save(e);

		}
		else {
			eliste.add(e);
		}
		}
		return eliste;
	}
}


