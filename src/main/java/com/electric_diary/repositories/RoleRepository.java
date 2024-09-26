package com.electric_diary.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.electric_diary.entities.RoleEntity;
import com.electric_diary.enums.RoleEnum;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
