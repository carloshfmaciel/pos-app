package net.posapp.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.posapp.rest.request.EntityRequest;
import net.posapp.service.EntityService;

@RestController
@RequestMapping("/entities")
public class EntityController {
	
	@Autowired
	private EntityService entityService;

	@GetMapping(path = "/")
	public ResponseEntity<List<EntityRequest>> listAll() {
		List<EntityRequest> list = entityService.listAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<EntityRequest> findById(@PathVariable Integer id) {
		EntityRequest entityRequest = entityService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(entityRequest);
	}

	@PostMapping(path = "/")
	public ResponseEntity<EntityRequest> insert(@RequestBody EntityRequest entityRequest) {
		entityRequest = entityService.save(entityRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityRequest);
	}

	@PutMapping(path = "/")
	public ResponseEntity<EntityRequest> update(@RequestBody EntityRequest entityRequest) {
		entityRequest = entityService.save(entityRequest);
		return ResponseEntity.status(HttpStatus.OK).body(entityRequest);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity inative(@PathVariable Integer id) {
		entityService.inactive(id);
		return ResponseEntity.ok().build();
	}

}
