package validator;

import form.BookRegistration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookRegistrationValidator implements Validator {

    @Override
    public boolean supports(Class<?> klass) {
        return BookRegistration.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookRegistration bookRegistration = (BookRegistration) target;

        System.out.println("Validating bookRegistration: " + bookRegistration);

        if (!isValidISBN(bookRegistration.getBookISBN())) {
            errors.rejectValue("bookISBN", "bookRegistration.bookISBN.invalid", "Invalid ISBN");
            System.out.println("Invalid ISBN " + bookRegistration.getBookISBN());
        }

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
