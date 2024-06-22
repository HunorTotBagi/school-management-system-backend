package com.electric_diary.exception;

@SuppressWarnings("serial")
public class ParentNotFoundException extends RuntimeException {
	public ParentNotFoundException(String message) {
		super(message);
	}
}
