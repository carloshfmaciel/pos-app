package net.posapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.posapp.builder.EmployeeBuilder;
import net.posapp.constants.EntityStatus;
import net.posapp.constants.OrderStatus;
import net.posapp.exception.NotFoundException;
import net.posapp.orm.Employee;
import net.posapp.repository.EmployeeRepository;
import net.posapp.rest.request.EmployeeRequest;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeBuilder employeeBuilder;

	public EmployeeRequest save(EmployeeRequest employeeRequest) {
		Employee employee = employeeBuilder.build(employeeRequest);
		employeeRepository.save(employee);
		employeeRequest.setId(employee.getEntity().getId());
		return employeeRequest;
	}

	public List<EmployeeRequest> listAll() {
		List<Employee> employees = employeeRepository.findAll();
		if (employees.isEmpty())
			throw new NotFoundException();
		List<EmployeeRequest> employeesRequest = employeeBuilder.build(employees);
		return employeesRequest;
	}

	public EmployeeRequest findById(Integer id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException());
		EmployeeRequest employeeRequest = employeeBuilder.build(employee);
		return employeeRequest;
	}

	public void inactive(Integer id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException());
		if (employee.getEntity().getStatus().equals(OrderStatus.INACTIVE)) {
			throw new IllegalArgumentException("status is already inactive!");
		}
		employee.getEntity().setStatus(EntityStatus.INACTIVE);
		employeeRepository.save(employee);
	}

}
