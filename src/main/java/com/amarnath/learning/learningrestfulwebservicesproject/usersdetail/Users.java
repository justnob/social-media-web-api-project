package com.amarnath.learning.learningrestfulwebservicesproject.usersdetail;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Users {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, message = "Name should be at least 4 characters or more")
	//@JsonProperty("user_name")
	private String name;

	@Past(message = "The birth date must be in past")
	//@JsonProperty("birthdate")
	private LocalDate birthdate;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<PostForUsers> posts;

	public Users() {

	}

	public Users(Integer id, String name, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setDatetime(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public List<PostForUsers> getPosts() {
		return posts;
	}

	public void setPosts(List<PostForUsers> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}






}
