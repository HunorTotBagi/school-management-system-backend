package com.electric_diary.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.electric_diary.DTO.Request.TeacherRequestDTO;
import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.services.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	protected TeacherService teacherService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping
	public ResponseEntity<TeacherEntity> createTeacher(@Valid @RequestBody TeacherRequestDTO teacherRequestDTO) {
		TeacherEntity createdTeacher = teacherService.createTeacher(teacherRequestDTO);
		logger.info("Teacher created successfully with ID: {}", createdTeacher.getId());
		return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Iterable<TeacherEntity>> getAllTeachers() {
		Iterable<TeacherEntity> teachers = teacherService.getAllTeachers();
		logger.info("Fetched all teachers");
		return new ResponseEntity<>(teachers, HttpStatus.OK);
	}

	@GetMapping("/{teacherId}")
	public ResponseEntity<TeacherEntity> getTeacherById(@PathVariable Integer teacherId) {
		TeacherEntity teacher = teacherService.getTeacherById(teacherId);
		logger.info("Teacher with ID {} found", teacherId);
		return new ResponseEntity<>(teacher, HttpStatus.OK);
	}

	@PutMapping("/{teacherId}")
	public ResponseEntity<TeacherEntity> updateTeacher(@PathVariable Integer teacherId, @RequestBody TeacherRequestDTO teacherRequestDTO) {
		TeacherEntity updatedTeacher = teacherService.updateTeacher(teacherId, teacherRequestDTO);
		logger.info("Teacher with ID {} updated successfully", updatedTeacher.getId());
		return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
	}

	@DeleteMapping("/{teacherId}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable Integer teacherId) {
		teacherService.deleteTeacher(teacherId);
		logger.info("Teacher with ID {} deleted successfully", teacherId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/teaches/Subject")
	public ResponseEntity<TeacherEntity> teacherTeachesSubject(@PathVariable Integer teacherId, @RequestBody TeacherRequestDTO teacherDTOBody) {
		TeacherEntity updatedTeacher = teacherService.teacherTeachesSubject(teacherId, teacherDTOBody);
		logger.info("Teacher with ID {} assigned to subject successfully", updatedTeacher.getId());
		return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
	}
}
