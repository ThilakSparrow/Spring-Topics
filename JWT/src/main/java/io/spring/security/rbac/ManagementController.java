package io.spring.security.rbac;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/v2/management")
public class ManagementController {

	@GetMapping
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public String managementGet() {
		return "Get :: management Controller";
	}
	
}
