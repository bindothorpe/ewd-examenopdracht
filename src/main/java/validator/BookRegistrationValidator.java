package validator;

import form.BookRegistration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.Map;

public class BookRegistrationValidator implements Validator {

    @Override
    public boolean supports(Class<?> klass) {
        return BookRegistration.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookRegistration bookRegistration = (BookRegistration) target;

        //Fill map with all numeric fields
        Map<String, String> numericFields = new HashMap<>();
        numericFields.put("bookLocation1Code1", bookRegistration.getBookLocation1Code1());
        numericFields.put("bookLocation1Code2", bookRegistration.getBookLocation1Code2());
        numericFields.put("bookLocation2Code1", bookRegistration.getBookLocation2Code1());
        numericFields.put("bookLocation2Code2", bookRegistration.getBookLocation2Code2());
        numericFields.put("bookLocation3Code1", bookRegistration.getBookLocation3Code1());
        numericFields.put("bookLocation3Code2", bookRegistration.getBookLocation3Code2());


        System.out.println("Errors bookRegistration: " + errors.getAllErrors());

        //Check if isbn is a valid isbn
        if (!isValidISBN(bookRegistration.getBookISBN())) {
            errors.rejectValue("bookISBN", "bookRegistration.bookISBN.invalid", "Invalid ISBN");
        }

        //Check if at least one author is filled in
        if (bookRegistration.getBookAuthor1().isBlank() && bookRegistration.getBookAuthor2().isBlank()) {
            errors.rejectValue("bookAuthor1", "bookRegistration.bookAuthor1.invalid", "Please enter at least one Author");
        }

        //Check if at least one location is filled in
        if(areAllLocationsEmpty(bookRegistration)) {
            errors.rejectValue("bookLocation3Code1", "bookRegistration.bookLocation1Code1.invalid", "Please fill in at least one location");

        } else {
            //Check if location 1 has an empty field
            if(isLocation1NotFullyFilled(bookRegistration)) {
                errors.rejectValue("bookLocation1Code1", "bookRegistration.bookLocation1Code1.invalid", "Please fill in all location 1 fields");
            } else if((!bookRegistration.getBookLocation1Code1().isBlank()) && (!bookRegistration.getBookLocation1Code2().isBlank()) && (!areLocation1CodesBetween50And300(bookRegistration))) {
                errors.rejectValue("bookLocation1Code1", "bookRegistration.bookLocation1Code1.invalid", "Please enter a number between 50 and 300, at least 50 appart");
            }
            //Check if location 2 has an empty field
            if (isLocation2NotFullyFilled(bookRegistration)) {
                errors.rejectValue("bookLocation2Code1", "bookRegistration.bookLocation2Code1.invalid", "Please fill in all location 2 fields");
            }else if((!bookRegistration.getBookLocation2Code1().isBlank()) && (!bookRegistration.getBookLocation2Code2().isBlank()) && (!areLocation2CodesBetween50And300(bookRegistration))) {
                errors.rejectValue("bookLocation2Code1", "bookRegistration.bookLocation2Code1.invalid", "Please enter a number between 50 and 300, at least 50 appart");
            }
            //Check if location 3 has an empty field
            if (isLocation3NotFullyFilled(bookRegistration)) {
                errors.rejectValue("bookLocation3Code1", "bookRegistration.bookLocation3Code1.invalid", "Please fill in all location 3 fields");
            }else if((!bookRegistration.getBookLocation3Code1().isBlank()) && (!bookRegistration.getBookLocation3Code2().isBlank()) && (!areLocation3CodesBetween50And300(bookRegistration))) {
                errors.rejectValue("bookLocation3Code1", "bookRegistration.bookLocation3Code1.invalid", "Please enter a number between 50 and 300, at least 50 appart");
            }
        }





        for(Map.Entry<String, String> e : numericFields.entrySet()) {
            if(!e.getValue().isBlank()) {
                try {
                    Integer.parseInt(e.getValue());
                } catch (NumberFormatException ex) {
                    errors.rejectValue(e.getKey(), "bookRegistration." + e.getKey() + ".invalid", "Please enter a valid number");
                }
            }
        }

    }

    private boolean isLocation1NotFullyFilled(BookRegistration bookRegistration) {
        if(bookRegistration.getBookLocation1Code1().isBlank() && bookRegistration.getBookLocation1Code2().isBlank() && bookRegistration.getBookLocation1Name().isBlank()) {
            return false;
        }
        return bookRegistration.getBookLocation1Code1().isBlank() || bookRegistration.getBookLocation1Code2().isBlank() || bookRegistration.getBookLocation1Name().isBlank();
    }

    private boolean isLocation2NotFullyFilled(BookRegistration bookRegistration) {
        if(bookRegistration.getBookLocation2Code1().isBlank() && bookRegistration.getBookLocation2Code2().isBlank() && bookRegistration.getBookLocation2Name().isBlank()) {
            return false;
        }
        return bookRegistration.getBookLocation2Code1().isBlank() || bookRegistration.getBookLocation2Code2().isBlank() || bookRegistration.getBookLocation2Name().isBlank();
    }

    private boolean isLocation3NotFullyFilled(BookRegistration bookRegistration) {
        if(bookRegistration.getBookLocation3Code1().isBlank() && bookRegistration.getBookLocation3Code2().isBlank() && bookRegistration.getBookLocation3Name().isBlank()) {
            return false;
        }
        return bookRegistration.getBookLocation3Code1().isBlank() || bookRegistration.getBookLocation3Code2().isBlank() || bookRegistration.getBookLocation3Name().isBlank();
    }

    private boolean areAllLocationsEmpty(BookRegistration bookRegistration) {
        return bookRegistration.getBookLocation1Code1().isBlank() && bookRegistration.getBookLocation1Code2().isBlank() && bookRegistration.getBookLocation1Name().isBlank() &&
                bookRegistration.getBookLocation2Code1().isBlank() && bookRegistration.getBookLocation2Code2().isBlank() && bookRegistration.getBookLocation2Name().isBlank() &&
                bookRegistration.getBookLocation3Code1().isBlank() && bookRegistration.getBookLocation3Code2().isBlank() && bookRegistration.getBookLocation3Name().isBlank();
    }

    private boolean areLocation1CodesBetween50And300(BookRegistration bookRegistration) {
        int code1 = Integer.parseInt(bookRegistration.getBookLocation1Code1());
        int code2 = Integer.parseInt(bookRegistration.getBookLocation1Code2());
        return areLocationCodesBetween50And300AndAtleast50Appart(code1, code2);
    }

    private boolean areLocation2CodesBetween50And300(BookRegistration bookRegistration) {
        int code1 = Integer.parseInt(bookRegistration.getBookLocation2Code1());
        int code2 = Integer.parseInt(bookRegistration.getBookLocation2Code2());
        return areLocationCodesBetween50And300AndAtleast50Appart(code1, code2);
    }

    private boolean areLocation3CodesBetween50And300(BookRegistration bookRegistration) {
        int code1 = Integer.parseInt(bookRegistration.getBookLocation3Code1());
        int code2 = Integer.parseInt(bookRegistration.getBookLocation3Code2());
        return areLocationCodesBetween50And300AndAtleast50Appart(code1, code2);
    }

    private boolean areLocationCodesBetween50And300AndAtleast50Appart(int locationCode1, int locationCode2) {
        return locationCode1 >= 50 && locationCode1 <= 300 && locationCode2 >= 50 && locationCode2 <= 300 && Math.abs(locationCode1 - locationCode2) >= 50;
    }


    private boolean isValidISBN(String isbn) {
        // Remove all non-digit characters
        String digits = isbn.replaceAll("[^\\d]", "");

        // Check if the length is 13
        if (digits.length() != 13)
            return false;


        // Calculate the check digit
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Integer.parseInt(digits.substring(i, i + 1));
            sum += (i % 2 == 0) ? digit : 3 * digit;
        }
        int checkDigit = (10 - (sum % 10)) % 10;

        // Check if the check digit matches the last digit of the input
        return checkDigit == Integer.parseInt(digits.substring(12));
    }

}
