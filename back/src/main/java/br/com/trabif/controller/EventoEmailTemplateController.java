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

import br.com.trabif.domain.EventoEmailTemplate;
import br.com.trabif.dto.EventoEmailTemplateDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.EventoEmailTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "eventoEmailTemplate", description = "API de EventoEmailTemplate")
public class EventoEmailTemplateController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EventoEmailTemplateService eventoEmailTemplateService;

	@Operation(summary = "Busca eventoEmailTemplates", description = "Buscar todos os eventoEmailTemplates, buscar eventoEmailTemplates por descricao", tags = {
			"eventoEmailTemplate" })
	@GetMapping(value = "/eventoEmailTemplate")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<EventoEmailTemplateDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true) @RequestBody(required = false) String descricao,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		return ResponseEntity.ok(eventoEmailTemplateService.findAll(pageable));
	}

	@Operation(summary = "Busca ID", description = "Buscar eventoEmailTemplate por ID", tags = { "eventoEmailTemplate" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = EventoEmailTemplate.class))),
			@ApiResponse(responseCode = "404", description = "EventoEmailTemplate não encontrado")
	})
	@GetMapping(value = "/eventoEmailTemplate/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<EventoEmailTemplate> findEventoEmailTemplateById(@PathVariable long id) {
		try {
			EventoEmailTemplate eventoEmailTemplate = eventoEmailTemplateService.findById(id);
			return ResponseEntity.ok(eventoEmailTemplate);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}

	}

	@Operation(summary = "Busca ID", description = "Buscar eventoEmailTemplate por ID do evento", tags = { "eventoEmailTemplate" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = EventoEmailTemplate.class))),
			@ApiResponse(responseCode = "404", description = "EventoEmailTemplate não encontrado para este evento")
	})
	@GetMapping(value = "/eventoEmailTemplate/evento/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<EventoEmailTemplateDTO>> findEventoEmailTemplateByIdEvento(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<EventoEmailTemplateDTO> eventoEmailTemplates = eventoEmailTemplateService.findAllByIdEvento(id, pageable);
		return ResponseEntity.ok(eventoEmailTemplates);
	}

	@Operation(summary = "Busca ID", description = "Buscar eventoEmailTemplate por ID do emailTemplate", tags = {
			"eventoEmailTemplate" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = EventoEmailTemplate.class))),
			@ApiResponse(responseCode = "404", description = "EventoEmailTemplate não encontrado para este evento")
	})
	@GetMapping(value = "/eventoEmailTemplate/emailTemplate/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<EventoEmailTemplateDTO>> findEventoEmailTemplateByIdEmailTemplate(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<EventoEmailTemplateDTO> eventoEmailTemplates = eventoEmailTemplateService.findAllByIdEmailTemplate(id, pageable);
		return ResponseEntity.ok(eventoEmailTemplates);
	}

	@Operation(summary = "Adicionar eventoEmailTemplate", description = "Adicionar novo eventoEmailTemplate informado no banco de dados", tags = {
			"eventoEmailTemplate" })
	@PostMapping(value = "/eventoEmailTemplate")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<EventoEmailTemplateDTO> addEventoEmailTemplate(@RequestBody EventoEmailTemplate eventoEmailTemplate)
			throws URISyntaxException {
		try {
			EventoEmailTemplate novoEventoEmailTemplate = eventoEmailTemplateService.save(eventoEmailTemplate);

			return ResponseEntity.created(new URI("/api/eventoEmailTemplate" + novoEventoEmailTemplate.getId()))
					.body(new EventoEmailTemplateDTO().converter(eventoEmailTemplate));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Operation(summary = "Alterar EventoEmailTemplate", description = "Alterar valores do eventoEmailTemplate com id selecionado", tags = {
			"eventoEmailTemplate" })
	@PutMapping(value = "/eventoEmailTemplate/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<EventoEmailTemplate> updateEventoEmailTemplate(@Valid @RequestBody EventoEmailTemplate eventoEmailTemplate,
			@PathVariable long id) {
		try {
			eventoEmailTemplate.setId(id);
			eventoEmailTemplateService.update(eventoEmailTemplate);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@Operation(summary = "Deletar eventoEmailTemplate", description = "Deletar eventoEmailTemplate com o ID informado", tags = {
			"eventoEmailTemplate" })
	@DeleteMapping(path = "/eventoEmailTemplate/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Void> deleteEventoEmailTemplateById(@PathVariable long id) {
		try {
			eventoEmailTemplateService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
