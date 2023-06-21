package com.amarnath.learning.learningrestfulwebservicesproject.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amarnath.learning.learningrestfulwebservicesproject.error.UserNotFoundException;
import com.amarnath.learning.learningrestfulwebservicesproject.repository.UserRepository;
import com.amarnath.learning.learningrestfulwebservicesproject.usersdetail.PostForUsers;
import com.amarnath.learning.learningrestfulwebservicesproject.usersdetail.Users;

import jakarta.validation.Valid;

@RestController
public class UserControllerWithJpa {

	@Autowired
	private UserRepository userRepository;

	public UserControllerWithJpa(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	// Get all users available in the database controller
	@GetMapping("/users")
	private List<Users> GetAllUsers() {

		List<Users> allUsers = userRepository.findAll();

		return allUsers;

	}

	// To find a user by id controller
	@GetMapping("/users/{id}")
	private EntityModel<Users> GetUserById(@PathVariable int id) {

		Optional<Users> getUserById = userRepository.findById(id);

		if(getUserById.isEmpty()) {
			throw new UserNotFoundException("USER DOES NOT EXIST WITH ID:" + id);
		}


		EntityModel<Users> entityModel =  EntityModel.of(getUserById.get());

		WebMvcLinkBuilder link =  linkTo(methodOn(UserControllerWithJpa.class).getClass());

		entityModel.add(link.withRel("All-users"));

		return entityModel;

	}

	// To create a new user controller
	@PostMapping("/users")
	private ResponseEntity<Users> AddUser(@Valid @RequestBody Users body) {

		userRepository.save(body);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(body.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	// To delete a user by id controller
	@DeleteMapping("/users/{id}")
	private ResponseEntity<Users> DeleteUserById(@PathVariable int id) {

		userRepository.deleteById(id);

		Optional<Users> getUserById = userRepository.findById(id);

		if (getUserById.isPresent()) {
			throw new UserCannotBeDeletedException("USER CAN'T BE DELETED WITH ID:" + id);
		}

		return ResponseEntity.noContent().build();

	}
	
	@GetMapping("/users/{id}/post")
	private List<PostForUsers> GetPostByUserById(@PathVariable int id) {

		Optional<Users> getUserById = userRepository.findById(id);

		if(getUserById.isEmpty()) {
			throw new UserNotFoundException("USER DOES NOT EXIST WITH ID:" + id);
		}
		
		return getUserById.get().getPosts();

	}

	// To update a existing user controller
	@PutMapping("/users/{id}")
	private ResponseEntity<Users> UpdateUser(@Valid @PathVariable int id, @RequestBody Users body) {

		userRepository.deleteById(id);

		body.setId(id);

		userRepository.save(body);

		return ResponseEntity.noContent().build();

	}

}
