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

import br.com.trabif.domain.Area;
import br.com.trabif.dto.AreaDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "area", description = "API de Area")
public class AreaController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private AreaService areaService;
	
	@Operation(summary = "Busca areas", description = "Buscar todos os areas, buscar areas por descricao", tags = {"area"})
	@GetMapping(value = "/area")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Page<AreaDTO>> findAll(
			@Parameter(description = "Descrição para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) String descricao,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(descricao == null || descricao.isEmpty()) {
			return ResponseEntity.ok(areaService.findAll(pageable));
		} else {
			return ResponseEntity.ok(areaService.findAllByDescricao(descricao, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar area por ID", tags = {"area"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = Area.class))),
			@ApiResponse(responseCode = "404", description = "Area não encontrado")
	})
	@GetMapping(value = "/area/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Area> findAreaById(@PathVariable long id) {
		try {
			Area area = areaService.findById(id);
			return ResponseEntity.ok(area);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@Operation(summary = "Adicionar area", description = "Adicionar novo area informado no banco de dados", tags = {"area"})
	@PostMapping(value = "/area")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<AreaDTO> addArea(@RequestBody Area area) throws URISyntaxException {
		try {
			Area novoArea = areaService.save(area);
			
			return ResponseEntity.created(new URI("/api/area" + novoArea.getId())).body(new AreaDTO().converter(area));
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar Area", description = "Alterar valores do area com id selecionado", tags = {"area"})
	@PutMapping(value = "/area/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Area> updateArea(@Valid @RequestBody Area area, 
			@PathVariable long id) {
		try {
			area.setId(id);
			areaService.update(area);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar area", description = "Deletar area com o ID informado", tags = {"area"})
	@DeleteMapping(path = "/area/{id}")
	@CrossOrigin("http://localhost:4200")
	public ResponseEntity<Void> deleteAreaById(@PathVariable long id) {
		try {
			areaService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
