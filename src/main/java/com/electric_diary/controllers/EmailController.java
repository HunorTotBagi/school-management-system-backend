package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electric_diary.entities.EmailEntity;
import com.electric_diary.services.EmailService;

@RestController
@RequestMapping(path = "/api/v1/emails")
public class EmailController {

	@Autowired
	private EmailService emailService;
	private static String PATH_TO_ATTACHMENT = "C://Users//htotbagi//Downloads//estimation_points.png";

	@PostMapping("/simpleEmail")
	public String sendSimpleMessage(@RequestBody EmailEntity object) {
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailService.sendSimpleMessage(object);
		return "Your mail has been sent!";
	}

	@PostMapping("/templateEmail")
	public String sendTemplateMessage(@RequestBody EmailEntity object) throws Exception {
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailService.sendTemplateMessage(object);
		return "Your mail has been sent!";
	}

	@PostMapping("/emailWithAttachment")
	public String sendMessageWithAttachment(@RequestBody EmailEntity object) throws Exception {
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailService.sendMessageWithAttachment(object, PATH_TO_ATTACHMENT);
		return "Your mail has been sent!";
	}
}