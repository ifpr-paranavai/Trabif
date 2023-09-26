package br.com.trabif.controller;

import java.net.URISyntaxException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trabif.domain.Email;
import br.com.trabif.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api")
@Tag(name = "email", description = "API de Email")
public class EmailController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private EmailService emailService;
	
	@Operation(summary = "Adicionar email", description = "Adicionar novo email informado no banco de dados", tags = {"email"})
	@PostMapping(value = "/email/{email}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Email> enviarEmail(@PathVariable String email, @RequestBody Map<String, Object> map) throws URISyntaxException {
		emailService.tratarEmail(email, map);
		
		return ResponseEntity.ok(null);
	}
	
}
