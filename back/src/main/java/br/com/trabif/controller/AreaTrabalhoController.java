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

import br.com.trabif.domain.AreaTrabalho;
import br.com.trabif.dto.AreaTrabalhoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.AreaTrabalhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "areaTrabalho", description = "API de AreaTrabalho")
public class AreaTrabalhoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;

	@Autowired
	private AreaTrabalhoService areaTrabalhoService;

	@Operation(summary = "Busca areaTrabalhos", description = "Buscar todos os areaTrabalhos, buscar areaTrabalhos por descricao", tags = {
			"areaTrabalho" })
	@GetMapping(value = "/areaTrabalho")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<AreaTrabalhoDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true) @RequestBody(required = false) String descricao,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		return ResponseEntity.ok(areaTrabalhoService.findAll(pageable));
	}

	@Operation(summary = "Busca ID", description = "Buscar areaTrabalho por ID", tags = { "areaTrabalho" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = AreaTrabalho.class))),
			@ApiResponse(responseCode = "404", description = "AreaTrabalho não encontrado")
	})
	@GetMapping(value = "/areaTrabalho/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<AreaTrabalho> findAreaTrabalhoById(@PathVariable long id) {
		try {
			AreaTrabalho areaTrabalho = areaTrabalhoService.findById(id);
			return ResponseEntity.ok(areaTrabalho);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}

	}

	@Operation(summary = "Busca ID", description = "Buscar areaTrabalho por ID do area", tags = { "areaTrabalho" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = AreaTrabalho.class))),
			@ApiResponse(responseCode = "404", description = "AreaTrabalho não encontrado para este area")
	})
	@GetMapping(value = "/areaTrabalho/area/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<AreaTrabalhoDTO>> findAreaTrabalhoByIdArea(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<AreaTrabalhoDTO> areaTrabalhos = areaTrabalhoService.findAllByIdArea(id, pageable);
		return ResponseEntity.ok(areaTrabalhos);
	}

	@Operation(summary = "Busca ID", description = "Buscar areaTrabalho por ID do trabalho", tags = {
			"areaTrabalho" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = AreaTrabalho.class))),
			@ApiResponse(responseCode = "404", description = "AreaTrabalho não encontrado para este area")
	})
	@GetMapping(value = "/areaTrabalho/trabalho/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<AreaTrabalhoDTO>> findAreaTrabalhoByIdTrabalho(@PathVariable long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<AreaTrabalhoDTO> areaTrabalhos = areaTrabalhoService.findAllByIdTrabalho(id, pageable);
		return ResponseEntity.ok(areaTrabalhos);
	}

	@Operation(summary = "Adicionar areaTrabalho", description = "Adicionar novo areaTrabalho informado no banco de dados", tags = {
			"areaTrabalho" })
	@PostMapping(value = "/areaTrabalho")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<AreaTrabalhoDTO> addAreaTrabalho(@RequestBody AreaTrabalho areaTrabalho)
			throws URISyntaxException {
		try {
			AreaTrabalho novoAreaTrabalho = areaTrabalhoService.save(areaTrabalho);

			return ResponseEntity.created(new URI("/api/areaTrabalho" + novoAreaTrabalho.getId()))
					.body(new AreaTrabalhoDTO().converter(areaTrabalho));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Operation(summary = "Alterar AreaTrabalho", description = "Alterar valores do areaTrabalho com id selecionado", tags = {
			"areaTrabalho" })
	@PutMapping(value = "/areaTrabalho/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<AreaTrabalho> updateAreaTrabalho(@Valid @RequestBody AreaTrabalho areaTrabalho,
			@PathVariable long id) {
		try {
			areaTrabalho.setId(id);
			areaTrabalhoService.update(areaTrabalho);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@Operation(summary = "Deletar areaTrabalho", description = "Deletar areaTrabalho com o ID informado", tags = {
			"areaTrabalho" })
	@DeleteMapping(path = "/areaTrabalho/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> deleteAreaTrabalhoById(@PathVariable long id) {
		try {
			areaTrabalhoService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
