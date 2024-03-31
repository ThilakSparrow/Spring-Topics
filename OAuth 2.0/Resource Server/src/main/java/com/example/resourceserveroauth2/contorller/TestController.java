package com.example.resourceserveroauth2.contorller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMethodSecurity
@RequestMapping("/auth")
public class TestController {

	@GetMapping("/check")
	public String check() {
		return "success";
	}
	
	@Secured("SCOPE_openid")
	@GetMapping("/secretdata")
	public String Secret() {
		return "this is Secret";
	}
	
	@PreAuthorize("hasAuthority('SCOPE_openid')")
	@GetMapping("/openidauth")
	public String  OpenIDauth() {
		return "you are a OpenId user";
	}
}
