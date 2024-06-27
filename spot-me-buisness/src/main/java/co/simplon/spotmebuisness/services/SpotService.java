package co.simplon.spotmebuisness.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.simplon.spotmebuisness.Dtos.SpotCreate;
import co.simplon.spotmebuisness.entities.Spot;
import co.simplon.spotmebuisness.repositories.SpotRepository;

@Service
public class SpotService {

    @Value("${spotmebusiness.uploads.dest}")
    private String dest;

    private final SpotRepository spots;

    public SpotService(SpotRepository spots) {
	this.spots = spots;
    }

    public void create(SpotCreate inputs) {
	Spot entity = new Spot();
	entity.setName(inputs.name());
	entity.setDescription(inputs.description());
	entity.setLat(inputs.lat());
	entity.setLng(inputs.lng());
	entity.setImageId(generateImageId(inputs));
	fileUpload(inputs, entity.getImageId());
	spots.save(entity);
    }

    private void fileUpload(SpotCreate inputs, String imageId) {
	File file = new File(dest.concat("/" + imageId));
	try (FileOutputStream outputStream = new FileOutputStream(file)) {
	    outputStream.write(inputs.image().getBytes());
	} catch (IOException e) {
	    e.printStackTrace();
	}
	;
    }

    private String generateImageId(SpotCreate inputs) {
	UUID uuid = UUID.randomUUID();
	return uuid.toString().concat(inputs.image().getContentType().replace("image/", "."));
    }

}
