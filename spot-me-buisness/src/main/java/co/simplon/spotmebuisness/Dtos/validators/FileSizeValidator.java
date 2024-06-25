package co.simplon.spotmebuisness.Dtos.validators;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile>  {
  
    private int max;
    
    @Override
    public void initialize(FileSize file) {
	max = file.max();
    }
    
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
	if (file.getSize() < max * 1000000) {
	    return true;
	}
	return false;
    }
   

}
