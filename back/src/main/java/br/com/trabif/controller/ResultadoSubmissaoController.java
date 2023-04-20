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

import br.com.trabif.domain.ResultadoSubmissao;
import br.com.trabif.dto.ResultadoSubmissaoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.ResultadoSubmissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "resultadoSubmissao", description = "API de ResultadoSubmissao")
public class ResultadoSubmissaoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;

	@Autowired
	private ResultadoSubmissaoService resultadoSubmissaoService;

	@Operation(summary = "Busca resultadoSubmissoes", description = "Buscar todos os resultadoSubmissoes, buscar resultadoSubmissoes por descricao", tags = {
			"resultadoSubmissao" })
	@GetMapping(value = "/resultadoSubmissao")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<ResultadoSubmissaoDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true) @RequestBody(required = false) String descricao,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		return ResponseEntity.ok(resultadoSubmissaoService.findAll(pageable));
	}

	@Operation(summary = "Busca ID", description = "Buscar resultadoSubmissao por ID", tags = { "resultadoSubmissao" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ResultadoSubmissao.class))),
			@ApiResponse(responseCode = "404", description = "ResultadoSubmissao não encontrado")
	})
	@GetMapping(value = "/resultadoSubmissao/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<ResultadoSubmissao> findResultadoSubmissaoById(@PathVariable long id) {
		try {
			ResultadoSubmissao resultadoSubmissao = resultadoSubmissaoService.findById(id);
			return ResponseEntity.ok(resultadoSubmissao);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}

	}

	@Operation(summary = "Busca ID", description = "Buscar resultadoSubmissao por ID do area", tags = { "resultadoSubmissao" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ResultadoSubmissao.class))),
			@ApiResponse(responseCode = "404", description = "ResultadoSubmissao não encontrado para este area")
	})
	@GetMapping(value = "/resultadoSubmissao/confianca/{confianca}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<ResultadoSubmissaoDTO>> findResultadoSubmissaoByIdCriterio(@PathVariable int confianca,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<ResultadoSubmissaoDTO> resultadoSubmissoes = resultadoSubmissaoService.findAllByConfianca(confianca, pageable);
		return ResponseEntity.ok(resultadoSubmissoes);
	}

	@Operation(summary = "Busca ID", description = "Buscar resultadoSubmissao por ID do trabalho", tags = {
			"resultadoSubmissao" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ResultadoSubmissao.class))),
			@ApiResponse(responseCode = "404", description = "ResultadoSubmissao não encontrado para este area")
	})
	@GetMapping(value = "/resultadoSubmissao/resultado/{resultado}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Page<ResultadoSubmissaoDTO>> findResultadoSubmissaoByResultado(@PathVariable int resultado,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true) Pageable pageable) {
		Page<ResultadoSubmissaoDTO> resultadoSubmissoes = resultadoSubmissaoService.findAllByResultado(resultado, pageable);
		return ResponseEntity.ok(resultadoSubmissoes);
	}

	@Operation(summary = "Adicionar resultadoSubmissao", description = "Adicionar novo resultadoSubmissao informado no banco de dados", tags = {
			"resultadoSubmissao" })
	@PostMapping(value = "/resultadoSubmissao")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<ResultadoSubmissaoDTO> addResultadoSubmissao(@RequestBody ResultadoSubmissao resultadoSubmissao)
			throws URISyntaxException {
		try {
			ResultadoSubmissao novoResultadoSubmissao = resultadoSubmissaoService.save(resultadoSubmissao);

			return ResponseEntity.created(new URI("/api/resultadoSubmissao" + novoResultadoSubmissao.getId()))
					.body(new ResultadoSubmissaoDTO().converter(resultadoSubmissao));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Operation(summary = "Alterar ResultadoSubmissao", description = "Alterar valores do resultadoSubmissao com id selecionado", tags = {
			"resultadoSubmissao" })
	@PutMapping(value = "/resultadoSubmissao/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<ResultadoSubmissao> updateResultadoSubmissao(@Valid @RequestBody ResultadoSubmissao resultadoSubmissao,
			@PathVariable long id) {
		try {
			resultadoSubmissao.setId(id);
			resultadoSubmissaoService.update(resultadoSubmissao);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@Operation(summary = "Deletar resultadoSubmissao", description = "Deletar resultadoSubmissao com o ID informado", tags = {
			"resultadoSubmissao" })
	@DeleteMapping(path = "/resultadoSubmissao/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> deleteResultadoSubmissaoById(@PathVariable long id) {
		try {
			resultadoSubmissaoService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
