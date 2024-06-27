package co.simplon.spotmebuisness.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.spotmebuisness.Dtos.SpotCreate;
import co.simplon.spotmebuisness.Dtos.SpotView;
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
    public void create(@ModelAttribute @Valid SpotCreate inputs) {
	service.create(inputs);
    }

    @GetMapping
    public Collection<SpotView> getAll() {
	return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
	service.deleteOne(id);
    }

}
