package net.posapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.posapp.orm.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
