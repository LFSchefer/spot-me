package co.simplon.spotmebuisness.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.spotmebuisness.Dtos.SpotCreate;
import co.simplon.spotmebuisness.Dtos.SpotView;
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
	if (inputs.image() != null) {
	    entity.setImageId(generateImageId(inputs.image()));
	    fileUpload(inputs.image(), entity.getImageId());
	}
	spots.save(entity);
    }

    public Collection<SpotView> getAll() {
	return spots.findAllProjectedBy();
    }

    private void fileUpload(MultipartFile image, String imageId) {
	File file = new File(dest.concat("/" + imageId));
	try (FileOutputStream outputStream = new FileOutputStream(file)) {
	    outputStream.write(image.getBytes());
	} catch (IOException e) {
	    e.printStackTrace();
	}
	;
    }

    private String generateImageId(MultipartFile image) {
	UUID uuid = UUID.randomUUID();
	return uuid.toString().concat(image.getContentType().replace("image/", "."));
    }

    public void deleteOne(Long id) {
	spots.deleteById(id);
    }

    // Frank correction

//    private String buildImageId(MultipartFile image) {
//	UUID uuid = UUID.randomUUID();
//	String name = image.getOriginalFilename();
//	int index = name.lastIndexOf('.');
//	String ext = name.substring(index, name.length());
//	return uuid + ext;
//    }
//
//    private void storeImage(MultipartFile image, String imageId) {
//	try {
//	    String dest = String.format("%s/%s", uploadsDest, imageId);
//	    File file = new File(dest);
//	    image.transferTo(file);
//	} catch (Exception ex) {
//	    throw new RuntimeException(ex);
//	}
//    }

}
