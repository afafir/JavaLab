package withoutTemplate.repositories;

import model.Author;
import model.Book;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AuthorRepositoryDbImpl implements AuthorRepository {

    //query
    private final String FIND_ALL_QUERY = "SELECT author.*, book.id AS book_id, book.name AS book_name, book.genre FROM author LEFT JOIN book ON author.id = book.id_author";
    //query
    private final String FIND_BY_NAME = "SELECT author.*, book.id AS book_id, book.name AS book_name, book.genre FROM author LEFT JOIN book ON author.id = book.id_author WHERE author.name=? ";
    //query
    private final String FIND_BY_ID = "SELECT * FROM author WHERE id=?";
    //query
    private final String INSERT = "INSERT INTO author(name) VALUES (?)";
    //query
    private final String DELETE = "DELETE FROM author WHERE id=?";
    //query
    private final String UPDATE = "UPDATE author set name=? WHERE id=?";
    //query
    private final String BOOK_INSERT = "INSERT INTO book(name, genre, id_author) VALUES (?,?,?) ";
    //query
    private final String BOOK_DELETE = "DELETE FROM book WHERE id_author=?";


    private Map<Integer, Author> authors = new HashMap<Integer, Author>();

    private RowMapper<Author> mapper = row -> {
        Integer id = row.getInt("id");
        if (!authors.containsKey(id)) {
            authors.put(id, Author.builder()
                    .id(row.getInt("id"))
                    .name(row.getString("name"))
                    .books(new ArrayList<Book>())
                    .build());
        }
        Book book = Book.builder()
                .id(row.getInt("book_id"))
                .name(row.getString("book_name"))
                .genre(row.getString("genre"))
                .author(authors.get(id))
                .build();
        authors.get(id).getBooks().add(book);
        return authors.get(id);
    };


    public Optional<Author> findByName(String name) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(FIND_BY_NAME);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapper.map(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
        return Optional.empty();
    }

    public Author save(Author author) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, author.getName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                author.setId(rs.getInt("id"));
            }
            rs.close();
            ps.close();
            for (Book book : author.getBooks()) {
                PreparedStatement psBook = conn.prepareStatement(BOOK_INSERT);
                psBook.setString(1, book.getName());
                psBook.setString(2, book.getGenre());
                psBook.setLong(3, author.getId());
                ps.executeUpdate();
                psBook.close();
            }
            return author;
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public void update(Author author) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, author.getName());
            ps.setLong(2, author.getId());
            ps.executeUpdate();
            if (author.getBooks() != null) {
                PreparedStatement psBook = conn.prepareStatement(BOOK_DELETE);
                psBook.setLong(1, author.getId());
                ps.executeUpdate();
                for (Book book : author.getBooks()) {
                    psBook = conn.prepareStatement(BOOK_INSERT);
                    psBook.setString(1, book.getName());
                    psBook.setString(2, book.getGenre());
                    psBook.setLong(3, book.getAuthor().getId());
                    psBook.executeUpdate();
                }
                psBook.close();
                ps.close();
            }
            ps.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }


    }

    public void delete(Long id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }


    }

    public Optional<Author> find(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of((mapper.map(rs)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Author> findAll() {
        List<Author> authors = new ArrayList<Author>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = mapper.map(rs);
                authors.add(author);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
