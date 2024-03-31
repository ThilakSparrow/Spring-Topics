package io.spring.security.owasp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.spring.security.owasp.entity.AccountDTO;
import io.spring.security.owasp.repository.AccountRepository;
import io.spring.security.owasp.repository.UserRepository;

@Service
public class SqlInjectionService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;

	public ResponseEntity<?> accountInfo_v(String customerId) {
		List<AccountDTO> acc = accountRepository.unsafeJpaFindAccountsByCustomerId(customerId);
		return new ResponseEntity<>(acc, HttpStatus.OK);
	}

	public ResponseEntity<?> accountInfo_s(String customerId) {
		List<AccountDTO> acc = accountRepository.safeJpaFindAccountsByCustomerId(customerId);
		return new ResponseEntity<>(acc, HttpStatus.OK);
	}

}
