package net.posapp.builder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.posapp.orm.Entity;
import net.posapp.orm.Order;
import net.posapp.repository.EntityRepository;
import net.posapp.repository.OrderRepository;
import net.posapp.rest.request.OrderRequest;

@Component
public class OrderBuilder {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EntityRepository entityRepository;

	public Order build(OrderRequest orderRequest) {

		Order order = new Order();

		Optional<Order> orderResult = orderRepository.findById(orderRequest.getOrderId());
		if (orderResult.isPresent()) {
			order = orderResult.get();
		}

		Optional<Entity> entityResult = entityRepository.findById(orderRequest.getCustomerId());
		if (entityResult.isPresent()) {
			order.setCustomer(entityResult.get());
		}

		return order;
	}

}
