package com.lab6.bed.restApi.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab6.bed.restApi.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String username);

}
