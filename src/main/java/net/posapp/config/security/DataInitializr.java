package net.posapp.config.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.posapp.constants.EntityStatus;
import net.posapp.constants.RolesConst;
import net.posapp.orm.EntityType;
import net.posapp.orm.Role;
import net.posapp.orm.User;
import net.posapp.repository.EntityTypeRepository;
import net.posapp.repository.RoleRepository;
import net.posapp.repository.UserRepository;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private EntityTypeRepository entityTypeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		List<User> users = userRepository.findAll();

		if (users.isEmpty()) {
			createUser("Admin", "admin", passwordEncoder.encode("123456"), RolesConst.ROLE_ADMIN);
			createUser("Cliente", "cliente", passwordEncoder.encode("123456"), RolesConst.ROLE_CLIENT);
		}

	}

	public void createUser(String name, String email, String password, String roleName) {

		Role role = new Role(roleName);

		this.roleRepository.save(role);
		
		EntityType employeeType = entityTypeRepository.findByType("E");
		
		User user = new User(name, email, password, Arrays.asList(role));
		user.setEntityType(employeeType);
		user.setStatus(EntityStatus.ACTIVE);
		userRepository.save(user);
	}
}
