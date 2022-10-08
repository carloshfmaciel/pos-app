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
import net.posapp.rest.request.ScheduleDeliveryRequest;
import net.posapp.service.ScheduleDeliveryService;

@RestController
@RequestMapping("/schedule-deliveries")
@Slf4j
public class ScheduleDeliveryController {

	@Autowired
	private ScheduleDeliveryService scheduleDeliveryService;

	@GetMapping(path = "/")
	public ResponseEntity<List<ScheduleDeliveryRequest>> listAll() {
		try {
			List<ScheduleDeliveryRequest> list = scheduleDeliveryService.listAll();
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
	public ResponseEntity<ScheduleDeliveryRequest> findById(@PathVariable Integer id) {
		try {
			ScheduleDeliveryRequest scheduleDeliveryRequest = scheduleDeliveryService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(scheduleDeliveryRequest);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(path = "/")
	public ResponseEntity<ScheduleDeliveryRequest> insert(ScheduleDeliveryRequest scheduleDeliveryRequest) {
		try {
			scheduleDeliveryService.save(scheduleDeliveryRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(scheduleDeliveryRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(path = "/")
	public ResponseEntity<ScheduleDeliveryRequest> update(ScheduleDeliveryRequest scheduleDeliveryRequest) {
		try {
			scheduleDeliveryService.save(scheduleDeliveryRequest);
			return ResponseEntity.status(HttpStatus.OK).body(scheduleDeliveryRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity inative(@PathVariable Integer id) {
		try {
			scheduleDeliveryService.inactive(id);
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
