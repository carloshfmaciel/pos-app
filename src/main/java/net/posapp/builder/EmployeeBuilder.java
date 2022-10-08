package net.posapp.builder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.posapp.orm.Address;
import net.posapp.orm.City;
import net.posapp.orm.Employee;
import net.posapp.orm.Entity;
import net.posapp.orm.EntityType;
import net.posapp.repository.CityRepository;
import net.posapp.repository.EntityTypeRepository;
import net.posapp.rest.request.EmployeeAddressRequest;
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

	public EmployeeRequest build(Employee employee) {

		EmployeeRequest employeeRequest = new EmployeeRequest();
		employeeRequest.setId(employee.getId());
		employeeRequest.setJobRole(employee.getJobRole());
		employeeRequest.setName(employee.getEntity().getName());
		employeeRequest.setEntityType(employee.getEntity().getEntityType().getType());
		employeeRequest
				.setRoles(employee.getEntity().getRoles() != null && !employee.getEntity().getRoles().isEmpty()
						? employee.getEntity().getRoles().stream().map(role -> role.getRole().getName()).collect(
								Collectors.toList())
						: Arrays.asList());
		employeeRequest.setAdmissionDate(employee.getAdmissionDate());
		employeeRequest.setStartPeriodTime(employee.getStartPeriodTime());
		employeeRequest.setEndPeriodTime(employee.getEndPeriodTime());

		EmployeeAddressRequest employeeAddressRequest = new EmployeeAddressRequest();
		employeeAddressRequest.setAddress(employee.getEntity().getAddress().getAddress());
		employeeAddressRequest.setNumber(employee.getEntity().getAddress().getNumber());
		employeeAddressRequest.setZipCode(employee.getEntity().getAddress().getZipCode());
		employeeAddressRequest.setCityId(employee.getEntity().getAddress().getCity().getId());

		employeeRequest.setAddress(employeeAddressRequest);

		return employeeRequest;
	}

	public List<EmployeeRequest> build(List<Employee> employees) {
		List<EmployeeRequest> result = employees.stream().map(employee -> this.build(employee))
				.collect(Collectors.toList());
		return result;
	}

}
