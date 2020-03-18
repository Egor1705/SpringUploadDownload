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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	public static String uploadDir = System.getProperty("user.dir",
			"C:\\Users\\User\\eclipse-FinalProject")+"/uploads";
	public static final String PATH = "C:\\\\Users\\\\User\\\\eclipse-FinalProject\\\\SpringSecurityDemo\\\\SpringSecurityUser\\\\uploads\\output.zip"; 
	

	@RequestMapping("/foo")
	public String UploadPage(Model model) {
		return "index";
	}
	
	 @RequestMapping("/upload")
	  public String upload(Model model,@RequestParam("file") MultipartFile[] files) throws FileNotFoundException {
		 
		 FileOutputStream fos = null;
	        ZipOutputStream zipOut = null;
		  StringBuilder fileNames = new StringBuilder();
		 ZipEntry ze = null;
		  
		  
		  
		  
		  
		  
		  
		  for (MultipartFile file : files) {			  
			  try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(PATH));
			             FileInputStream fis= new FileInputStream("D:\\".concat(file.getOriginalFilename()));)
			     {
			        ze= new ZipEntry(file.getOriginalFilename()); 
			        zout.putNextEntry(ze);
			         // считываем содержимое файла в массив byte
			         byte[] buffer = new byte[fis.available()];
			         fis.read(buffer);
			         // добавляем содержимое к архиву
			         zout.write(buffer);
			         // закрываем текущую запись для новой записи
			         zout.closeEntry();
			     }
			     catch(Exception ex){
			           
			         System.out.println(ex.getMessage());
			     } 
			  
			  
			  
			  
			  
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
		
	
	
	

