package validator;

import domain.Location;
import form.BookRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import service.BookService;
import service.LocationService;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class BookRegistrationValidator implements Validator {

    @Autowired
    BookService bookService;

    @Autowired
    LocationService locationService;

    @Override
    public boolean supports(Class<?> klass) {
        return BookRegistration.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookRegistration bookRegistration = (BookRegistration) target;

        //Check if at least one author is filled in
        if (bookRegistration.getBookAuthor1().isBlank() && bookRegistration.getBookAuthor2().isBlank() && bookRegistration.getBookAuthor3().isBlank()) {
            errors.rejectValue("bookAuthor1", "error.book.authors.empty");
        }

        //Check if at least one location is filled in and valid
        handleLocationValidation(bookRegistration, errors);

        //Check if the book is already present
        if (isBookAlreadyPresent(bookRegistration)) {
            errors.rejectValue("bookISBN", "error.book.isbn.already_present");
        }

    }

    private void handleLocationValidation(BookRegistration bookRegistration, Errors errors) {
        if (areAllLocationsEmpty(bookRegistration)) {
            errors.rejectValue("bookLocation3Code1", "error.book.locations.empty");
            return;
        }

        handleLocationValidation(1, bookRegistration.getBookLocation1Code1(), bookRegistration.getBookLocation1Code2(), bookRegistration.getBookLocation1Name(), errors);
        handleLocationValidation(2, bookRegistration.getBookLocation2Code1(), bookRegistration.getBookLocation2Code2(), bookRegistration.getBookLocation2Name(), errors);
        handleLocationValidation(3, bookRegistration.getBookLocation3Code1(), bookRegistration.getBookLocation3Code2(), bookRegistration.getBookLocation3Name(), errors);

    }

    private void handleLocationValidation(int locNumber, String locCode1, String locCode2, String name, Errors errors) {
        //Check if location 1 has an empty field
        if (isLocationNotFullyFilled(locCode1, locCode2, name)) {
            errors.rejectValue("bookLocation" + locNumber + "Code1", "error.book.location.empty.field");
            return;
        }

        if(locCode1.isBlank() && locCode2.isBlank() && name.isBlank()) {
            return;
        }

        //give me a regex that only allows letters and spaces
        if(name.matches(".*[^a-zA-Z\\s].*")) {
            errors.rejectValue("bookLocation" + locNumber + "Name", "error.book.location.name.invalid");
        }

        //Check if location codes are numbers
        int code1;
        int code2;
        try {
            code1 = Integer.parseInt(locCode1);
            code2 = Integer.parseInt(locCode2);
        } catch (NumberFormatException e) {
            errors.rejectValue("bookLocation" + locNumber + "Code1", "error.book.location.code.invalid");
            return;
        }

        int min = 50;
        int max = 300;
        int diff = 50;
        //Check if location codes are between 50 and 300
        if(!(code1 >= min && code1 <= max && code2 >= min && code2 <= max)) {
            int[] args = {min, max};
            String[] strings = Arrays.stream(args)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);
            errors.rejectValue("bookLocation" + locNumber + "Code1", "error.book.location.code.range.invalid", strings, "Something went wrong");
            return;
        }

        //Check if the difference between code1 and code2 is at least 50
        if(Math.abs(code1 - code2) < diff) {
            int[] args = {min, max, diff};
            String[] strings = Arrays.stream(args)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);
            errors.rejectValue("bookLocation" + locNumber + "Code1", "error.book.location.code.difference.invalid", strings, "Something went wrong");
            return;
        }

        //Check if the location already exists
        Location loc = new Location(code1, code2, name);
        if(loc.equals(locationService.findByLocationCode1AndLocationCode2AndLocationName(code1, code2, name))) {
            errors.rejectValue("bookLocation" + locNumber + "Code1", "error.book.location.already_present");
            return;
        }


    }

    private boolean isLocationNotFullyFilled(String code1, String code2, String name) {
        if (code1.isBlank() && code2.isBlank() && name.isBlank()) {
            return false;
        }
        return code1.isBlank() || code2.isBlank() || name.isBlank();
    }

    private boolean areAllLocationsEmpty(BookRegistration bookRegistration) {
        return bookRegistration.getBookLocation1Code1().isBlank() && bookRegistration.getBookLocation1Code2().isBlank() && bookRegistration.getBookLocation1Name().isBlank() &&
                bookRegistration.getBookLocation2Code1().isBlank() && bookRegistration.getBookLocation2Code2().isBlank() && bookRegistration.getBookLocation2Name().isBlank() &&
                bookRegistration.getBookLocation3Code1().isBlank() && bookRegistration.getBookLocation3Code2().isBlank() && bookRegistration.getBookLocation3Name().isBlank();
    }

    private boolean isBookAlreadyPresent(BookRegistration bookRegistration) {
        return bookService.findByISBN(bookRegistration.getBookISBN()) != null;
    }

}
