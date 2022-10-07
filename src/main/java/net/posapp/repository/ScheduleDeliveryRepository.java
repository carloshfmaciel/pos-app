package net.posapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.posapp.orm.ScheduleDelivery;

@Repository
public interface ScheduleDeliveryRepository extends JpaRepository<ScheduleDelivery, Integer> {

}
