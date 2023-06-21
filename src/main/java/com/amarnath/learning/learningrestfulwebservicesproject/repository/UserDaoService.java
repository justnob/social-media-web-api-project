package com.amarnath.learning.learningrestfulwebservicesproject.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.amarnath.learning.learningrestfulwebservicesproject.usersdetail.Users;

@Service
public class UserDaoService {

	private static List<Users> allUsers = new ArrayList<>();

	private static int count = 0;

	static {

//		allUsers.add(new Users(++count, "Amarnath Sah", LocalDate.now().plusDays(5)));
//		allUsers.add(new Users(++count, "Rahul Sah", LocalDate.now().plusDays(3)));
//		allUsers.add(new Users(++count, "Akash Sah", LocalDate.now().plusDays(2)));
//		allUsers.add(new Users(++count, "Ketan Sah", LocalDate.now().plusDays(7)));
//		allUsers.add(new Users(++count, "Niraj Sah", LocalDate.now().plusDays(9)));
//		allUsers.add(new Users(++count, "Niraj Sah", LocalDate.now().plusDays(9)));
	}

	//Get all users available repository
	public List<Users> getAllUsers() {

		return allUsers;

	}

	//To find a user by id repository
	public Users GetUserById(int id) {

		Predicate<? super Users> predicate = user -> user.getId().equals(id);
		Optional<Users> findUser = allUsers.stream().filter(predicate).findFirst();

		return findUser.orElse(null);
	}

	//To create a new user repository
	public void addUser(Users body) {

		body.setId(++count);

		allUsers.add(body);

	}

	//To update a existing user repository
	public void updateUser(int id,Users body) {

		Predicate<? super Users> predicate = b -> b.getId().equals(id);
		allUsers.removeIf(predicate);

		allUsers.add(body);

	}

	//To delete a user by id repository
	public boolean deleteUserById(int id) {

		Predicate<? super Users> predicate = b -> b.getId().equals(id);
		boolean removeIf = allUsers.removeIf(predicate);

		return removeIf;

	}



}
