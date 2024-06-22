package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
		return subjectService.createSubject(subjectBody, result);
	}

	@GetMapping
	public ResponseEntity<Iterable<SubjectEntity>> getAllSubjects() {
		return subjectService.getAllSubjects();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubjectEntity> getSubjectById(@PathVariable String id) {
		return subjectService.getSubjectById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SubjectEntity> updateSubject(@PathVariable String id, @RequestBody SubjectEntity subjectBody) {
		return subjectService.updateSubject(id, subjectBody);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SubjectEntity> deleteSubject(@PathVariable String id) {
		return subjectService.deleteSubject(id);
	}
}
