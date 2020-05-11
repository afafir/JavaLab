package coHelp.model.document;

import coHelp.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.File;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_document", schema = "cohelp1")

@Inheritance(
        strategy = InheritanceType.JOINED
)
@Slf4j
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String path;
    private Long size;

    @Transient
    private String fileName;
    @Transient
    private String extension;
    @Transient
    private File sourceFile;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @PostLoad
    public void loadFile() {
        // persistent(path) -> transient(sourceFile, fileName)
        log.info("postload");
        sourceFile = new File(path);
        fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
        extension = sourceFile.getName().substring(sourceFile.getName().lastIndexOf("."));
    }

    @PreUpdate
    public void updateFileInformation() {
        // transient(sourceFile) -> persistent(path, size)
        log.info("preupdate");
        this.path = sourceFile.getPath();
        this.size = sourceFile.length();
        this.fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
    }


    @PrePersist
    public void loadFileInformation() {
        // transient(sourceFile) -> persistent(path, size)
        this.path = sourceFile.getPath();
        this.size = sourceFile.length();
        this.fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
    }

}
