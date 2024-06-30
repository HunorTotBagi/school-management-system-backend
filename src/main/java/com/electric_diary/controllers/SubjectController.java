package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.services.SubjectService;

@RestController
@RequestMapping(path = "/api/v1/subjects")
public class SubjectController {

	@Autowired
	protected SubjectService subjectService;

	@PostMapping
	public ResponseEntity<SubjectEntity> createSubject(@RequestBody SubjectEntity subjectBody, BindingResult result) {
		return new ResponseEntity<>(subjectService.createSubject(subjectBody, result), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<SubjectEntity>> getAllSubjects() {
		return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubjectEntity> getSubjectById(@PathVariable String id) {
		return new ResponseEntity<>(subjectService.getSubjectById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SubjectEntity> updateSubject(@PathVariable String id, @RequestBody SubjectEntity subjectBody) {
		return new ResponseEntity<>(subjectService.updateSubject(id, subjectBody), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SubjectEntity> deleteSubject(@PathVariable String id) {
		return new ResponseEntity<>(subjectService.deleteSubject(id), HttpStatus.OK);
	}

	@PutMapping("/{subjectId}/students/{studentId}")
	public ResponseEntity<SubjectEntity> enrollStudentToSubject(@PathVariable String subjectId, @PathVariable String studentId) {
		return new ResponseEntity<>(subjectService.enrollStudentToSubject(subjectId, studentId), HttpStatus.OK);
	}
}
