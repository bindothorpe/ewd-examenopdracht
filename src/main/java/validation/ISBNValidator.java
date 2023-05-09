package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ISBNValidator implements ConstraintValidator<ISBN, String> {

    @Override
    public void initialize(ISBN constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return isValidISBN(value);
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
