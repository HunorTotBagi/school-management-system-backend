package com.electric_diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electric_diary.entities.SubjectEntity;
import com.electric_diary.handlers.SubjectHandler;

@RestController
@RequestMapping(path = "/api/v1/subjects")
public class SubjectController {

	@Autowired
	protected SubjectHandler subjectHandler;
	
	@RequestMapping(method = RequestMethod.POST)
	public SubjectEntity createSubject(@RequestParam String name, @RequestParam String weeklyFund) {
		return subjectHandler.createSubject(name, weeklyFund);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<SubjectEntity> getAllSubjects() {
		return subjectHandler.getAllSubjects();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public SubjectEntity getSubjectById(@PathVariable String id) {
		return subjectHandler.getSubjectById(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public SubjectEntity updateSubject(@PathVariable String id, @RequestParam String name,
			@RequestParam String weeklyFund) {
		return subjectHandler.updateSubject(id, name, weeklyFund);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public SubjectEntity deleteSubject(@PathVariable String id) {
		return subjectHandler.deleteSubject(id);
	}
}
