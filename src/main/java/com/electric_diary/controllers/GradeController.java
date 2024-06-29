package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electric_diary.entities.GradeDTO;
import com.electric_diary.entities.GradeEntity;
import com.electric_diary.services.GradeService;

@RestController
@RequestMapping(path = "/api/v1/grades")
public class GradeController {
	@Autowired
	private GradeService gradeService;

	@PostMapping
	public ResponseEntity<GradeEntity> assignGrade(@RequestBody GradeDTO GradeDTOBody) {
		return new ResponseEntity<>(gradeService.assignGrade(GradeDTOBody), HttpStatus.OK);
	}
}