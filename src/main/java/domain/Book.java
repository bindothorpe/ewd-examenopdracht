package domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private double price;
    @Column
    private String ISBN;
    @Column
    private String coverUrl;

    @ManyToMany(mappedBy = "bookList")
    private List<User> usersList;

    @ManyToMany(mappedBy = "bookList")
    private List<Author> authors;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Location> locations;

    public Book(String title, double price, String ISBN, String coverUrl) {
        this.title = title;
        this.price = price;
        this.ISBN = ISBN;
        this.coverUrl = coverUrl;
    }
}
