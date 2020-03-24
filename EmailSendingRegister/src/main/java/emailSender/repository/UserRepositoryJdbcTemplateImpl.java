package emailSender.repository;

import emailSender.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String FIND_BY_NAME ="SELECT * FROM users WHERE name=?";
    private static final String FIND_BY_EMAIL ="SELECT * FROM users WHERE email=?";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String INSERT = "INSERT INTO users(name, email, password) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE users SET name=?, email=?, password=?, flag=? WHERE id=?";

    @Autowired
    JdbcTemplate template;


    private RowMapper<User> userRowMapper = (ResultSet rs, int row) -> User.builder()
            .id((long) rs.getInt("id"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .email(rs.getString("email"))
            .enabled(rs.getBoolean("flag"))
            .build();



    @Override
    public Optional<User> findByName(String name) {
        try {
            User user = template.queryForObject(FIND_BY_NAME,new Object[]{name}, userRowMapper);
            return Optional.of(user);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = template.queryForObject(FIND_BY_EMAIL,new Object[]{email}, userRowMapper);
            return Optional.ofNullable(user);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public User save(User data) {
        KeyHolder holder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"id"});
            ps.setString(1, data.getName());
            ps.setString(2, data.getEmail());
            ps.setString(3, data.getPassword());
            return ps;
        },holder);
        data.setId(holder.getKey().longValue());
        return data;
    }

    @Override
    public void update(User data) {
        template.update(UPDATE, data.getName(), data.getEmail(),  data.getPassword(),data.isEnabled(), data.getId());
    }

    @Override
    public void delete(Long id) {


    }

    @Override
    public Optional<User> find(Long id) {
        try {
            User user = template.queryForObject(FIND_BY_ID,new Object[]{id}, userRowMapper);
            return Optional.of(user);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return template.query(FIND_ALL, userRowMapper);
    }
}
