package form;

import domain.Author;
import domain.Book;
import domain.Location;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import validation.DecimalString;
import validation.ISBN;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookRegistration {

    @NotBlank(message = "{error.book.title.empty}")
    private String bookTitle;

    @ISBN
    private String bookISBN;

    @DecimalString
    private String bookPrice;

    @NotBlank(message = "{error.book.cover.empty}")
    @URL(message = "{error.book.cover.empty}")
    private String bookCoverUrl;

    private String bookAuthor1;
    private String bookAuthor2;
    private String bookAuthor3;

    private String bookLocation1Code1;
    private String bookLocation1Code2;
    private String bookLocation1Name;

    private String bookLocation2Code1;
    private String bookLocation2Code2;
    private String bookLocation2Name;

    private String bookLocation3Code1;
    private String bookLocation3Code2;
    private String bookLocation3Name;

    public Book getBook() {
        Book b = new Book(bookTitle, Double.parseDouble(bookPrice), bookISBN, bookCoverUrl);
        b.getAuthors().addAll(getAuthors());
        b.getLocations().addAll(getLocations());
        return b;
    }

    public List<Location> getLocations() {
        List<Location> locs = new ArrayList<>();
        Location loc = getLocation(bookLocation1Code1, bookLocation1Code2, bookLocation1Name);
        if(loc != null)
            locs.add(loc);
        loc = getLocation(bookLocation2Code1, bookLocation2Code2, bookLocation2Name);
        if(loc != null)
            locs.add(loc);
        loc = getLocation(bookLocation3Code1, bookLocation3Code2, bookLocation3Name);
        if(loc != null)
            locs.add(loc);
        return locs;
    }

    private Location getLocation(String code1, String code2, String name) {
        if(!(code1.isBlank() || code2.isBlank() || name.isBlank()))
            return new Location(Integer.parseInt(code1), Integer.parseInt(code2), name);
        return null;
    }

    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        if(!bookAuthor1.isBlank())
            authors.add(new Author(bookAuthor1));
        if(!bookAuthor2.isBlank())
            authors.add(new Author(bookAuthor2));
        if(!bookAuthor3.isBlank())
            authors.add(new Author(bookAuthor3));
        return authors;
    }

}
