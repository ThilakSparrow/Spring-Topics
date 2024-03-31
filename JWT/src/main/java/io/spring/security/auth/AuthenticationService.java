package io.spring.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.spring.security.jwt.JwtService;
import io.spring.security.repository.UserRepository;
import io.spring.security.user.User;


@Service
public class AuthenticationService {

	@Autowired PasswordEncoder passwordEncoder;
	@Autowired UserRepository userRepository;
	@Autowired JwtService jwtService;
	@Autowired AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest registerRequest) {

		User user = User.builder().firstName(registerRequest.getFirstname()).lastName(registerRequest.getLastname())
				.email(registerRequest.getEmail()).password(passwordEncoder.encode(registerRequest.getPassword()))
				.role(registerRequest.getRole()).build();

		userRepository.save(user);

		String jwtToken = jwtService.generateToken(user);
//		String refreshToken = jwtService.generateRefreshToken(user);

		return AuthenticationResponse.builder().accessToken(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())); 
		
		User user = userRepository.findByEmail(authRequest.getEmail())
				.orElseThrow( () -> new UsernameNotFoundException("User not found :)"));
		
		String jwtToken = jwtService.generateToken(user);
//		String refreshToken = jwtService.generateRefreshToken(user);

		return AuthenticationResponse.builder().accessToken(jwtToken).build();
	}

}
