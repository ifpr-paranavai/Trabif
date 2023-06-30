package br.com.trabif.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.trabif.domain.EmailTemplate;
import br.com.trabif.dto.EmailTemplateDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.EmailTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "emailTemplate", description = "API de EmailTemplate")
public class EmailTemplateController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmailTemplateService emailTemplateService;
	
	@Operation(summary = "Busca emailTemplates", description = "Buscar todos os emailTemplates, buscar emailTemplates por título", tags = {"emailTemplate"})
	@GetMapping(value = "/emailTemplate")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<EmailTemplateDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) String titulo,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(titulo == null || titulo.isEmpty()) {
			return ResponseEntity.ok(emailTemplateService.findAll(pageable));
		} else {
			return ResponseEntity.ok(emailTemplateService.findAllByTitulo(titulo, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar emailTemplate por ID", tags = {"emailTemplate"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = EmailTemplate.class))),
			@ApiResponse(responseCode = "404", description = "EmailTemplate não encontrado")
	})
	@GetMapping(value = "/emailTemplate/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<EmailTemplate> findEmailTemplateById(@PathVariable long id) {
		try {
			EmailTemplate emailTemplate = emailTemplateService.findById(id);
			return ResponseEntity.ok(emailTemplate);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@Operation(summary = "Adicionar emailTemplate", description = "Adicionar novo emailTemplate informado no banco de dados", tags = {"emailTemplate"})
	@PostMapping(value = "/emailTemplate")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<EmailTemplateDTO> addEmailTemplate(@RequestBody EmailTemplate emailTemplate) throws URISyntaxException {
		try {
			EmailTemplate novoEmailTemplate = emailTemplateService.save(emailTemplate);
			
			return ResponseEntity.created(new URI("/api/emailTemplate" + novoEmailTemplate.getId())).body(new EmailTemplateDTO().converter(emailTemplate));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar EmailTemplate", description = "Alterar valores do emailTemplate com id selecionado", tags = {"emailTemplate"})
	@PutMapping(value = "/emailTemplate/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<EmailTemplate> updateEmailTemplate(@Valid @RequestBody EmailTemplate emailTemplate, 
			@PathVariable long id) {
		try {
			emailTemplate.setId(id);
			emailTemplateService.update(emailTemplate);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar emailTemplate", description = "Deletar emailTemplate com o ID informado", tags = {"emailTemplate"})
	@DeleteMapping(path = "/emailTemplate/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Void> deleteEmailTemplateById(@PathVariable long id) {
		try {
			emailTemplateService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
