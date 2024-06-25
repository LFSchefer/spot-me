package co.simplon.spotmebuisness.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.spotmebuisness.Dtos.SpotCreate;
import jakarta.validation.Valid;

@RestController
// /spots => collection of resources
@RequestMapping("/spots")
public class SpotController {

    // @Requestbody => txt/json
    @PostMapping
    public void create(@ModelAttribute @Valid SpotCreate inputs) {
//	System.out.println(inputs);
//	MultipartFile image = inputs.image();
//	System.out.println(inputs.image().getContentType());
//	System.out.println(image.getOriginalFilename());
//	System.out.println(image.getSize());

    }

}
