package net.posapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.posapp.builder.EmployeeBuilder;
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
		Employee employeeSaved = employeeRepository.save(employee);
		employeeRequest.setId(employeeSaved.getEntity().getId());
		return employeeRequest;
	}

}
