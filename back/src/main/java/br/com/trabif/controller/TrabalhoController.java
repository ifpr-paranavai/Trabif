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

import br.com.trabif.domain.Trabalho;
import br.com.trabif.dto.TrabalhoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.TrabalhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "trabalho", description = "API de Trabalho")
public class TrabalhoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private TrabalhoService trabalhoService;
	
	@Operation(summary = "Busca trabalhos", description = "Buscar todos os trabalhos, buscar trabalhos por título", tags = {"trabalho"})
	@GetMapping(value = "/trabalho")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<TrabalhoDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) String titulo,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(titulo == null || titulo.isEmpty()) {
			return ResponseEntity.ok(trabalhoService.findAll(pageable));
		} else {
			return ResponseEntity.ok(trabalhoService.findAllByTitulo(titulo, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar trabalho por ID", tags = {"trabalho"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = Trabalho.class))),
			@ApiResponse(responseCode = "404", description = "Trabalho não encontrado")
	})
	@GetMapping(value = "/trabalho/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Trabalho> findTrabalhoById(@PathVariable long id) {
		try {
			Trabalho trabalho = trabalhoService.findById(id);
			return ResponseEntity.ok(trabalho);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}

	@Operation(summary = "Busca ID", description = "Buscar trabalho por ID da categoria", tags = {
		"trabalho" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = Trabalho.class))),
			@ApiResponse(responseCode = "404", description = "Trabalho não encontrado com esta categoria")
	})
	@GetMapping(value = "/trabalho/categoria/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<TrabalhoDTO>> findTrabalhoByIdCategoria(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<TrabalhoDTO> trabalhos = trabalhoService.findAllByIdCategoria(id, pageable);
		return ResponseEntity.ok(trabalhos);
	}
	
	@Operation(summary = "Adicionar trabalho", description = "Adicionar novo trabalho informado no banco de dados", tags = {"trabalho"})
	@PostMapping(value = "/trabalho")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<TrabalhoDTO> addTrabalho(@RequestBody Trabalho trabalho) throws URISyntaxException {
		try {
			Trabalho novoTrabalho = trabalhoService.save(trabalho);
			
			return ResponseEntity.created(new URI("/api/trabalho" + novoTrabalho.getId())).body(new TrabalhoDTO().converter(trabalho));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar Trabalho", description = "Alterar valores do trabalho com id selecionado", tags = {"trabalho"})
	@PutMapping(value = "/trabalho/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Trabalho> updateTrabalho(@Valid @RequestBody Trabalho trabalho, 
			@PathVariable long id) {
		try {
			trabalho.setId(id);
			trabalhoService.update(trabalho);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar trabalho", description = "Deletar trabalho com o ID informado", tags = {"trabalho"})
	@DeleteMapping(path = "/trabalho/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Void> deleteTrabalhoById(@PathVariable long id) {
		try {
			trabalhoService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
