package com.electric_diary.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.electric_diary.DTO.Request.ParentRequestDTO;
import com.electric_diary.entities.ParentEntity;
import com.electric_diary.entities.RoleEntity;
import com.electric_diary.entities.StudentEntity;
import com.electric_diary.entities.UserEntity;
import com.electric_diary.exception.NotFoundException;
import com.electric_diary.repositories.ParentRepository;
import com.electric_diary.repositories.RoleRepository;
import com.electric_diary.repositories.StudentRepository;
import com.electric_diary.repositories.UserRepository;
import com.electric_diary.services.ParentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ParentServiceImpl implements ParentService {
	@PersistenceContext
	protected EntityManager entityManager;

	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ParentRepository parentRepository;
	private final StudentRepository studentRepository;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public ParentServiceImpl(final ParentRepository parentRepository, final StudentRepository studentRepository,
			final UserRepository userRepository, final RoleRepository roleRepository) {
		this.parentRepository = parentRepository;
		this.studentRepository = studentRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public ParentEntity createParent(ParentRequestDTO parentRequestDTO) {
		UserEntity newUser = createAndSaveUser(parentRequestDTO);
		return createAndSaveParent(parentRequestDTO, newUser);
	}

	@Override
	public Iterable<ParentEntity> getAllParents() {
		logger.info("Fetched all parents.");
		return parentRepository.findAll();
	}

	@Override
	public ParentEntity getParentById(Integer parentId) {
		logger.info("Fetched parent with ID {}.", parentId);
		return parentRepository.findById(parentId).orElseThrow(() -> new NotFoundException("Parent", parentId));
	}

	@Override
	public ParentEntity updateParent(Integer parentId, ParentRequestDTO parentRequestDTO) {
		ParentEntity existingParent = getParentById(parentId);
		UserEntity existingUser = getUserById(existingParent.getId());

		updateParent(parentRequestDTO, existingParent);
		updateUser(parentRequestDTO, existingUser);

		return existingParent;
	}

	@Override
	public ParentEntity deleteParent(Integer parentId) {
		ParentEntity parent = getParentById(parentId);
		UserEntity user = parent.getUser();

		parentRepository.delete(parent);
		userRepository.delete(user);
		logger.info("Deleted parent with ID {} and associated user with ID {}.", parentId, user.getId());

		return parent;
	}

	@Override
	public ParentEntity assignStudentToParent(Integer parentId, Integer studentId) {
		ParentEntity parent = getParentById(parentId);
		StudentEntity student = getStudentById(studentId);

		student.setParent(parent);
		studentRepository.save(student);
		logger.info("Assigned student with ID {} to parent with ID {}.", studentId, parentId);

		return parent;
	}

	private UserEntity createAndSaveUser(ParentRequestDTO parentRequestDTO) {
		validateUserFields(parentRequestDTO);
		RoleEntity newRole = getRoleById(4);
		UserEntity user = new UserEntity();

		user.setFirstName(parentRequestDTO.getFirstName());
		user.setLastName(parentRequestDTO.getLastName());
		user.setPassword("{noop}" + parentRequestDTO.getPassword());
		user.setEmail(parentRequestDTO.getEmail());
		user.setRole(newRole);

		userRepository.save(user);
		logger.info("User with ID {} created.", user.getId());
		return user;
	}

	private void validateUserFields(ParentRequestDTO parentRequestDTO) {
		if (parentRequestDTO.getEmail() == null || parentRequestDTO.getPassword() == null
				|| parentRequestDTO.getFirstName() == null || parentRequestDTO.getLastName() == null) {
			throw new IllegalArgumentException("All fields are required.");
		}
		if (!parentRequestDTO.getEmail().contains("@"))
			throw new IllegalArgumentException("Invalid email format.");
	}

	private ParentEntity createAndSaveParent(ParentRequestDTO parentRequestDTO, UserEntity newUser) {
		ParentEntity parent = new ParentEntity();
		parent.setFirstName(parentRequestDTO.getFirstName());
		parent.setLastName(parentRequestDTO.getLastName());
		parent.setEmail(parentRequestDTO.getEmail());
		parent.setUser(newUser);

		parentRepository.save(parent);
		logger.info("Parent with ID {} created.", parent.getId());
		return parent;
	}

	private void updateParent(ParentRequestDTO parentRequestDTO, ParentEntity existingParent) {
		existingParent.setFirstName(parentRequestDTO.getFirstName());
		existingParent.setLastName(parentRequestDTO.getLastName());
		existingParent.setEmail(parentRequestDTO.getEmail());

		parentRepository.save(existingParent);
		logger.info("Parent with ID {} updated.", existingParent.getId());
	}

	private void updateUser(ParentRequestDTO parentRequestDTO, UserEntity existingUser) {
		existingUser.setFirstName(parentRequestDTO.getFirstName());
		existingUser.setLastName(parentRequestDTO.getLastName());
		existingUser.setEmail(parentRequestDTO.getEmail());

		userRepository.save(existingUser);
		logger.info("User with ID {} updated.", existingUser.getId());
	}

	private UserEntity getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User", userId));
	}

	private StudentEntity getStudentById(Integer studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student", studentId));
	}

	private RoleEntity getRoleById(Integer roleId) {
		return roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role", roleId));
	}
}
