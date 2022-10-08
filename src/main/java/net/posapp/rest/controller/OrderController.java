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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.posapp.rest.request.OrderRequest;
import net.posapp.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(path = "/")
	public ResponseEntity<List<OrderRequest>> listAll() {
		List<OrderRequest> list = orderService.listAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<OrderRequest> findById(@PathVariable Integer id) {
		OrderRequest orderRequest = orderService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(orderRequest);
	}

	@PostMapping(path = "/")
	public ResponseEntity<OrderRequest> insert(OrderRequest orderRequest) {
		orderService.save(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderRequest);
	}

	@PutMapping(path = "/")
	public ResponseEntity<OrderRequest> update(OrderRequest orderRequest) {
		orderService.save(orderRequest);
		return ResponseEntity.status(HttpStatus.OK).body(orderRequest);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity inative(@PathVariable Integer id) {
		orderService.inactive(id);
		return ResponseEntity.ok().build();
	}

}
