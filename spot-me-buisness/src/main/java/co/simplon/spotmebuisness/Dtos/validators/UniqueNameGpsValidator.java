package co.simplon.spotmebuisness.Dtos.validators;

import co.simplon.spotmebuisness.Dtos.SpotCreate;
import co.simplon.spotmebuisness.repositories.SpotRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueNameGpsValidator implements ConstraintValidator<UniqueNameGps, Object> {
    String[] fields;

    private final SpotRepository repo;

    public UniqueNameGpsValidator(String[] fields, SpotRepository repo) {
	this.fields = fields;
	this.repo = repo;
    }

    @Override
    public void initialize(UniqueNameGps annotation) {
	fields = annotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
	try {
	    SpotCreate spot = (SpotCreate) value;
	    String name = spot.name();
	    if (name.isEmpty()) {
		return true;
	    }
	    double lat = spot.lat();
	    double lng = spot.lng();
	    return repo.findAllProjectedByNameAndLatAndLng(name, lat, lng).isEmpty();
	} catch (Exception e) {
	    return true;
	}
    }
}
