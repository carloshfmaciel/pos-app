package net.posapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.posapp.builder.OrderBuilder;
import net.posapp.constants.OrderStatus;
import net.posapp.exception.NotFoundException;
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

	public List<OrderRequest> listAll() {
		List<Order> orders = orderRepository.findAll();
		if (orders.isEmpty())
			throw new NotFoundException();
		List<OrderRequest> result = orderBuilder.build(orders);
		return result;
	}

	public OrderRequest findById(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException());
		OrderRequest result = orderBuilder.build(order);
		return result;
	}

	public void inactive(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException());
		if(order.getStatus().equals(OrderStatus.INACTIVE)) {
			throw new IllegalArgumentException("status is already inactive!");
		}
		order.setStatus(OrderStatus.INACTIVE);
		orderRepository.save(order);
	}
}
