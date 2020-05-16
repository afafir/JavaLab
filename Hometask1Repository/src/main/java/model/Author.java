package model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    private long id;
    private String name;
    private List<Book> books;

}
