package net.posapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.posapp.builder.EntityBuilder;
import net.posapp.constants.EntityStatus;
import net.posapp.constants.OrderStatus;
import net.posapp.exception.NotFoundException;
import net.posapp.orm.User;
import net.posapp.repository.UserRepository;
import net.posapp.rest.request.EntityRequest;

@Service
public class EntityService {

	@Autowired
	private UserRepository entityRepository;
	
	@Autowired
	private EntityBuilder entityBuilder;

	public EntityRequest save(EntityRequest entityRequest) {
		User entity = entityBuilder.build(entityRequest);
		entityRepository.save(entity);

		entityRequest = entityBuilder.build(entity);
		
		return entityRequest;
	}

	public List<EntityRequest> listAll() {
		List<User> entities = entityRepository.findAll();
		if (entities.isEmpty())
			throw new NotFoundException();
		List<EntityRequest> entitiesRequest = entityBuilder.build(entities);
		return entitiesRequest;
	}

	public EntityRequest findById(Integer id) {
		User entity = entityRepository.findById(id).orElseThrow(() -> new NotFoundException());
		EntityRequest entityRequest = entityBuilder.build(entity);
		return entityRequest;
	}

	public void inactive(Integer id) {
		User entity = entityRepository.findById(id).orElseThrow(() -> new NotFoundException());
		if (entity.getStatus().equals(OrderStatus.INACTIVE)) {
			throw new IllegalArgumentException("status is already inactive!");
		}
		entity.setStatus(EntityStatus.INACTIVE);
		entityRepository.save(entity);
	}


}
