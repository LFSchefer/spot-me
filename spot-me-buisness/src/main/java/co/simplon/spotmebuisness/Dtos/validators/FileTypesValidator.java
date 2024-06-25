package co.simplon.spotmebuisness.Dtos.validators;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileTypesValidator implements ConstraintValidator<FileTypes, MultipartFile> {

    private List<String> types;

    @Override
    public void initialize(FileTypes file) {
	types = Arrays.asList(file.types());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
	if (types.contains(file.getContentType().replace("image/", ""))) {
	    return true;
	}
	return false;
    }

}
