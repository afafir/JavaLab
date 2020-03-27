package downloader.repositories;

import downloader.models.FileInfo;
import downloader.models.user.Role;
import downloader.models.user.State;
import downloader.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FileRepositoryImpl implements FileRepository {


    private static final String FIND_ALL = "SELECT * FROM file_info JOIN lab_user ON user_id=lab_file.id";
    private static final String FIND_BY_NAME = "SELECT * FROM lab_file JOIN lab_user as u  ON user_id=lab_file.id WHERE name=?";
    private static final String FIND_BY_GENERATED_NAME = "SELECT * FROM lab_file JOIN lab_user as u ON user_id=lab_file.id WHERE generatedname=?";
    private static final String FIND_BY_ID = "SELECT * FROM lab_file JOIN lab_user as u ON user_id=lab_file.id WHERE lab_file.id=? ";

    @Autowired
    JdbcTemplate template;


    private RowMapper<FileInfo> fileInfoRowMapper = (ResultSet rs, int row) -> {
        User user = User.builder().id(rs.getLong("user_id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(Role.valueOf(rs.getString("role")))
                .username(rs.getString("username"))
                .state(State.valueOf(rs.getString("state")))
                .build();

        return FileInfo.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .generatedName(rs.getString("generated_name"))
                .uploadedUser(user)
                .size(rs.getLong("size"))
                .path(rs.getString("path"))
                .state(rs.getString("state"))
                .mimeType(rs.getString("mime_type"))
                .extension(rs.getString("extension"))
                .build();
    };

    @Override
    public Optional<FileInfo> findByName(String name) {
        try {
            FileInfo fileInfo = template.queryForObject(FIND_BY_NAME, new Object[]{name}, fileInfoRowMapper);
            return Optional.of(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<FileInfo> findByGeneratedName(String generatedName) {
        try {
            FileInfo fileInfo = template.queryForObject(FIND_BY_GENERATED_NAME, new Object[]{generatedName}, fileInfoRowMapper);
            return Optional.of(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static final String INSERT = "INSERT INTO lab_file(name, generatedname, user_id,size,path,mime_type,extension) "
            + "VALUES (?,?,?,?,?,?,?,?)";

    @Override
    public FileInfo save(FileInfo data) {
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            template.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"id"});
                ps.setString(1, data.getName());
                ps.setString(2, data.getGeneratedName());
                ps.setLong(3, data.getUploadedUser().getId());
                ps.setLong(4, data.getSize());
                ps.setString(5, data.getPath());
                ps.setString(6, data.getState());
                ps.setString(7, data.getMimeType());
                ps.setString(8, data.getExtension());
                return ps;
            }, holder);
        } catch (DuplicateKeyException e) {
            data.setGeneratedName(UUID.randomUUID().toString());
            save(data);
        }
        data.setId(holder.getKey().longValue());
        return data;
    }

    @Override
    public void update(FileInfo data) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<FileInfo> find(Long id) {
        try {
            FileInfo fileInfo = template.queryForObject(FIND_BY_ID, new Object[]{id}, fileInfoRowMapper);
            return Optional.of(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FileInfo> findAll() {
        return null;
    }
}
