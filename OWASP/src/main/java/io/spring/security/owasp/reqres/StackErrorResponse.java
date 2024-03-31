package io.spring.security.owasp.reqres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StackErrorResponse {
	
	private boolean success;
	private String msg;	
	
}
