package com.amarnath.learning.learningrestfulwebservicesproject.error;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String messsage) {

		super(messsage);

	}

}
