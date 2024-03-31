package io.spring.security.owasp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

	private String username;
	private String role;
	private Integer credits;
	
}
