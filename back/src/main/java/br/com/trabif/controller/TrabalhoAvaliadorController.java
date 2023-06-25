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

import br.com.trabif.domain.TrabalhoAvaliador;
import br.com.trabif.dto.TrabalhoAvaliadorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.TrabalhoAvaliadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "trabalhoAvaliador", description = "API de TrabalhoAvaliador")
public class TrabalhoAvaliadorController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;

	@Autowired
	private TrabalhoAvaliadorService trabalhoAvaliadorService;

	@Operation(summary = "Busca trabalhoAvaliadores", description = "Buscar todos os trabalhoAvaliadores, buscar trabalhoAvaliadores por descricao", tags = {
			"trabalhoAvaliador" })
	@GetMapping(value = "/trabalhoAvaliador")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<TrabalhoAvaliadorDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true) @RequestBody(required = false) String descricao,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		return ResponseEntity.ok(trabalhoAvaliadorService.findAll(pageable));
	}

	@Operation(summary = "Busca ID", description = "Buscar trabalhoAvaliador por ID", tags = { "trabalhoAvaliador" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = TrabalhoAvaliador.class))),
			@ApiResponse(responseCode = "404", description = "TrabalhoAvaliador não encontrado")
	})
	@GetMapping(value = "/trabalhoAvaliador/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<TrabalhoAvaliador> findTrabalhoAvaliadorById(@PathVariable long id) {
		try {
			TrabalhoAvaliador trabalhoAvaliador = trabalhoAvaliadorService.findById(id);
			return ResponseEntity.ok(trabalhoAvaliador);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}

	}

	@Operation(summary = "Busca ID", description = "Buscar trabalhoAvaliador por ID do trabalho", tags = { "trabalhoAvaliador" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = TrabalhoAvaliador.class))),
			@ApiResponse(responseCode = "404", description = "TrabalhoAvaliador não encontrado para este trabalho")
	})
	@GetMapping(value = "/trabalhoAvaliador/trabalho/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<TrabalhoAvaliadorDTO>> findTrabalhoAvaliadorByIdTrabalho(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<TrabalhoAvaliadorDTO> trabalhoAvaliadores = trabalhoAvaliadorService.findAllByIdTrabalho(id, pageable);
		return ResponseEntity.ok(trabalhoAvaliadores);
	}

	@Operation(summary = "Busca ID", description = "Buscar trabalhoAvaliador por ID do avaliador", tags = {
			"trabalhoAvaliador" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = TrabalhoAvaliador.class))),
			@ApiResponse(responseCode = "404", description = "TrabalhoAvaliador não encontrado para este trabalho")
	})
	@GetMapping(value = "/trabalhoAvaliador/avaliador/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<TrabalhoAvaliadorDTO>> findTrabalhoAvaliadorByIdAvaliador(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<TrabalhoAvaliadorDTO> trabalhoAvaliadores = trabalhoAvaliadorService.findAllByIdAvaliador(id, pageable);
		return ResponseEntity.ok(trabalhoAvaliadores);
	}

	@Operation(summary = "Busca ID", description = "Buscar trabalhoAvaliador por ID do resultadoSubmissao", tags = {
		"trabalhoAvaliador" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = TrabalhoAvaliador.class))),
			@ApiResponse(responseCode = "404", description = "TrabalhoAvaliador não encontrado para este resultadoSubmissao")
	})
	@GetMapping(value = "/trabalhoAvaliador/resultadoSubmisao/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<TrabalhoAvaliadorDTO>> findTrabalhoAvaliadorByIdResultadoSubmissao(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<TrabalhoAvaliadorDTO> trabalhoAvaliadores = trabalhoAvaliadorService.findAllByIdResultadoSubmissao(id, pageable);
		return ResponseEntity.ok(trabalhoAvaliadores);
	}

	@Operation(summary = "Adicionar trabalhoAvaliador", description = "Adicionar novo trabalhoAvaliador informado no banco de dados", tags = {
			"trabalhoAvaliador" })
	@PostMapping(value = "/trabalhoAvaliador")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<TrabalhoAvaliadorDTO> addTrabalhoAvaliador(@RequestBody TrabalhoAvaliador trabalhoAvaliador)
			throws URISyntaxException {
		try {
			TrabalhoAvaliador novoTrabalhoAvaliador = trabalhoAvaliadorService.save(trabalhoAvaliador);

			return ResponseEntity.created(new URI("/api/trabalhoAvaliador" + novoTrabalhoAvaliador.getId()))
					.body(new TrabalhoAvaliadorDTO().converter(trabalhoAvaliador));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Operation(summary = "Alterar TrabalhoAvaliador", description = "Alterar valores do trabalhoAvaliador com id selecionado", tags = {
			"trabalhoAvaliador" })
	@PutMapping(value = "/trabalhoAvaliador/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<TrabalhoAvaliador> updateTrabalhoAvaliador(@Valid @RequestBody TrabalhoAvaliador trabalhoAvaliador,
			@PathVariable long id) {
		try {
			trabalhoAvaliador.setId(id);
			trabalhoAvaliadorService.update(trabalhoAvaliador);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@Operation(summary = "Deletar trabalhoAvaliador", description = "Deletar trabalhoAvaliador com o ID informado", tags = {
			"trabalhoAvaliador" })
	@DeleteMapping(path = "/trabalhoAvaliador/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Void> deleteTrabalhoAvaliadorById(@PathVariable long id) {
		try {
			trabalhoAvaliadorService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
