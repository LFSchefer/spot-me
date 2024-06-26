package co.simplon.spotmebuisness.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.spotmebuisness.Dtos.SpotCreate;
import co.simplon.spotmebuisness.services.SpotService;
import jakarta.validation.Valid;

@RestController
// /spots => collection of resources
@RequestMapping("/spots")
public class SpotController {

    private final SpotService service;

    public SpotController(SpotService service) {
	this.service = service;
    }

    // @Requestbody => txt/json
    @PostMapping
    public void create(@ModelAttribute @Valid SpotCreate inputs) throws FileNotFoundException, IOException {
	service.create(inputs);
    }

}
