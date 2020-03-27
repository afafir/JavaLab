package downloader.models;

import downloader.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FileInfo {
    private Long id;
    private String name;
    private String generatedName;
    private User uploadedUser;
    private Long size;
    private String path;
    private String state;
    private String mimeType;
    private String extension;

}
