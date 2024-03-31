package io.spring.security.owasp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.spring.security.owasp.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findByUsername(String username);
//	
//	List<Users> findByUsername(String username);

}
