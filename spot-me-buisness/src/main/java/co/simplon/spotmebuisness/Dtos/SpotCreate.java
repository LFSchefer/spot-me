package co.simplon.spotmebuisness.Dtos;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.spotmebuisness.Dtos.validators.FileSize;
import co.simplon.spotmebuisness.Dtos.validators.FileTypes;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SpotCreate(@NotBlank @Size(max = 200) String name, @Size(max = 2000) String description,
	@NotNull @Min(-90) @Max(90) Double lat, @NotNull @Min(-180) @Max(180) Double lng,
	// TODO
	// Custom Validator
	// @FilesSize(max = 2)
	// @FileType(types = {"png","jpeg"})

	@FileSize(max = 2) @FileTypes(types = {
		"jpeg", "png", "gif" }) MultipartFile image){

}
