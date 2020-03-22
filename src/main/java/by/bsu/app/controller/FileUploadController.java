package by.bsu.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class FileUploadController {

	public static String uploadDir = System.getProperty("user.dir",
			"C:\\Users\\User\\eclipse-FinalProject")+"\\uploads";
	public static final String PATH = "C:\\\\Users\\\\User\\\\eclipse-FinalProject\\\\SpringSecurityDemo\\\\SpringSecurityUser\\\\uploads\\output.zip"; 
	
	//private static String UPLOADED_FOLDER = "E://uploadedfiles//";

	

	@RequestMapping("/foo")
	public String UploadPage(Model model) {
		return "index";
	}
	
	
	@RequestMapping("/upload")
	  public String upload(Model model,@RequestParam("file") MultipartFile[] files) throws FileNotFoundException {
		 
		  StringBuilder fileNames = new StringBuilder();
		 
		 

		  for (MultipartFile file : files) {
		  Path fileNameAndPath = Paths.get(uploadDir, file.getOriginalFilename());
		  fileNames.append(file.getOriginalFilename()+" ");
		  
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	  model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
	  return "uploadstatusview";
	
	
	}
	
}


		  

		
	
	
	

