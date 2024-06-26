package co.simplon.spotmebuisness.Dtos.validators;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private long max;

    @Override
    public void initialize(FileSize annotation) {
	max = annotation.max() * FileSize.ONE_MB;
	if (max <= 0) {
	    throw new IllegalArgumentException(String.format("Negatif value is not allowed ! %s", max));
	}
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
	if (file == null) {
	    return true;
	}
	return file.getSize() <= max;
    }

}
