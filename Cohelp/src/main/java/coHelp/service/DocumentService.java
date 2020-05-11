package coHelp.service;

import coHelp.model.document.Document;
import coHelp.model.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

public interface DocumentService {
    @Transactional
    Document updateDocument(Document document, File file);
    @Transactional
    Document uploadDocument(Document document);

}
