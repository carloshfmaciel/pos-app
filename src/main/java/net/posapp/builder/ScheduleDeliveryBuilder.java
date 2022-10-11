package net.posapp.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.posapp.constants.ScheduleDeliveryStatus;
import net.posapp.orm.Employee;
import net.posapp.orm.Order;
import net.posapp.orm.ScheduleDelivery;
import net.posapp.repository.EmployeeRepository;
import net.posapp.repository.OrderRepository;
import net.posapp.repository.ScheduleDeliveryRepository;
import net.posapp.rest.request.ScheduleDeliveryRequest;

@Component
public class ScheduleDeliveryBuilder {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ScheduleDeliveryRepository scheduleDeliveryRepository;

	public ScheduleDelivery build(ScheduleDeliveryRequest scheduleDeliveryRequest) {

		ScheduleDelivery scheduleDelivery;

		if (scheduleDeliveryRequest.getId() != null) {
			scheduleDelivery = scheduleDeliveryRepository.findById(scheduleDeliveryRequest.getId())
					.orElseThrow(() -> new IllegalArgumentException("id is invalid!"));
		} else {
			scheduleDelivery = new ScheduleDelivery();
			scheduleDelivery.setStatus(ScheduleDeliveryStatus.ACTIVE);
		}

		Order orderResult = orderRepository.findActiveOrderById(scheduleDeliveryRequest.getOrderId())
				.orElseThrow(() -> new IllegalArgumentException("orderId is invalid!"));
		Employee employeeResult = employeeRepository.findActiveEmployeeById(scheduleDeliveryRequest.getEmployeeId())
				.orElseThrow(() -> new IllegalArgumentException("employeeId is invalid!"));

		scheduleDelivery.setOrder(orderResult);
		scheduleDelivery.setEmployee(employeeResult);
		scheduleDelivery.setDeliveryDate(scheduleDeliveryRequest.getDeliveryDate());
		if(scheduleDeliveryRequest.getStatus() != null) {
			scheduleDelivery.setStatus(scheduleDeliveryRequest.getStatus());
		}
		scheduleDelivery.setDeliveredAt(scheduleDeliveryRequest.getDeliveredAt());

		return scheduleDelivery;
	}

	public ScheduleDeliveryRequest build(ScheduleDelivery scheduleDelivery) {

		ScheduleDeliveryRequest scheduleDeliveryRequest = new ScheduleDeliveryRequest();
		scheduleDeliveryRequest.setId(scheduleDelivery.getId());
		scheduleDeliveryRequest.setEmployeeId(scheduleDelivery.getEmployee().getId());
		scheduleDeliveryRequest.setOrderId(scheduleDelivery.getOrder().getId());
		scheduleDeliveryRequest.setDeliveryDate(scheduleDelivery.getDeliveryDate());
		scheduleDeliveryRequest.setStatus(scheduleDelivery.getStatus());
		scheduleDeliveryRequest.setDeliveredAt(scheduleDelivery.getDeliveredAt());

		return scheduleDeliveryRequest;
	}

	public List<ScheduleDeliveryRequest> build(List<ScheduleDelivery> scheduleDeliveries) {

		List<ScheduleDeliveryRequest> result = scheduleDeliveries.stream()
				.map(scheduleDelivery -> this.build(scheduleDelivery)).collect(Collectors.toList());

		return result;
	}

}
