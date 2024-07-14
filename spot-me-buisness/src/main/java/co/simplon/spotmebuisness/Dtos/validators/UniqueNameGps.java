package co.simplon.spotmebuisness.Dtos.validators;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = UniqueNameGpsValidator.class)
public @interface UniqueNameGps {

    String[] value();

    String message() default "Name, lat and lng must be a unique couple";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
