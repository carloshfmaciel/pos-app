package net.posapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.posapp.orm.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT o FROM Order o WHERE o.id = :orderId AND o.status = 'A'")
	public Optional<Order> findActiveOrderById(@Param("orderId") Integer orderId);

}
