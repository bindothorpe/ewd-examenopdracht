package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DecimalStringValidator implements ConstraintValidator<DecimalString, String> {

    private double min;
    private double max;

    @Override
    public void initialize(DecimalString constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return isValidDecimalString(value, context);
    }

    private boolean isValidDecimalString(String value, ConstraintValidatorContext context) {
        try {
            double d = Double.parseDouble(value);
            return d > min && d < max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
