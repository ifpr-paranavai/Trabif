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

import br.com.trabif.domain.Avaliador;
import br.com.trabif.dto.AvaliadorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.AvaliadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "avaliador", description = "API de Avaliador")
public class AvaliadorController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private AvaliadorService avaliadorService;
	
	@Operation(summary = "Busca avaliadores", description = "Buscar todos os avaliadores, buscar avaliadores por nome", tags = {"avaliador"})
	@GetMapping(value = "/avaliador")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<AvaliadorDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) String nome,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(nome.isEmpty()) {
			return ResponseEntity.ok(avaliadorService.findAll(pageable));
		} else {
			return ResponseEntity.ok(avaliadorService.findAllByNome(nome, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar avaliador por ID", tags = {"avaliador"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = Avaliador.class))),
			@ApiResponse(responseCode = "404", description = "Avaliador não encontrado")
	})
	@GetMapping(value = "/avaliador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Avaliador> findAvaliadorById(@PathVariable long id) {
		try {
			Avaliador avaliador = avaliadorService.findById(id);
			return ResponseEntity.ok(avaliador);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@Operation(summary = "Adicionar avaliador", description = "Adicionar novo avaliador informado no banco de dados", tags = {"avaliador"})
	@PostMapping(value = "/avaliador")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<AvaliadorDTO> addAvaliador(@RequestBody Avaliador avaliador) throws URISyntaxException {
		try {
			Avaliador novoAvaliador = avaliadorService.save(avaliador);
			
			return ResponseEntity.created(new URI("/api/avaliador" + novoAvaliador.getId())).body(new AvaliadorDTO().converter(avaliador));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar Avaliador", description = "Alterar valores do avaliador com id selecionado", tags = {"avaliador"})
	@PutMapping(value = "/avaliador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Avaliador> updateAvaliador(@Valid @RequestBody Avaliador avaliador, 
			@PathVariable long id) {
		try {
			avaliador.setId(id);
			avaliadorService.update(avaliador);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar avaliador", description = "Deletar avaliador com o ID informado", tags = {"avaliador"})
	@DeleteMapping(path = "/avaliador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> deleteAvaliadorById(@PathVariable long id) {
		try {
			avaliadorService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
