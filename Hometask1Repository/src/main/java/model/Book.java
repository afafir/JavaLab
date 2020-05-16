package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private long id;
    private String genre;
    private String name;
    @ToString.Exclude
    private Author author;

    // private Author author;
    public Book(String genre, String name, Author author) {
        this.genre = genre;
        this.name = name;
        this.author = author;
    }

}
