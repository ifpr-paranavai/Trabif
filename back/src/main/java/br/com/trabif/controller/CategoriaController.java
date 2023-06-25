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

import br.com.trabif.domain.Categoria;
import br.com.trabif.dto.CategoriaDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "categoria", description = "API de Categoria")
public class CategoriaController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Operation(summary = "Busca categorias", description = "Buscar todos os categorias, buscar categorias por descricao", tags = {"categoria"})
	@GetMapping(value = "/categoria")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<CategoriaDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) String descricao,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(descricao == null || descricao.isEmpty()) {
			return ResponseEntity.ok(categoriaService.findAll(pageable));
		} else {
			return ResponseEntity.ok(categoriaService.findAllByDescricao(descricao, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar categoria por ID", tags = {"categoria"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = Categoria.class))),
			@ApiResponse(responseCode = "404", description = "Categoria não encontrado")
	})
	@GetMapping(value = "/categoria/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Categoria> findCategoriaById(@PathVariable long id) {
		try {
			Categoria categoria = categoriaService.findById(id);
			return ResponseEntity.ok(categoria);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@Operation(summary = "Adicionar categoria", description = "Adicionar novo categoria informado no banco de dados", tags = {"categoria"})
	@PostMapping(value = "/categoria")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<CategoriaDTO> addCategoria(@RequestBody Categoria categoria) throws URISyntaxException {
		try {
			Categoria novoCategoria = categoriaService.save(categoria);
			
			return ResponseEntity.created(new URI("/api/categoria" + novoCategoria.getId())).body(new CategoriaDTO().converter(categoria));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar Categoria", description = "Alterar valores do categoria com id selecionado", tags = {"categoria"})
	@PutMapping(value = "/categoria/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Categoria> updateCategoria(@Valid @RequestBody Categoria categoria, 
			@PathVariable long id) {
		try {
			categoria.setId(id);
			categoriaService.update(categoria);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar categoria", description = "Deletar categoria com o ID informado", tags = {"categoria"})
	@DeleteMapping(path = "/categoria/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Void> deleteCategoriaById(@PathVariable long id) {
		try {
			categoriaService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
