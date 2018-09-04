package br.com.lojapet.infra;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, MultipartFile file) {
		try {
			String currentDate = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());

				
			String realPath = request.getServletContext().getRealPath("/" + baseFolder);
			String path = realPath + "/" + file.getOriginalFilename();
			String newPath = realPath + "/" + currentDate.concat(file.getOriginalFilename());
			
			
			File newFile = renameFile(file, path, newPath);
			
			writeFile(newPath, newFile);
			
			return  newFile.getName();

		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeFile(String newPath, File newFile) throws IOException {
		BufferedImage originalImage = ImageIO.read(newFile);
		BufferedImage thumbnail = Scalr.resize(originalImage,Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT , 150,150);
		ImageIO.write(thumbnail, "png", new File(newPath));
	}

	private File renameFile(MultipartFile file, String path, String newPath) throws IOException {
		file.transferTo(new File(path));
		File oldfile = new File(path);
		File newFile = new File(newPath);
		oldfile.renameTo(newFile);
		oldfile.delete();
		return newFile;
	}

}
