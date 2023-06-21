package com.amarnath.learning.learningrestfulwebservicesproject.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amarnath.learning.learningrestfulwebservicesproject.error.UserNotFoundException;
import com.amarnath.learning.learningrestfulwebservicesproject.repository.UserDaoService;
import com.amarnath.learning.learningrestfulwebservicesproject.usersdetail.Users;

import jakarta.validation.Valid;

//@RestController
public class UserController {

	@Autowired
	private UserDaoService repo;

	public UserController(UserDaoService repo) {
		super();
		this.repo = repo;
	}

	// Get all users available in the database controller
	@GetMapping("/users")
	private List<Users> GetAllUsers() {

		List<Users> allUsers = repo.getAllUsers();

		return allUsers;

	}

	// To find a user by id controller
	@GetMapping("/users/{id}")
	private EntityModel<Users> GetUserById(@PathVariable int id) {

		Users getUserById = repo.GetUserById(id);

		if(getUserById == null) {
			throw new UserNotFoundException("USER DOES NOT EXIST WITH ID:" + id);
		}


		EntityModel<Users> entityModel =  EntityModel.of(getUserById);

		WebMvcLinkBuilder link =  linkTo(methodOn(UserController.class).getClass());

		entityModel.add(link.withRel("All-users"));

		return entityModel;

	}

	// To create a new user controller
	@PostMapping("/users")
	private ResponseEntity<Users> AddUser(@Valid @RequestBody Users body) {

		repo.addUser(body);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(body.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	// To delete a user by id controller
	@DeleteMapping("/users/{id}")
	private ResponseEntity<Users> DeleteUserById(@PathVariable int id) {

		boolean deleteUserById = repo.deleteUserById(id);

		if (!deleteUserById) {
			throw new UserCannotBeDeletedException("USER CAN'T BE DELETED WITH ID:" + id);
		}

		return ResponseEntity.noContent().build();

	}

	// To update a existing user controller
	@PutMapping("/users/{id}")
	private ResponseEntity<Users> UpdateUser(@Valid @PathVariable int id, @RequestBody Users body) {

		repo.updateUser(id, body);

		return ResponseEntity.noContent().build();

	}

}
