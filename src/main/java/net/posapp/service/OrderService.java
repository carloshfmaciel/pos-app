package net.posapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.posapp.builder.OrderBuilder;
import net.posapp.orm.Order;
import net.posapp.repository.OrderRepository;
import net.posapp.rest.request.OrderRequest;

@Service
public class OrderService {

	@Autowired
	private OrderBuilder orderBuilder;

	@Autowired
	private OrderRepository orderRepository;

	public OrderRequest save(OrderRequest orderRequest) {
		Order order = orderBuilder.build(orderRequest);
		Order orderSaved = orderRepository.save(order);
		orderRequest.setOrderId(orderSaved.getId());
		return orderRequest;
	}
}
