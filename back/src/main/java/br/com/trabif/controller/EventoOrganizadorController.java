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

import br.com.trabif.domain.EventoOrganizador;
import br.com.trabif.dto.EventoOrganizadorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.EventoOrganizadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "eventoOrganizador", description = "API de EventoOrganizador")
public class EventoOrganizadorController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;

	@Autowired
	private EventoOrganizadorService eventoOrganizadorService;

	@Operation(summary = "Busca eventoOrganizadores", description = "Buscar todos os eventoOrganizadores, buscar eventoOrganizadores por descricao", tags = {
			"eventoOrganizador" })
	@GetMapping(value = "/eventoOrganizador")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<EventoOrganizadorDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true) @RequestBody(required = false) String descricao,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		return ResponseEntity.ok(eventoOrganizadorService.findAll(pageable));
	}

	@Operation(summary = "Busca ID", description = "Buscar eventoOrganizador por ID", tags = { "eventoOrganizador" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = EventoOrganizador.class))),
			@ApiResponse(responseCode = "404", description = "EventoOrganizador não encontrado")
	})
	@GetMapping(value = "/eventoOrganizador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<EventoOrganizador> findEventoOrganizadorById(@PathVariable long id) {
		try {
			EventoOrganizador eventoOrganizador = eventoOrganizadorService.findById(id);
			return ResponseEntity.ok(eventoOrganizador);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}

	}

	@Operation(summary = "Busca ID", description = "Buscar eventoOrganizador por ID do evento", tags = { "eventoOrganizador" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = EventoOrganizador.class))),
			@ApiResponse(responseCode = "404", description = "EventoOrganizador não encontrado para este evento")
	})
	@GetMapping(value = "/eventoOrganizador/evento/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<EventoOrganizadorDTO>> findEventoOrganizadorByIdEvento(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<EventoOrganizadorDTO> eventoOrganizadores = eventoOrganizadorService.findAllByIdEvento(id, pageable);
		return ResponseEntity.ok(eventoOrganizadores);
	}

	@Operation(summary = "Busca ID", description = "Buscar eventoOrganizador por ID do organizador", tags = {
			"eventoOrganizador" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = EventoOrganizador.class))),
			@ApiResponse(responseCode = "404", description = "EventoOrganizador não encontrado para este evento")
	})
	@GetMapping(value = "/eventoOrganizador/organizador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<EventoOrganizadorDTO>> findEventoOrganizadorByIdOrganizador(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<EventoOrganizadorDTO> eventoOrganizadores = eventoOrganizadorService.findAllByIdOrganizador(id, pageable);
		return ResponseEntity.ok(eventoOrganizadores);
	}

	@Operation(summary = "Adicionar eventoOrganizador", description = "Adicionar novo eventoOrganizador informado no banco de dados", tags = {
			"eventoOrganizador" })
	@PostMapping(value = "/eventoOrganizador")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<EventoOrganizadorDTO> addEventoOrganizador(@RequestBody EventoOrganizador eventoOrganizador)
			throws URISyntaxException {
		try {
			EventoOrganizador novoEventoOrganizador = eventoOrganizadorService.save(eventoOrganizador);

			return ResponseEntity.created(new URI("/api/eventoOrganizador" + novoEventoOrganizador.getId()))
					.body(new EventoOrganizadorDTO().converter(eventoOrganizador));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Operation(summary = "Alterar EventoOrganizador", description = "Alterar valores do eventoOrganizador com id selecionado", tags = {
			"eventoOrganizador" })
	@PutMapping(value = "/eventoOrganizador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<EventoOrganizador> updateEventoOrganizador(@Valid @RequestBody EventoOrganizador eventoOrganizador,
			@PathVariable long id) {
		try {
			eventoOrganizador.setId(id);
			eventoOrganizadorService.update(eventoOrganizador);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@Operation(summary = "Deletar eventoOrganizador", description = "Deletar eventoOrganizador com o ID informado", tags = {
			"eventoOrganizador" })
	@DeleteMapping(path = "/eventoOrganizador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> deleteEventoOrganizadorById(@PathVariable long id) {
		try {
			eventoOrganizadorService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
