package com.amarnath.learning.learningrestfulwebservicesproject.error;

import java.time.LocalDateTime;

public class ErrorDetails {

	private LocalDateTime timestamp;
	private String massage;
	private String details;
	public ErrorDetails(LocalDateTime timestamp, String massage, String details) {
		super();
		this.timestamp = timestamp;
		this.massage = massage;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public String getMassage() {
		return massage;
	}
	public String getDetails() {
		return details;
	}



}
