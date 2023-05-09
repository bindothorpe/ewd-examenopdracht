package validator;

import domain.Location;
import form.BookRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import service.BookService;
import service.LocationService;

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
            errors.rejectValue("bookAuthor1", "bookRegistration.bookAuthor1.invalid", "Please enter at least one Author");
        }

        //Check if at least one location is filled in and valid
        handleLocationValidation(bookRegistration, errors);

        //Check if the book is already present
        if (isBookAlreadyPresent(bookRegistration)) {
            errors.rejectValue("bookISBN", "bookRegistration.bookISBN.invalid", "Book already present");
        }

    }

    private void handleLocationValidation(BookRegistration bookRegistration, Errors errors) {
        if (areAllLocationsEmpty(bookRegistration)) {
            errors.rejectValue("bookLocation3Code1", "bookRegistration.bookLocation1Code1.invalid", "Please fill in at least one location");
            return;
        }

        handleLocationValidation(1, bookRegistration.getBookLocation1Code1(), bookRegistration.getBookLocation1Code2(), bookRegistration.getBookLocation1Name(), errors);
        handleLocationValidation(2, bookRegistration.getBookLocation2Code1(), bookRegistration.getBookLocation2Code2(), bookRegistration.getBookLocation2Name(), errors);
        handleLocationValidation(3, bookRegistration.getBookLocation3Code1(), bookRegistration.getBookLocation3Code2(), bookRegistration.getBookLocation3Name(), errors);

    }

    private void handleLocationValidation(int locNumber, String locCode1, String locCode2, String name, Errors errors) {
        //Check if location 1 has an empty field
        if (isLocationNotFullyFilled(locCode1, locCode2, name)) {
            errors.rejectValue("bookLocation" + locNumber + "Code1", "bookRegistration.bookLocation" + locNumber + "Code1.invalid", "Please fill in all location " + locNumber + " fields");
            return;
        }

        if(locCode1.isBlank() && locCode2.isBlank() && name.isBlank()) {
            return;
        }

        //give me a regex that only allows letters and spaces
        if(name.matches(".*[^a-zA-Z\\s].*")) {
            errors.rejectValue("bookLocation" + locNumber + "Name", "bookRegistration.bookLocation" + locNumber + "Name.invalid", "Please enter a valid name (only letters and spaces)");
        }

        //Check if location codes are numbers
        int code1;
        int code2;
        try {
            code1 = Integer.parseInt(locCode1);
            code2 = Integer.parseInt(locCode2);
        } catch (NumberFormatException e) {
            errors.rejectValue("bookLocation" + locNumber + "Code1", "bookRegistration.bookLocation" + locNumber + "Code1.invalid", "Please enter a number");
            return;
        }

        //Check if location codes are between 50 and 300
        if(!(code1 >= 50 && code1 <= 300 && code2 >= 50 && code2 <= 300)) {
            errors.rejectValue("bookLocation" + locNumber + "Code1", "bookRegistration.bookLocation" + locNumber + "Code1.invalid", "Please enter a number between 50 and 300");
            return;
        }

        //Check if the difference between code1 and code2 is at least 50
        if(Math.abs(code1 - code2) < 50) {
            errors.rejectValue("bookLocation" + locNumber + "Code1", "bookRegistration.bookLocation" + locNumber + "Code1.invalid", "Please enter a number between 50 and 300, at least 50 apart");
            return;
        }

        //Check if the location already exists
        Location loc = new Location(code1, code2, name);
        if(loc.equals(locationService.findByLocationCode1AndLocationCode2AndLocationName(code1, code2, name))) {
            errors.rejectValue("bookLocation" + locNumber + "Code1", "bookRegistration.bookLocation" + locNumber + "Code1.invalid", "Location already exists");
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
