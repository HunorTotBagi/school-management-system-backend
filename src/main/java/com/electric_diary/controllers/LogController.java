package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electric_diary.services.LogService;

@RestController
@RequestMapping(path = "/api/v1/logs/download")
public class LogController {
	
	@Autowired
	private LogService logService;

	@GetMapping
	public ResponseEntity<byte[]> getLog() {
	    byte[] log = logService.obterLog();

	    return ResponseEntity.ok().body(log);
	}
}
