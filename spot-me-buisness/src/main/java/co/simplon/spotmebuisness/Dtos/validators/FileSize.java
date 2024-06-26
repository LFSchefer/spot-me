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
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {
    String message() default "The file is too large";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    static final long ONE_MB = 1024 * 1024;

    static final long TWO_MB = ONE_MB * 2;

    long max() default FileSize.TWO_MB;

}
