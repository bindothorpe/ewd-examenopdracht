package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DecimalStringValidator implements ConstraintValidator<DecimalString, String> {

    @Override
    public void initialize(DecimalString constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return isValidDecimalString(value);
    }

    private boolean isValidDecimalString(String value) {
        try {
            double d = Double.parseDouble(value);
            return d > 0.0 && d < 100.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
