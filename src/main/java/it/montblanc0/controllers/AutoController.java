package it.montblanc0.controllers;

import it.montblanc0.entities.Auto;
import it.montblanc0.repos.AutoRepository;
import it.montblanc0.response.ResponseHandler;
import it.montblanc0.services.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Validated
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auto")
public class AutoController {

	@Autowired
	private AutoService autoService;
	@Autowired
	private AutoRepository autoRepository;

	@GetMapping(value = "/all")
	public List<Auto> getAll() {
		return autoService.findAll();
	}


	//  Send A JSON like this:
	//	{
	//		"marca": "BMW",
	//			"modello": "X3",
	//			"prezzo": 16850,
	//			"colore": "Nera",
	//			"alimentazione": "DIESEL",
	//			"motore": {
	//						"id": 20
	//					  }
	//	}
	@PostMapping("/insert")
	public ResponseEntity<Object> insert(@RequestBody Auto auto) {
		Long id;
		try {
			id = autoService.save(auto);
		} catch (IllegalArgumentException e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, auto);
		}
		return ResponseHandler.generateResponse("Auto was successfully saved", HttpStatus.OK, autoService.getById(id));
	}

	@PutMapping("/update-price/{id}")
	public ResponseEntity<Object> updatePrice(@PathVariable("id") Long id,
	                                          @RequestBody Auto auto) {
		Optional<Auto> autoOptional = autoRepository.findById(id);
		if (autoOptional.isPresent()) {
			Auto newAuto = autoOptional.get();
			newAuto.setPrezzo(auto.getPrezzo());
			autoService.update(id, newAuto);
			return ResponseHandler.generateResponse("Price was successfully updated", HttpStatus.OK, autoService.getById(id));
		}
		return ResponseHandler.generateResponse("No entity found with that ID", HttpStatus.NOT_FOUND, auto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") Long id,
	                                     @RequestBody Auto auto) {
		Optional<Auto> autoOptional = autoRepository.findById(id);
		if (autoOptional.isPresent()) {
			autoService.update(id, auto);
			return ResponseHandler.generateResponse("Auto was successfully updated", HttpStatus.OK, autoService.getById(id));
		}
		return ResponseHandler.generateResponse("No entity found with that ID", HttpStatus.NOT_FOUND, auto);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		Optional<Auto> autoOptional = autoRepository.findById(id);
		if (autoOptional.isPresent()) {
			autoService.delete(id);
			return ResponseHandler.generateResponse("Auto was successfully deleted", HttpStatus.OK, null);
		}
		return ResponseHandler.generateResponse("No entity found with that ID", HttpStatus.NOT_FOUND, null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findOneWithURI(@PathVariable Long id) {
		Optional<Auto> autoOptional = autoRepository.findById(id);
		Auto auto = null;
		Link link = linkTo(methodOn(AutoController.class).findOneWithURI(id)).withSelfRel();
		if (autoOptional.isPresent()) {
			auto = autoOptional.get();
			return ResponseHandler.generateResponseWithURI("Auto with id:" + id + " successfully retrieved", HttpStatus.OK, auto, link);
		} else {
			return ResponseHandler.generateResponse("No entity found with that ID", HttpStatus.NOT_FOUND, auto);
		}
	}

}
