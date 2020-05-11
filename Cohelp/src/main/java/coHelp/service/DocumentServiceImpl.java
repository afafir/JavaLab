package coHelp.service;

import coHelp.model.document.Document;
import coHelp.model.user.User;
import coHelp.repository.DocumentRepository;
import coHelp.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentRepository documentRepository;


    @SneakyThrows
    @Override
    public Document updateDocument(Document document, File file) {
        document = documentRepository.find(document.getId()).get();
        document.setSourceFile(file);
        document.setType(Files.probeContentType(file.toPath()));
        document.setFileName(file.getName());
        return document;

    }

    @Override
    public Document uploadDocument(Document document) {
      //  documentRepository.save(document);
        return document;
    }


}
