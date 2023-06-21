package com.amarnath.learning.learningrestfulwebservicesproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amarnath.learning.learningrestfulwebservicesproject.usersdetail.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

}
