package co.simplon.spotmebuisness.Dtos.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = FileTypesValidator.class)
public @interface FileTypes {
    String message() default "Bad file type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] types();

}
