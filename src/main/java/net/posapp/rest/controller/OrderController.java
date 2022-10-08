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

import lombok.extern.slf4j.Slf4j;
import net.posapp.exception.NotFoundException;
import net.posapp.rest.request.OrderRequest;
import net.posapp.service.OrderService;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(path = "/")
	public ResponseEntity<List<OrderRequest>> listAll() {
		try {
			List<OrderRequest> list = orderService.listAll();
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
	public ResponseEntity<OrderRequest> findById(@PathVariable Integer id) {
		try {
			OrderRequest orderRequest = orderService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(orderRequest);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(path = "/")
	public ResponseEntity<OrderRequest> insert(OrderRequest orderRequest) {
		try {
			orderService.save(orderRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(orderRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(path = "/")
	public ResponseEntity<OrderRequest> update(OrderRequest orderRequest) {
		try {
			orderService.save(orderRequest);
			return ResponseEntity.status(HttpStatus.OK).body(orderRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity inative(@PathVariable Integer id) {
		try {
			orderService.inactive(id);
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
