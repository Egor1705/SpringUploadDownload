package by.bsu.app.controller;

import java.io.BufferedOutputStream;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
public class FileDownloadController {

	public static String uploadDir = System.getProperty("user.dir", "C:\\Users\\User\\eclipse-FinalProject")
			+ "/uploads";

	// @RequestMapping("/")
	// public String showFiles(Model model) {
	// File folder = new File(uploadDir);
	// File[] listOfFiles = folder.listFiles();
	// model.addAttribute("files", listOfFiles);
	// return "greeting";
	// }
	//
	//
	@RequestMapping("/sh")
	public String showFiles(Model model) {
		File folder = new File(uploadDir);
		File[] listOfFiles = folder.listFiles();
		model.addAttribute("files", listOfFiles);
		return "showFiles";
	}

	// @RequestMapping("/downloadFile/{fileName}")
	// @ResponseBody
	// public void show(@PathVariable("fileName") String fileName,
	// HttpServletResponse response) {
	// if (fileName.substring(fileName.length()-4,
	// fileName.length()).equals(".txt"))
	// response.setContentType("application/txt");
	// if (fileName.substring(fileName.length()-4,
	// fileName.length()).equals(".pdf"))
	// response.setContentType("application/pdf");
	// if (fileName.substring(fileName.length()-4,
	// fileName.length()).equals(".zip"))
	// response.setContentType("application/zip");
	// response.setHeader("Content-Disposition", "attachment; filename=" +fileName);
	// response.setHeader("Content-Transfer-Encoding", "binary");
	// try {
	// BufferedOutputStream bos = new
	// BufferedOutputStream(response.getOutputStream());
	// FileInputStream fis = new FileInputStream(uploadDir+fileName);
	// int len;
	// byte[] buf = new byte[1024];
	// while((len = fis.read(buf)) > 0) {
	// bos.write(buf,0,len);
	// }
	// bos.close();
	// response.flushBuffer();
	// }
	// catch(IOException e) {
	// e.printStackTrace();
	// }
	//// }
	////
	// }

	// good example!

	@RequestMapping(value = "downloadFile/{fileName..}", method = RequestMethod.GET)
	public StreamingResponseBody getSteamingFile(@PathVariable("fileName..") String fileName,
			HttpServletResponse response) throws IOException {

		 response.setContentType("application/txt");
		response.setContentType("application/pdf");
		 response.setContentType("application/docx");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		

		return outputStream -> {
			int nRead;
			InputStream inputStream1 = null;
			InputStream inputStream2 = null;
			byte[] data = new byte[1024];
			File file1 = new File(uploadDir + "\\" + fileName);
			System.out.println(file1.getAbsoluteFile().toString());
			

			if (file1.getAbsoluteFile().toString().substring(file1.getAbsoluteFile().toString().length() - 4)
					.equals(".pdf")) {
				inputStream1 = new FileInputStream(file1);

				while ((nRead = inputStream1.read(data, 0, data.length)) != -1) {
					System.out.println("Writing some bytes..");
					outputStream.write(data, 0, nRead);
				}

			}
			
			else if (file1.getAbsoluteFile().toString().substring(file1.getAbsoluteFile().toString().length() - 4)
					.equals(".txt")) {
				inputStream2 = new FileInputStream(file1);

				while ((nRead = inputStream2.read(data, 0, data.length)) != -1) {
					System.out.println("Writing some bytes..");
					outputStream.write(data, 0, nRead);
				}

			}

			else if (file1.getAbsoluteFile().toString().substring(file1.getAbsoluteFile().toString().length() - 5)
					.equals(".docx")) {
				inputStream2 = new FileInputStream(file1);

				while ((nRead = inputStream2.read(data, 0, data.length)) != -1) {
					System.out.println("Writing some bytes..");
					outputStream.write(data, 0, nRead);
				}

			}

		};

	}

}
