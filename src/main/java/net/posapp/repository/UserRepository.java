package net.posapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.posapp.orm.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
}
