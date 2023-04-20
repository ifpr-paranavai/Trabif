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

import br.com.trabif.domain.Autor;
import br.com.trabif.dto.AutorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "autor", description = "API de Autor")
public class AutorController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private AutorService autorService;
	
	@Operation(summary = "Busca autores", description = "Buscar todos os autores, buscar autores por nome", tags = {"autor"})
	@GetMapping(value = "/autor")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<AutorDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) String nome,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(nome.isEmpty()) {
			return ResponseEntity.ok(autorService.findAll(pageable));
		} else {
			return ResponseEntity.ok(autorService.findAllByNome(nome, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar autor por ID", tags = {"autor"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = Autor.class))),
			@ApiResponse(responseCode = "404", description = "Autor não encontrado")
	})
	@GetMapping(value = "/autor/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Autor> findAutorById(@PathVariable long id) {
		try {
			Autor autor = autorService.findById(id);
			return ResponseEntity.ok(autor);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@Operation(summary = "Adicionar autor", description = "Adicionar novo autor informado no banco de dados", tags = {"autor"})
	@PostMapping(value = "/autor")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<AutorDTO> addAutor(@RequestBody Autor autor) throws URISyntaxException {
		try {
			Autor novoAutor = autorService.save(autor);
			
			return ResponseEntity.created(new URI("/api/autor" + novoAutor.getId())).body(new AutorDTO().converter(autor));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar Autor", description = "Alterar valores do autor com id selecionado", tags = {"autor"})
	@PutMapping(value = "/autor/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Autor> updateAutor(@Valid @RequestBody Autor autor, 
			@PathVariable long id) {
		try {
			autor.setId(id);
			autorService.update(autor);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar autor", description = "Deletar autor com o ID informado", tags = {"autor"})
	@DeleteMapping(path = "/autor/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> deleteAutorById(@PathVariable long id) {
		try {
			autorService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
