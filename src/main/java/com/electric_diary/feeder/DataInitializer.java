package com.electric_diary.feeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.electric_diary.entities.RoleEntity;
import com.electric_diary.entities.UserEntity;
import com.electric_diary.enums.RoleEnum;
import com.electric_diary.repositories.RoleRepository;
import com.electric_diary.repositories.UserRepository;

@Component
@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository) {
		return args -> {
			initRoles(roleRepository);
			initUsers(userRepository, roleRepository);
		};
	}

	private void initRoles(RoleRepository roleRepository) {
		for (RoleEnum roleEnum : RoleEnum.values()) {
			if (!roleRepository.findByName(roleEnum.name()).isPresent()) {
				RoleEntity role = RoleEntity.builder().name(roleEnum.name()).build();
				roleRepository.save(role);
			}
		}
	}

	private void initUsers(UserRepository userRepository, RoleRepository roleRepository) {
		if (!userRepository.findByEmail("user@example.com").isPresent()) {
			RoleEntity userRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN.name())
					.orElseThrow(() -> new RuntimeException("Role not found"));

			UserEntity user = UserEntity.builder().firstName("admin").lastName("admin").email("admin@school.com")
					.password("{noop}admin").role(userRole).build();

			userRepository.save(user);
		}
	}
}
