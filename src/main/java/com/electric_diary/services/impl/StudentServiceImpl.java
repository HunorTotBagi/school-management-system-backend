package com.electric_diary.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.electric_diary.DTO.Request.ParentRequestDTO;
import com.electric_diary.DTO.Request.StudentRequestDTO;
import com.electric_diary.entities.ClassEntity;
import com.electric_diary.entities.ParentEntity;
import com.electric_diary.entities.RoleEntity;
import com.electric_diary.entities.StudentEntity;
import com.electric_diary.entities.TeacherEntity;
import com.electric_diary.entities.UserEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ClassRepository;
import com.electric_diary.repositories.ParentRepository;
import com.electric_diary.repositories.RoleRepository;
import com.electric_diary.repositories.StudentRepository;
import com.electric_diary.repositories.UserRepository;
import com.electric_diary.services.StudentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StudentServiceImpl implements StudentService {
	@PersistenceContext
	protected EntityManager entityManager;

	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final StudentRepository studentRepository;
	private final ClassRepository classRepository;
	private final ParentRepository parentRepository;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public StudentServiceImpl(final StudentRepository studentRepository, final ClassRepository classRepository,
			final ParentRepository parentRepository, final UserRepository userRepository,
			final RoleRepository roleRepository) {
		this.studentRepository = studentRepository;
		this.classRepository = classRepository;
		this.parentRepository = parentRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public StudentEntity createStudent(StudentRequestDTO studentRequestDTO) {
		UserEntity newUser = createAndSaveUser(studentRequestDTO);
		return createAndSaveStudent(studentRequestDTO, newUser);
	}

	@Override
	public Iterable<StudentEntity> getAllStudents() {
		logger.info("Fetched all students.");
		return studentRepository.findAll();
	}

	@Override
	public StudentEntity getStudentById(Integer studentId) {
		logger.info("Fetched student with ID {}.", studentId);
		return studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student", studentId));
	}

	@Override
	public StudentEntity updateStudent(Integer studentId, StudentRequestDTO studentRequestDTO) {
		StudentEntity existingStudent = getStudentById(studentId);
		UserEntity existingUser = getUserById(existingStudent.getUser().getId());
		
		updateStudent(studentRequestDTO, existingStudent);
		updateUser(studentRequestDTO, existingUser);
		
		return existingStudent;
	}

	private void updateUser(StudentRequestDTO studentRequestDTO, UserEntity existingUser) {
		existingUser.setFirstName(studentRequestDTO.getFirstName());
		existingUser.setLastName(studentRequestDTO.getLastName());
		existingUser.setEmail(studentRequestDTO.getEmail());

		userRepository.save(existingUser);
		logger.info("User with ID {} updated.", existingUser.getId());		
	}

	@Override
	public StudentEntity deleteStudent(Integer studentId) {
		StudentEntity student = getStudentById(studentId);

		student.getParent().getStudents().remove(student);

		studentRepository.delete(student);
		logger.info("Deleted student with ID {}.", studentId);
		return student;
	}

	private UserEntity createAndSaveUser(StudentRequestDTO studentRequestDTO) {
		validateUserFields(studentRequestDTO);
		RoleEntity newRole = getRoleById(3);
		UserEntity user = new UserEntity();

		user.setFirstName(studentRequestDTO.getFirstName());
		user.setLastName(studentRequestDTO.getLastName());
		user.setPassword("{noop}" + studentRequestDTO.getPassword());
		user.setEmail(studentRequestDTO.getEmail());
		user.setRole(newRole);

		userRepository.save(user);
		logger.info("User with ID {} created.", user.getId());
		return user;
	}

	private void validateUserFields(StudentRequestDTO studentRequestDTO) {
		if (studentRequestDTO.getEmail() == null || studentRequestDTO.getPassword() == null
				|| studentRequestDTO.getFirstName() == null || studentRequestDTO.getLastName() == null) {
			throw new IllegalArgumentException("All fields are required.");
		}
		if (!studentRequestDTO.getEmail().contains("@"))
			throw new IllegalArgumentException("Invalid email format.");
	}

	private StudentEntity createAndSaveStudent(StudentRequestDTO studentRequestDTO, UserEntity newUser) {
		StudentEntity student = new StudentEntity();
		ClassEntity newClass = getClassById(studentRequestDTO.getClassId());
		ParentEntity parent = getParentById(studentRequestDTO.getParentId());

		student.setFirstName(studentRequestDTO.getFirstName());
		student.setLastName(studentRequestDTO.getLastName());
		student.setEmail(studentRequestDTO.getEmail());
		student.setPassword(studentRequestDTO.getPassword());
		student.setNewClass(newClass);
		student.setParent(parent);
		student.setUser(newUser);

		studentRepository.save(student);
		logger.info("Student with ID {} created.", student.getId());
		return student;
	}
	
	private void updateStudent(StudentRequestDTO studentRequestDTO, StudentEntity existingStudent) {
		ClassEntity newClass = getClassById(studentRequestDTO.getClassId());
		ParentEntity parent = getParentById(studentRequestDTO.getParentId());
		
		existingStudent.setFirstName(studentRequestDTO.getFirstName());
		existingStudent.setLastName(studentRequestDTO.getLastName());
		existingStudent.setEmail(studentRequestDTO.getEmail());
		existingStudent.setNewClass(newClass);
		existingStudent.setParent(parent);
		
		studentRepository.save(existingStudent);
		logger.info("Student with ID {} updated.", existingStudent.getId());
	}

	private RoleEntity getRoleById(Integer roleId) {
		return roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role", roleId));
	}

	private ClassEntity getClassById(Integer classId) {
		return classRepository.findById(classId).orElseThrow(() -> new NotFoundException("Class", classId));
	}

	public ParentEntity getParentById(Integer parentId) {
		return parentRepository.findById(parentId).orElseThrow(() -> new NotFoundException("Parent", parentId));
	}

	private UserEntity getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User", userId));
	}
}
