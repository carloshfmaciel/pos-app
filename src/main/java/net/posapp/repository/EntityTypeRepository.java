package net.posapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.posapp.orm.EntityType;

@Repository
public interface EntityTypeRepository extends JpaRepository<EntityType, Integer> {

	public EntityType findByType(String type);
	
}
