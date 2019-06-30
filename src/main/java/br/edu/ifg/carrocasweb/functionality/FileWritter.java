package br.edu.ifg.carrocasweb.functionality;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class FileWritter {
	public static String uploadDirectory = "C:\\Repositorios\\ifg-carrocas-web\\src\\main\\resources\\static\\uploads";

	public void upload(MultipartFile[] files, long id) {
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			// Cria pasta com o id do anuncio
		
			new File(FileWritter.uploadDirectory + "/" + id).mkdir();
			Path fileNameAndPath = Paths.get(uploadDirectory, id + "/foto.jpg");
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
