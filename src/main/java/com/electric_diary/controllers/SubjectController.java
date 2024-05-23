package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.services.SubjectService;

@RestController
@RequestMapping(path = "/api/v1/subjects")
public class SubjectController {

	@Autowired
	protected SubjectService subjectService;

	@PostMapping
	public SubjectEntity createSubject(@RequestBody SubjectEntity subjectBody) {
		return subjectService.createSubject(subjectBody);
	}

	@GetMapping
	public Iterable<SubjectEntity> getAllSubjects() {
		return subjectService.getAllSubjects();
	}

	@GetMapping("/{id}")
	public SubjectEntity getSubjectById(@PathVariable String id) {
		return subjectService.getSubjectById(id);
	}

	@PutMapping("/{id}")
	public SubjectEntity updateSubject(@PathVariable String id, @RequestBody SubjectEntity subjectBody) {
		return subjectService.updateSubject(id, subjectBody);
	}

	@DeleteMapping("/{id}")
	public SubjectEntity deleteSubject(@PathVariable String id) {
		return subjectService.deleteSubject(id);
	}
}
