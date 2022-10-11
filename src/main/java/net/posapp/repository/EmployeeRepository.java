package net.posapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.posapp.orm.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e WHERE e.entityType.type = 'E' AND e.status = 'A' AND e.id = :employeeId ")
	public Optional<Employee> findActiveEmployeeById(@Param("employeeId") Integer employeeId);

}
