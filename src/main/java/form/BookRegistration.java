package form;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import validation.ISBN;

@Getter
@Setter
public class BookRegistration {

    @NotBlank(message = "Please enter a title")
    private String bookTitle;

    @ISBN
    private String bookISBN;

    @DecimalMin(value = "0.0", inclusive = false, message = "Please enter a valid price")
    @DecimalMax(value = "100.0", inclusive = false, message = "Please enter a valid price")
    private double bookPrice;

    @NotBlank(message = "Please enter a description")
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

}
