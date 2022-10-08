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
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import net.posapp.exception.NotFoundException;
import net.posapp.rest.request.EmployeeRequest;
import net.posapp.service.EmployeeService;

@RestController("/employee")
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(path = "/")
	public ResponseEntity<List<EmployeeRequest>> listAll() {
		try {
			List<EmployeeRequest> list = employeeService.listAll();
			return ResponseEntity.ok(list);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<EmployeeRequest> findById(@PathVariable Integer id) {
		try {
			EmployeeRequest employeeRequest = employeeService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(employeeRequest);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(path = "/")
	public ResponseEntity<EmployeeRequest> insert(EmployeeRequest employeeRequest) {
		try {
			employeeService.save(employeeRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(employeeRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(path = "/")
	public ResponseEntity<EmployeeRequest> update(EmployeeRequest employeeRequest) {
		try {
			employeeService.save(employeeRequest);
			return ResponseEntity.status(HttpStatus.OK).body(employeeRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity inative(@PathVariable Integer id) {
		try {
			employeeService.inactive(id);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok().build();
	}

}
