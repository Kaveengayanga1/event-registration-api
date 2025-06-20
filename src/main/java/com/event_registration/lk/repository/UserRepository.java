package com.event_registration.lk.repository;

import com.event_registration.lk.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,String> {
    public Boolean existsUserEntityByEmailContainingIgnoreCase(String email);
    Optional<UserEntity> findUserEntityByEmailContainingIgnoreCase(String email);

}
