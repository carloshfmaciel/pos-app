package net.posapp.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.posapp.constants.EntityStatus;
import net.posapp.orm.Address;
import net.posapp.orm.City;
import net.posapp.orm.Entity;
import net.posapp.orm.EntityType;
import net.posapp.repository.CityRepository;
import net.posapp.repository.EntityRepository;
import net.posapp.repository.EntityTypeRepository;
import net.posapp.rest.request.EntityAddressRequest;
import net.posapp.rest.request.EntityRequest;

@Component
public class EntityBuilder {

	@Autowired
	private EntityRepository entityRepository;

	@Autowired
	private EntityTypeRepository entityTypeRepository;

	@Autowired
	private CityRepository cityRepository;

	public Entity build(EntityRequest entityRequest) {

		if (entityRequest != null) {

			Entity entity = null;

			if (entityRequest.getId() != null) {
				entity = entityRepository.findById(entityRequest.getId())
						.orElseThrow(() -> new IllegalArgumentException("id invalid!"));
			} else {
				entity = new Entity();
				entity.setStatus(EntityStatus.ACTIVE);
			}

			entity.setName(entityRequest.getName());

			// FIND ENTITY TYPE BY ID
			EntityType EntityType = entityTypeRepository.findByType(entityRequest.getEntityType());
			entity.setEntityType(EntityType);

			if(entityRequest.getAddress() == null) {
				throw new IllegalArgumentException("address is mandatory!");
			}
			
			Address address = new Address();
			address.setAddress(entityRequest.getAddress().getAddress());
			address.setNumber(entityRequest.getAddress().getNumber());
			address.setZipCode(entityRequest.getAddress().getZipCode());

			Optional<City> city = cityRepository.findById(entityRequest.getAddress().getCityId());
			address.setCity(city.isPresent() ? city.get() : null);
			entity.setAddress(address);

			return entity;
		}

		return null;

	}

	public EntityRequest build(Entity entity) {

		EntityRequest entityRequest = new EntityRequest();
		entityRequest.setId(entity.getId());
		entityRequest.setName(entity.getName());
		entityRequest.setEntityType(entity.getEntityType().getType());
		entityRequest.setStatus(entity.getStatus());

		EntityAddressRequest entityAddressRequest = new EntityAddressRequest();
		entityAddressRequest.setAddress(entity.getAddress().getAddress());
		entityAddressRequest.setNumber(entity.getAddress().getNumber());
		entityAddressRequest.setZipCode(entity.getAddress().getZipCode());
		entityAddressRequest.setCityId(entity.getAddress().getCity().getId());

		entityRequest.setAddress(entityAddressRequest);

		return entityRequest;
	}

	public List<EntityRequest> build(List<Entity> entities) {
		List<EntityRequest> result = entities.stream().map(entity -> this.build(entity))
				.collect(Collectors.toList());
		return result;
	}

}
