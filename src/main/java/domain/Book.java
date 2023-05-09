package domain;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Book.findMostSavedList", query = "SELECT b FROM Book b ORDER BY SIZE(b.usersList) DESC, b.title ASC"),
        }

)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double price;
    private String ISBN;
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
        usersList = new ArrayList<>();
        authors = new ArrayList<>();
        locations = new ArrayList<>();
    }

    public String getAuthorsAsString() {
        if (authors.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Author author : authors) {
            sb.append(author.getName()).append(", ");
        }
        return sb.substring(0, sb.toString().length() - 2);
    }

}
