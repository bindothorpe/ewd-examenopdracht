package validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DecimalStringValidator.class)
public @interface DecimalString {

    String message() default "{error.book.price.invalid}";
    double min() default 0;
    double max() default 100;
    Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};
}
