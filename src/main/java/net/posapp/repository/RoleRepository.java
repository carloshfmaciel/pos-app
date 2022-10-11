package net.posapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.posapp.orm.Role;
import net.posapp.orm.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public User findByName(String name);
	
}
