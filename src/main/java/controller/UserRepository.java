package controller;

import org.springframework.data.repository.CrudRepository;

import model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	 User findByNameIgnoreCase(String name);
}
