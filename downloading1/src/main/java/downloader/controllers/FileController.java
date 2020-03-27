package downloader.controllers;

import downloader.models.FileInfo;
import downloader.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public ModelAndView getPage1(@RequestParam("file") CommonsMultipartFile file, @RequestParam("email") String email) throws IOException {
       // System.out.println(file.getName());
        //System.out.println(file.getContentType());
        //System.out.println(file.getOriginalFilename());
        //System.out.println(file.getStorageDescription());
        //System.out.println(file.getSize());
        fileService.upload(file, null);
        return null;
    }

    @RequestMapping(value = "/files/{filename:.+}", method = RequestMethod.GET)
    public void getFile(@PathVariable("filename") String filename, HttpServletResponse response) {
        FileInfo info = fileService.download(filename.substring(0, filename.lastIndexOf(".")));
        Path file = Paths.get(info.getPath());
        if (Files.exists(file)) {
            response.setContentType(info.getMimeType());
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getFileName());
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                throw new IllegalArgumentException();
            }
        }
    }

}
