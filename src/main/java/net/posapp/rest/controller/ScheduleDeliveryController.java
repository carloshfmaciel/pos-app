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

import net.posapp.rest.request.ScheduleDeliveryRequest;
import net.posapp.service.ScheduleDeliveryService;

@RestController
@RequestMapping("/schedule-deliveries")
public class ScheduleDeliveryController {

	@Autowired
	private ScheduleDeliveryService scheduleDeliveryService;

	@GetMapping(path = "/")
	public ResponseEntity<List<ScheduleDeliveryRequest>> listAll() {
		List<ScheduleDeliveryRequest> list = scheduleDeliveryService.listAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ScheduleDeliveryRequest> findById(@PathVariable Integer id) {
		ScheduleDeliveryRequest scheduleDeliveryRequest = scheduleDeliveryService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(scheduleDeliveryRequest);
	}

	@PostMapping(path = "/")
	public ResponseEntity<ScheduleDeliveryRequest> insert(ScheduleDeliveryRequest scheduleDeliveryRequest) {
		scheduleDeliveryService.save(scheduleDeliveryRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(scheduleDeliveryRequest);
	}

	@PutMapping(path = "/")
	public ResponseEntity<ScheduleDeliveryRequest> update(ScheduleDeliveryRequest scheduleDeliveryRequest) {
		scheduleDeliveryService.save(scheduleDeliveryRequest);
		return ResponseEntity.status(HttpStatus.OK).body(scheduleDeliveryRequest);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity inative(@PathVariable Integer id) {
		scheduleDeliveryService.inactive(id);
		return ResponseEntity.ok().build();
	}

}
