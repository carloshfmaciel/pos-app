package net.posapp.builder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.posapp.constants.EntityStatus;
import net.posapp.orm.Address;
import net.posapp.orm.City;
import net.posapp.orm.Employee;
import net.posapp.orm.EntityType;
import net.posapp.repository.CityRepository;
import net.posapp.repository.EmployeeRepository;
import net.posapp.repository.EntityTypeRepository;
import net.posapp.rest.request.EmployeeRequest;
import net.posapp.rest.request.EntityAddressRequest;

@Component
public class EmployeeBuilder {

	@Autowired
	private EntityTypeRepository entityTypeRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee build(EmployeeRequest employeeRequest) {

		if (employeeRequest != null) {
			
			Employee employee = null;
			
			if (employeeRequest.getId() != null) {
				employee = employeeRepository.findById(employeeRequest.getId()).orElseThrow(() -> new IllegalArgumentException("employee id doesnt exist!"));
			}else {
				employee = new Employee();
				employee.setStatus(EntityStatus.ACTIVE);
			}
			
			employee.setName(employeeRequest.getName());

			// FIND ENTITY TYPE BY ID
			EntityType EntityType = entityTypeRepository.findByType(employeeRequest.getEntityType());
			employee.setEntityType(EntityType);
			
			if(employeeRequest.getAddress() == null) {
				throw new IllegalArgumentException("address is mandatory!");
			}

			Address address = new Address();
			address.setAddress(employeeRequest.getAddress().getAddress());
			address.setNumber(employeeRequest.getAddress().getNumber());
			address.setZipCode(employeeRequest.getAddress().getZipCode());

			Optional<City> city = cityRepository.findById(employeeRequest.getAddress().getCityId());
			address.setCity(city.isPresent() ? city.get() : null);
			employee.setAddress(address);

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
		employeeRequest.setName(employee.getName());
		employeeRequest.setEntityType(employee.getEntityType().getType());
		employeeRequest.setRoles(employee.getRoles() != null && !employee.getRoles().isEmpty()
				? employee.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
				: Arrays.asList());
		employeeRequest.setAdmissionDate(employee.getAdmissionDate());
		employeeRequest.setStartPeriodTime(employee.getStartPeriodTime());
		employeeRequest.setEndPeriodTime(employee.getEndPeriodTime());
		employeeRequest.setStatus(employee.getStatus());

		EntityAddressRequest employeeAddressRequest = new EntityAddressRequest();
		employeeAddressRequest.setAddress(employee.getAddress().getAddress());
		employeeAddressRequest.setNumber(employee.getAddress().getNumber());
		employeeAddressRequest.setZipCode(employee.getAddress().getZipCode());
		employeeAddressRequest.setCityId(employee.getAddress().getCity().getId());

		employeeRequest.setAddress(employeeAddressRequest);

		return employeeRequest;
	}

	public List<EmployeeRequest> build(List<Employee> employees) {
		List<EmployeeRequest> result = employees.stream().map(employee -> this.build(employee))
				.collect(Collectors.toList());
		return result;
	}

}
