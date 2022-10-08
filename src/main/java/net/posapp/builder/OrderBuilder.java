package net.posapp.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public OrderRequest build(Order order) {
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrderId(order.getId());
		orderRequest.setCustomerId(order.getCustomer().getId());
		orderRequest.setStatus(order.getStatus());
		return orderRequest;
	}

	public List<OrderRequest> build(List<Order> orders) {

		List<OrderRequest> result = orders.stream().map(order -> this.build(order)).collect(Collectors.toList());
		return result;
	}

}
