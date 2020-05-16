package downloader.services;

import downloader.models.FileInfo;
import downloader.models.user.User;
import downloader.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service("default")
public class FileServiceImpl implements FileService {


    private static final String STORAGE_PATH = "C:\\java\\JavaLab\\downloading1\\downloads\\";

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileInfo upload(CommonsMultipartFile file, User user) throws IOException {
        FileInfo info = FileInfo.builder()
                .name(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")))
                .generatedName(UUID.randomUUID().toString())
                .extension(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1))
                .size(file.getSize())
                .state("active")
                .mimeType(file.getContentType())
                .uploadedUser(user)
                .build();
        info.setPath(STORAGE_PATH + info.getGeneratedName() + "." + info.getExtension());
        info = fileRepository.save(info);
        file.transferTo(new File(info.getPath()));
        return info;
    }

    @Override
    public FileInfo download(String filename) {
        FileInfo info = fileRepository.findByName(filename).get();
        //  fileInputStream.transferTo(response.getOutputStream());
        return info;
    }
}
