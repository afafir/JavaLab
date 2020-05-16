package downloader.repositories;

import downloader.models.FileInfo;

import java.util.Optional;

public interface FileRepository extends CrudRepository<Long, FileInfo> {
    Optional<FileInfo> findByName(String name);

    Optional<FileInfo> findByGeneratedName(String generatedName);

}
