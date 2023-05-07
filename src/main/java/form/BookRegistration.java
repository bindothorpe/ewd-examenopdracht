package form;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRegistration {

    @NotBlank(message = "Please enter a title")
    private String bookTitle;

    @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$", message = "Please enter a valid ISBN")
    private String bookISBN;

    @DecimalMin(value = "0.0", inclusive = false, message = "Please enter a valid price")
    @DecimalMax(value = "100.0", inclusive = false, message = "Please enter a valid price")
    private double bookPrice;

    @NotBlank(message = "Please enter a description")
    private String bookCoverUrl;

}
