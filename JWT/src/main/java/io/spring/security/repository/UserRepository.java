package io.spring.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.spring.security.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
	Optional<User> findByEmail(String username);
	
}
