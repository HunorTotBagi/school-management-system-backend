package com.electric_diary.feeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.electric_diary.entities.RoleEntity;
import com.electric_diary.enums.RoleEnum;
import com.electric_diary.repositories.RoleRepository;

@Component
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            for (RoleEnum roleEnum : RoleEnum.values()) {
                if (!roleRepository.findByName(roleEnum.name()).isPresent()) {
                    RoleEntity role = RoleEntity.builder()
                            .name(roleEnum.name())
                            .build();
                    roleRepository.save(role);
                }
            }
        };
    }
}
