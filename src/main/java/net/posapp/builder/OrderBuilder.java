package net.posapp.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.posapp.constants.OrderStatus;
import net.posapp.orm.User;
import net.posapp.orm.Order;
import net.posapp.repository.UserRepository;
import net.posapp.repository.OrderRepository;
import net.posapp.rest.request.OrderRequest;

@Component
public class OrderBuilder {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository entityRepository;

	public Order build(OrderRequest orderRequest) {

		Order order = new Order();

		if (orderRequest.getOrderId() != null) {
			Order orderResult = orderRepository.findById(orderRequest.getOrderId())
					.orElseThrow(() -> new IllegalArgumentException("orderId is invalid!"));
			order = orderResult;
		}else{
			order.setStatus(OrderStatus.ACTIVE);
		}

		User entityResult = entityRepository.findById(orderRequest.getCustomerId())
				.orElseThrow(() -> new IllegalArgumentException("customerId is invalid!"));
		order.setCustomer(entityResult);

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
