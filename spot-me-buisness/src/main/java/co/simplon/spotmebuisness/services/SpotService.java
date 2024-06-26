package co.simplon.spotmebuisness.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.simplon.spotmebuisness.Dtos.SpotCreate;
import co.simplon.spotmebuisness.entities.Spot;
import co.simplon.spotmebuisness.repositories.SpotRepository;

@Service
public class SpotService {

    private final SpotRepository spots;

    public SpotService(SpotRepository spots) {
	this.spots = spots;
    }

    public void create(SpotCreate inputs) throws FileNotFoundException, IOException {
	Spot entity = new Spot();
	entity.setName(inputs.name());
	entity.setDescription(inputs.description());
	entity.setLat(inputs.lat());
	entity.setLng(inputs.lng());
	UUID uuid = UUID.randomUUID();
	String imageId = uuid.toString().concat(inputs.image().getContentType().replace("image/", "."));
	entity.setImageId(imageId);
	File outputFile = new File(System.getProperty("java.io.tmpdir").concat("/" + imageId));
	try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
	    outputStream.write(inputs.image().getBytes());
	}
	;
	spots.save(entity);
    }

}
