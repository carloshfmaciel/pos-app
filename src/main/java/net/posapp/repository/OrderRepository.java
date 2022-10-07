package net.posapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.posapp.orm.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
