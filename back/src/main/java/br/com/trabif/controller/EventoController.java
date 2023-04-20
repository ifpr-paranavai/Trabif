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

import br.com.trabif.domain.Evento;
import br.com.trabif.dto.EventoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "evento", description = "API de Evento")
public class EventoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private EventoService eventoService;
	
	@Operation(summary = "Busca eventos", description = "Buscar todos os eventos, buscar eventos por nome", tags = {"evento"})
	@GetMapping(value = "/evento")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<EventoDTO>> findAll(
			@Parameter(description = "Nome para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) String nome,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(nome.isEmpty()) {
			return ResponseEntity.ok(eventoService.findAll(pageable));
		} else {
			return ResponseEntity.ok(eventoService.findAllByNome(nome, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar evento por ID", tags = {"evento"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = Evento.class))),
			@ApiResponse(responseCode = "404", description = "Evento não encontrado")
	})
	@GetMapping(value = "/evento/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Evento> findEventoById(@PathVariable long id) {
		try {
			Evento evento = eventoService.findById(id);
			return ResponseEntity.ok(evento);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}

	@Operation(summary = "Busca ID", description = "Buscar evento por ID do organizador", tags = {
		"evento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = Evento.class))),
			@ApiResponse(responseCode = "404", description = "Evento não encontrado com este organizador")
	})
	@GetMapping(value = "/evento/organizador/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<EventoDTO>> findEventoByIdOrganizador(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<EventoDTO> eventos = eventoService.findAllByIdOrganizador(id, pageable);
		return ResponseEntity.ok(eventos);
	}
	
	@Operation(summary = "Adicionar evento", description = "Adicionar novo evento informado no banco de dados", tags = {"evento"})
	@PostMapping(value = "/evento")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<EventoDTO> addEvento(@RequestBody Evento evento) throws URISyntaxException {
		try {
			Evento novoEvento = eventoService.save(evento);
			
			return ResponseEntity.created(new URI("/api/evento" + novoEvento.getId())).body(new EventoDTO().converter(evento));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar Evento", description = "Alterar valores do evento com id selecionado", tags = {"evento"})
	@PutMapping(value = "/evento/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Evento> updateEvento(@Valid @RequestBody Evento evento, 
			@PathVariable long id) {
		try {
			evento.setId(id);
			eventoService.update(evento);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar evento", description = "Deletar evento com o ID informado", tags = {"evento"})
	@DeleteMapping(path = "/evento/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> deleteEventoById(@PathVariable long id) {
		try {
			eventoService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
