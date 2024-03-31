package io.spring.security.owasp.reqres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRegisterRequest {

	private String username;
	private String password;
	private String role;
	
}
