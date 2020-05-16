package downloader.services;

import downloader.models.FileInfo;
import downloader.models.user.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

public interface FileService {
    FileInfo upload(CommonsMultipartFile file, User user) throws IOException;

    FileInfo download(String filename);
}
