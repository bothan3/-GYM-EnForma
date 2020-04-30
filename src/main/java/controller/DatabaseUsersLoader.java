package controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.User;

@Component
public class DatabaseUsersLoader {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	private void initDatabase() {
		//userRepository.save(new User("user", "pass", "ROLE_USER"));
		//userRepository.save(new User("admin", "admin", "ROLE_ADMIN"));
	}

}
