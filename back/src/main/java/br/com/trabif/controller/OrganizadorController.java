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

import br.com.trabif.domain.Organizador;
import br.com.trabif.dto.OrganizadorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.OrganizadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "organizador", description = "API de Organizador")
public class OrganizadorController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private OrganizadorService organizadorService;
	
	@Operation(summary = "Busca organizadores", description = "Buscar todos os organizadores, buscar organizadores por nome", tags = {"organizador"})
	@GetMapping(value = "/organizador")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<OrganizadorDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) String nome,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(nome.isEmpty()) {
			return ResponseEntity.ok(organizadorService.findAll(pageable));
		} else {
			return ResponseEntity.ok(organizadorService.findAllByNome(nome, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar organizador por ID", tags = {"organizador"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = Organizador.class))),
			@ApiResponse(responseCode = "404", description = "Organizador não encontrado")
	})
	@GetMapping(value = "/organizador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Organizador> findOrganizadorById(@PathVariable long id) {
		try {
			Organizador organizador = organizadorService.findById(id);
			return ResponseEntity.ok(organizador);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@Operation(summary = "Adicionar organizador", description = "Adicionar novo organizador informado no banco de dados", tags = {"organizador"})
	@PostMapping(value = "/organizador")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<OrganizadorDTO> addOrganizador(@RequestBody Organizador organizador) throws URISyntaxException {
		try {
			Organizador novoOrganizador = organizadorService.save(organizador);
			
			return ResponseEntity.created(new URI("/api/organizador" + novoOrganizador.getId())).body(new OrganizadorDTO().converter(organizador));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar Organizador", description = "Alterar valores do organizador com id selecionado", tags = {"organizador"})
	@PutMapping(value = "/organizador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Organizador> updateOrganizador(@Valid @RequestBody Organizador organizador, 
			@PathVariable long id) {
		try {
			organizador.setId(id);
			organizadorService.update(organizador);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar organizador", description = "Deletar organizador com o ID informado", tags = {"organizador"})
	@DeleteMapping(path = "/organizador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> deleteOrganizadorById(@PathVariable long id) {
		try {
			organizadorService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
