package com.nodirverse.albatros.repository;

import com.nodirverse.albatros.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findUserEntityByEmail(String email);

    void deleteByEmail(String email);
}
