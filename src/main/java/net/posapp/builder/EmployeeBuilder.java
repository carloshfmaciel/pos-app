package net.posapp.builder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.posapp.orm.Address;
import net.posapp.orm.City;
import net.posapp.orm.Employee;
import net.posapp.orm.Entity;
import net.posapp.orm.EntityType;
import net.posapp.repository.CityRepository;
import net.posapp.repository.EntityTypeRepository;
import net.posapp.rest.request.EmployeeRequest;

@Component
public class EmployeeBuilder {

	@Autowired
	private EntityTypeRepository entityTypeRepository;

	@Autowired
	private CityRepository cityRepository;

	public Employee build(EmployeeRequest employeeRequest) {

		if (employeeRequest != null) {
			Entity entity = new Entity();
			if (employeeRequest.getId() != null) {
				entity.setId(employeeRequest.getId());
			}
			entity.setName(employeeRequest.getName());

			// FIND ENTITY TYPE BY ID
			EntityType EntityType = entityTypeRepository.findByType(employeeRequest.getEntityType());
			entity.setEntityType(EntityType);

			Address address = new Address();
			address.setAddress(employeeRequest.getAddress().getAddress());
			address.setNumber(employeeRequest.getAddress().getNumber());
			address.setZipCode(employeeRequest.getAddress().getZipCode());

			Optional<City> city = cityRepository.findById(employeeRequest.getAddress().getCityId());
			address.setCity(city.isPresent() ? city.get() : null);
			entity.setAddress(address);

			Employee employee = new Employee();
			employee.setEntity(entity);

			employee.setJobRole(employeeRequest.getJobRole());
			employee.setAdmissionDate(employeeRequest.getAdmissionDate());
			employee.setStartPeriodTime(employeeRequest.getStartPeriodTime());
			employee.setEndPeriodTime(employeeRequest.getEndPeriodTime());

			return employee;
		}

		return null;

	}

}
