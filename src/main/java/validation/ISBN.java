package validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=ISBNValidator.class)
public @interface ISBN {

    String message() default "The control number of the ISBN is invalid";
    Class<?>[] groups() default {};
    public abstract Class<? extends Payload>[] payload() default {};
}
