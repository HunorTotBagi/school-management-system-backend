package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electric_diary.entities.GradeDTO;
import com.electric_diary.entities.GradeEntity;
import com.electric_diary.services.GradeService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping(path = "/api/v1/grades")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class GradeController {
	@Autowired
	private GradeService gradeService;

	@PostMapping
	public ResponseEntity<GradeEntity> assignGrade(@RequestBody GradeDTO gradeDTOBody) {
		return new ResponseEntity<>(gradeService.assignGrade(gradeDTOBody), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<GradeEntity>> getAllGrades() {
		return new ResponseEntity<>(gradeService.getAllGrades(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GradeEntity> getGradeById(@PathVariable String id) {
		return new ResponseEntity<>(gradeService.getGradeById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GradeEntity> updateGrade(@PathVariable String id, @RequestBody GradeDTO gradeDTOBody) {
		return new ResponseEntity<>(gradeService.updateGrade(id, gradeDTOBody), HttpStatus.OK);
	}
}