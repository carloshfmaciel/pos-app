package net.posapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.posapp.builder.ScheduleDeliveryBuilder;
import net.posapp.constants.EntityStatus;
import net.posapp.constants.OrderStatus;
import net.posapp.exception.NotFoundException;
import net.posapp.orm.ScheduleDelivery;
import net.posapp.repository.ScheduleDeliveryRepository;
import net.posapp.rest.request.ScheduleDeliveryRequest;

@Service
public class ScheduleDeliveryService {

	@Autowired
	private ScheduleDeliveryBuilder scheduleDeliveryBuilder;

	@Autowired
	private ScheduleDeliveryRepository scheduleDeliveryRepository;

	public ScheduleDeliveryRequest save(ScheduleDeliveryRequest scheduleDeliveryRequest) {
		ScheduleDelivery scheduleDelivery = scheduleDeliveryBuilder.build(scheduleDeliveryRequest);
		scheduleDeliveryRepository.save(scheduleDelivery);
		scheduleDeliveryRequest.setId(scheduleDelivery.getId());
		return scheduleDeliveryRequest;
	}

	public List<ScheduleDeliveryRequest> listAll() {
		List<ScheduleDelivery> scheduleDeliveries = scheduleDeliveryRepository.findAll();
		if (scheduleDeliveries.isEmpty())
			throw new NotFoundException();
		List<ScheduleDeliveryRequest> result = scheduleDeliveryBuilder.build(scheduleDeliveries);
		return result;
	}

	public ScheduleDeliveryRequest findById(Integer id) {
		ScheduleDelivery scheduleDelivery = scheduleDeliveryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException());
		ScheduleDeliveryRequest scheduleDeliveryRequest = scheduleDeliveryBuilder.build(scheduleDelivery);
		return scheduleDeliveryRequest;
	}

	public void inactive(Integer id) {
		ScheduleDelivery scheduleDelivery = scheduleDeliveryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException());
		if (scheduleDelivery.getStatus().equals(OrderStatus.INACTIVE)) {
			throw new IllegalArgumentException("status is already inactive!");
		}
		scheduleDelivery.setStatus(EntityStatus.INACTIVE);
		scheduleDeliveryRepository.save(scheduleDelivery);
	}
}
