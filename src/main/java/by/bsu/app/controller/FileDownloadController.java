package by.bsu.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileDownloadController {
	

	public static String uploadDir = System.getProperty("user.dir",
			"C:\\Users\\User\\eclipse-FinalProject")+"/uploads";
	
	@GetMapping(value ="/download/{fileName}",produces="application/zip")
	public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
		Path path = Paths.get(uploadDir + fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	
	
	
//	@RequestMapping("/doo")
//	public String UploadPage(Model model) {
//		return "greeting";
//	}
//	
//	 @GetMapping("/download1")
//	   public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {
//
//	      File file = new File(uploadDir);
//	      InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//	      return ResponseEntity.ok()
//	            .header(HttpHeaders.CONTENT_DISPOSITION,
//	                  "attachment;filename=" + file.getName())
//	            .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
//	            .body(resource);
//	   }
	
	
	

//	@GetMapping(value = "/zip-download", produces="application/zip")
//	public void zipDownload(@RequestParam List<String> name, HttpServletResponse response) throws IOException {
//		ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
//		for (String fileName : name) {
//			File fileToZip = new File(fileName);
//			FileSystemResource resource = new FileSystemResource(uploadDir + fileName);
//			ZipEntry zipEntry = new ZipEntry(resource.getFilename());
//			zipEntry.setSize(resource.contentLength());
//			zipOut.putNextEntry(zipEntry);
//			StreamUtils.copy(resource.getInputStream(), zipOut);
//			zipOut.closeEntry();
//		}
//		zipOut.finish();
//		zipOut.close();
//		response.setStatus(HttpServletResponse.SC_OK);
//		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zipOut + "\"");
//	}
	
}

