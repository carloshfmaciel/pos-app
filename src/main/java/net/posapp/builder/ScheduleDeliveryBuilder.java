package net.posapp.builder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.posapp.orm.Employee;
import net.posapp.orm.Order;
import net.posapp.orm.ScheduleDelivery;
import net.posapp.repository.EmployeeRepository;
import net.posapp.repository.OrderRepository;
import net.posapp.rest.request.ScheduleDeliveryRequest;

@Component
public class ScheduleDeliveryBuilder {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public ScheduleDelivery build(ScheduleDeliveryRequest scheduleDeliveryRequest) {
		Optional<Order> orderResult = orderRepository.findById(scheduleDeliveryRequest.getOrderId());
		Optional<Employee> employeeResult = employeeRepository.findById(scheduleDeliveryRequest.getEmployeeId());

		ScheduleDelivery scheduleDelivery = new ScheduleDelivery();
		scheduleDelivery.setId(scheduleDeliveryRequest.getId());
		scheduleDelivery.setOrder(orderResult.get());
		scheduleDelivery.setEmployee(employeeResult.get());
		scheduleDelivery.setDeliveryDate(scheduleDeliveryRequest.getDeliveryDate());
		scheduleDelivery.setStatus(scheduleDeliveryRequest.getStatus());
		scheduleDelivery.setDeliveredAt(scheduleDeliveryRequest.getDeliveredAt());

		return scheduleDelivery;
	}

}
