package net.posapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.posapp.orm.Entity;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Integer> {
	
}
