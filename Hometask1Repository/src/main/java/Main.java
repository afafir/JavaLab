
import model.Author;
import model.Book;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import withTemplate.repositories.AuthorRepositoryJdbcTemplateImpl;
import withoutTemplate.repositories.AuthorRepository;
import withoutTemplate.repositories.AuthorRepositoryDbImpl;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUsername("postgres");
        ds.setPassword("qwerty123");
        ds.setUrl("jdbc:postgresql://localhost:5432/Homework1");
        AuthorRepository repository = new AuthorRepositoryJdbcTemplateImpl(ds);
        //    List<Author> authorList = repository.findAll();
        // Optional<Author> author = repository.findByName("Автор 1");
        Author author1 = Author.builder().name("Автор 12201").build();
        //  List<Book> books = Arrays.asList(new Book("Книга 101", "Жанр", author1), new Book("Книга 1", "Жанр",author1));
        //author1.setBooks(books);
        repository.save(author1);
    }
}
