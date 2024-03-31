package io.spring.security.rbac;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/v2/admin")
public class AdminController {

	@Secured("ROLE_ADMIN")
	@GetMapping("/get")
	public String adminGet() {
		return "Welcome Admin";
	}
	
}
