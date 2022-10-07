package net.posapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.posapp.builder.ScheduleDeliveryBuilder;
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

}
