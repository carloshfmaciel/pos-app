package net.posapp.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.posapp.constants.RolesConst;
import net.posapp.rest.request.EmployeeRequest;
import net.posapp.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(path = "/")
	public ResponseEntity<List<EmployeeRequest>> listAll() {
		List<EmployeeRequest> list = employeeService.listAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<EmployeeRequest> findById(@PathVariable Integer id) {
		EmployeeRequest employeeRequest = employeeService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeRequest);
	}

	@Secured({RolesConst.ROLE_ADMIN})
	@PostMapping(path = "/")
	public ResponseEntity<EmployeeRequest> insert(@RequestBody EmployeeRequest employeeRequest) {
		employeeService.save(employeeRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeRequest);
	}

	@Secured({RolesConst.ROLE_ADMIN})
	@PutMapping(path = "/")
	public ResponseEntity<EmployeeRequest> update(@RequestBody EmployeeRequest employeeRequest) {
		employeeService.save(employeeRequest);
		return ResponseEntity.status(HttpStatus.OK).body(employeeRequest);
	}

	@Secured({RolesConst.ROLE_ADMIN})
	@DeleteMapping(path = "/{id}")
	public ResponseEntity inative(@PathVariable Integer id) {
		employeeService.inactive(id);
		return ResponseEntity.ok().build();
	}

}
