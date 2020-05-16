package withTemplate.repositories;

import model.Author;
import model.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import withoutTemplate.repositories.AuthorRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class AuthorRepositoryJdbcTemplateImpl implements AuthorRepository {
    private JdbcTemplate template;

    //query
    private final String INSERT_BUCKET = "INSERT INTO bucket(id) VALUES (?)";
    private final String INSERT_ITEM = "INSERT INTO item(name) VALUES (name)";
    //query
    //query
    //query
    private final String BOOK_INSERT = "INSERT INTO book(name, genre, id_author) VALUES (?,?,?) ";
    //query
    private final String BOOK_DELETE = "DELETE FROM book WHERE id_author=?";

    private Map<Integer, Author> authors = new HashMap<Integer, Author>();

    public AuthorRepositoryJdbcTemplateImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Author> authorRowMapper = (ResultSet rs, int row) -> {
        Integer id = rs.getInt("id");
        if (!authors.containsKey(id)) {
            authors.put(id, Author.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .books(new ArrayList<Book>())
                    .build());
        }
        Book book = Book.builder()
                .id(rs.getInt("book_id"))
                .name(rs.getString("book_name"))
                .genre(rs.getString("genre"))
                .author(authors.get(id))
                .build();
        authors.get(id).getBooks().add(book);
        return authors.get(id);
    };

    @Override
    public Optional<Author> findByName(String name) {
        try {
            Author author = template.queryForObject(FIND_BY_NAME, new Object[]{name}, authorRowMapper);
            return Optional.ofNullable(author);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Author save() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT, new String[]{"id"});
            statement.setString(1, data.getName());
            return statement;
        }, keyHolder);
        data.setId(keyHolder.getKey().longValue());
        return data;
    }

    @Override
    public void update(Author data) {
        template.update(UPDATE, data.getName(), data.getId());
    }

    @Override
    public void delete(Long id) {
        template.update(DELETE, id);
    }

    @Override
    public Optional<Author> find(Long id) {
        template.queryForObject(FIND_BY_ID, new Object[]{id}, authorRowMapper);

        if (authors.containsKey(id)) {
            return Optional.ofNullable(authors.get(id));
        }
        return Optional.empty();

    }

    @Override
    public List<Author> findAll() {
        return template.query(FIND_ALL_QUERY, authorRowMapper);
    }
}
