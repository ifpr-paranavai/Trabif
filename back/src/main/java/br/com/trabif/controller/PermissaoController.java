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

import br.com.trabif.domain.Permissao;
import br.com.trabif.dto.PermissaoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.PermissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "permissao", description = "API de Permissao")
public class PermissaoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@Operation(summary = "Busca permissoes", description = "Buscar todos os permissoes, buscar permissoes por descricao", tags = {"permissao"})
	@GetMapping(value = "/permissao")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<PermissaoDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) String descricao,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(descricao == null || descricao.isEmpty()) {
			return ResponseEntity.ok(permissaoService.findAll(pageable));
		} else {
			return ResponseEntity.ok(permissaoService.findAllByDescricao(descricao, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar permissao por ID", tags = {"permissao"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = Permissao.class))),
			@ApiResponse(responseCode = "404", description = "Permissao não encontrado")
	})
	@GetMapping(value = "/permissao/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Permissao> findPermissaoById(@PathVariable long id) {
		try {
			Permissao permissao = permissaoService.findById(id);
			return ResponseEntity.ok(permissao);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@Operation(summary = "Adicionar permissao", description = "Adicionar novo permissao informado no banco de dados", tags = {"permissao"})
	@PostMapping(value = "/permissao")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<PermissaoDTO> addPermissao(@RequestBody Permissao permissao) throws URISyntaxException {
		try {
			Permissao novoPermissao = permissaoService.save(permissao);
			
			return ResponseEntity.created(new URI("/api/permissao" + novoPermissao.getId())).body(new PermissaoDTO().converter(permissao));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar Permissao", description = "Alterar valores do permissao com id selecionado", tags = {"permissao"})
	@PutMapping(value = "/permissao/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Permissao> updatePermissao(@Valid @RequestBody Permissao permissao, 
			@PathVariable long id) {
		try {
			permissao.setId(id);
			permissaoService.update(permissao);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar permissao", description = "Deletar permissao com o ID informado", tags = {"permissao"})
	@DeleteMapping(path = "/permissao/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Void> deletePermissaoById(@PathVariable long id) {
		try {
			permissaoService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
