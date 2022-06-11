package com.gsnotes.web.controllers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gsnotes.deliberation.Verification;
import com.gsnotes.bo.Etudiant;

@Controller

public class FileController {
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
		String message = "";
		if(!file.isEmpty())
		{
		  Tika tika = new Tika();
		  String detectedType = tika.detect(file.getBytes());
		  if (detectedType.equals("application/x-tika-ooxml") ) {//si le fichier est xlsx
			  Verification V = new Verification();
			  List<Etudiant> eliste= V.verifierXlsx(file);
			  if (eliste.size()==0) {
				  return "prof/ok.jsp";
			  }
			  else {
				  modelMap.put("eliste", eliste);
				  return "prof/exists.jsp";
			  }
		  }
		  else if (detectedType.equals("application/x-tika-msoffice")){//si le fichier est xlx
			  Verification V = new Verification();
			  List<Etudiant> eliste= V.verifierXlx(file);
			  if (eliste.size()==0) {
				  return "prof/ok.jsp";
			  }
			  else {
				  modelMap.put("eliste", eliste);
				  return "prof/exists.jsp";
			  }
		  }
		  else {//si le fichier n'est pas excel
			  message = "Ce fichier n'est pas un fichier excel .";
			  modelMap.put("message",message);
			  return "prof/message";
		  }
		}
		else {
			message = "Le fichier est vide .";
			modelMap.put("message",message);
			return "prof/message";
		}
	}
}
