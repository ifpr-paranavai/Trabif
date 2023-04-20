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

import br.com.trabif.domain.AutorTrabalho;
import br.com.trabif.dto.AutorTrabalhoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.AutorTrabalhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "autorTrabalho", description = "API de AutorTrabalho")
public class AutorTrabalhoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;

	@Autowired
	private AutorTrabalhoService autorTrabalhoService;

	@Operation(summary = "Busca autorTrabalhos", description = "Buscar todos os autorTrabalhos, buscar autorTrabalhos por descricao", tags = {
			"autorTrabalho" })
	@GetMapping(value = "/autorTrabalho")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<AutorTrabalhoDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true) @RequestBody(required = false) String descricao,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		return ResponseEntity.ok(autorTrabalhoService.findAll(pageable));
	}

	@Operation(summary = "Busca ID", description = "Buscar autorTrabalho por ID", tags = { "autorTrabalho" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = AutorTrabalho.class))),
			@ApiResponse(responseCode = "404", description = "AutorTrabalho não encontrado")
	})
	@GetMapping(value = "/autorTrabalho/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<AutorTrabalho> findAutorTrabalhoById(@PathVariable long id) {
		try {
			AutorTrabalho autorTrabalho = autorTrabalhoService.findById(id);
			return ResponseEntity.ok(autorTrabalho);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}

	}

	@Operation(summary = "Busca ID", description = "Buscar autorTrabalho por ID do autor", tags = { "autorTrabalho" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = AutorTrabalho.class))),
			@ApiResponse(responseCode = "404", description = "AutorTrabalho não encontrado para este autor")
	})
	@GetMapping(value = "/autorTrabalho/autor/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<AutorTrabalhoDTO>> findAutorTrabalhoByIdAutor(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<AutorTrabalhoDTO> autorTrabalhos = autorTrabalhoService.findAllByIdAutor(id, pageable);
		return ResponseEntity.ok(autorTrabalhos);
	}

	@Operation(summary = "Busca ID", description = "Buscar autorTrabalho por ID do trabalho", tags = {
			"autorTrabalho" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = AutorTrabalho.class))),
			@ApiResponse(responseCode = "404", description = "AutorTrabalho não encontrado para este autor")
	})
	@GetMapping(value = "/autorTrabalho/trabalho/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<AutorTrabalhoDTO>> findAutorTrabalhoByIdTrabalho(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<AutorTrabalhoDTO> autorTrabalhos = autorTrabalhoService.findAllByIdTrabalho(id, pageable);
		return ResponseEntity.ok(autorTrabalhos);
	}

	@Operation(summary = "Adicionar autorTrabalho", description = "Adicionar novo autorTrabalho informado no banco de dados", tags = {
			"autorTrabalho" })
	@PostMapping(value = "/autorTrabalho")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<AutorTrabalhoDTO> addAutorTrabalho(@RequestBody AutorTrabalho autorTrabalho)
			throws URISyntaxException {
		try {
			AutorTrabalho novoAutorTrabalho = autorTrabalhoService.save(autorTrabalho);

			return ResponseEntity.created(new URI("/api/autorTrabalho" + novoAutorTrabalho.getId()))
					.body(new AutorTrabalhoDTO().converter(autorTrabalho));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Operation(summary = "Alterar AutorTrabalho", description = "Alterar valores do autorTrabalho com id selecionado", tags = {
			"autorTrabalho" })
	@PutMapping(value = "/autorTrabalho/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<AutorTrabalho> updateAutorTrabalho(@Valid @RequestBody AutorTrabalho autorTrabalho,
			@PathVariable long id) {
		try {
			autorTrabalho.setId(id);
			autorTrabalhoService.update(autorTrabalho);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@Operation(summary = "Deletar autorTrabalho", description = "Deletar autorTrabalho com o ID informado", tags = {
			"autorTrabalho" })
	@DeleteMapping(path = "/autorTrabalho/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> deleteAutorTrabalhoById(@PathVariable long id) {
		try {
			autorTrabalhoService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
