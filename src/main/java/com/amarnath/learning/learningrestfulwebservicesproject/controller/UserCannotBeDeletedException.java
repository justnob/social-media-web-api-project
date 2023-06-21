package com.amarnath.learning.learningrestfulwebservicesproject.controller;

@SuppressWarnings("serial")
public class UserCannotBeDeletedException extends RuntimeException {

	public UserCannotBeDeletedException(String massage){
		super(massage);
	}

}
