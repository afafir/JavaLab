package emailSender.repository;

import emailSender.model.Token;
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
public class TokenRepositoryImpl implements TokenRepository {

    private static final String FIND_BY_TOKEN = "SELECT tokens.token, users.* FROM tokens LEFT JOIN users ON tokens.id_user = users.id WHERE token=?";
    private static final String FIND_FOR_USER = "SELECT tokens.token, users.* FROM tokens LEFT JOIN users ON tokens.id_user = users.id WHERE id=?";
    private static final String INSERT_TOKEN = "INSERT INTO tokens(id_user, token) VALUES (?,?)";
    @Autowired
    JdbcTemplate template;

    private RowMapper<Token> rowMapper = (ResultSet rs, int row)->
    {
        User user = User.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .build();
        return Token.builder()
                .user(user)
                .token(rs.getString("token"))
                .build();
    };

    @Override
    public Optional<Token> findByUser(User user) {
        try {
            Token token = template.queryForObject(FIND_FOR_USER,new Object[]{user.getId()}, rowMapper);
            return Optional.of(token);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Token> findByToken(String tokenStr) {
        try {
            Token token = template.queryForObject(FIND_BY_TOKEN,new Object[]{tokenStr}, rowMapper);
            return Optional.of(token);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Token save(Token data) {
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_TOKEN);
            ps.setLong(1, data.getUser().getId());
            ps.setString(2, data.getToken());
            return ps;
        });
        return data;
    }

    @Override
    public void update(Token data) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Token> find(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Token> findAll() {
        return null;
    }
}
