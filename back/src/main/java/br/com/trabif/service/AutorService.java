package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Autor;
import br.com.trabif.dto.AutorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	private boolean existsById(Long id) {
		return autorRepository.existsById(id);
	}
	
	public Autor findById(Long id) throws ResourceNotFoundException {
		Autor autor = autorRepository.findById(id).orElse(null);
		
		if(autor == null) {
			throw new ResourceNotFoundException("Autor não encontrado com o id: " + id);
		} else {
			return autor;
		}
	}
	
	public Page<AutorDTO> findAll(Pageable pageable) {
		
		return new AutorDTO().converterListaAutorDTO(autorRepository.findAll(pageable)) ;
	}
	
	public Page<AutorDTO> findAllByNome(String descricao, Pageable page) {
		Page<Autor> autores = autorRepository.findByNome(descricao, page);
		
		
		return new AutorDTO().converterListaAutorDTO(autores);
	}
	
	public Autor save(Autor autor) throws BadResourceException, ResourceAlreadyExistsException {
		if(!autor.getNome().isEmpty()) {
			if(existsById(autor.getId())) {
				throw new ResourceAlreadyExistsException("Autor com id: " + autor.getId() + " já existe.");
			}			
			autor.setStatus('A');
			autor.setDataCadastro(Calendar.getInstance().getTime());
			return autorRepository.save(autor);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar autor");
			exe.addErrorMessage("Autor esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(Autor autor) throws BadResourceException, ResourceNotFoundException {
		if (!autor.getNome().isEmpty()) {
			if (!existsById(autor.getId())) {
				throw new ResourceNotFoundException("Autor não encontrado com o id: " + autor.getId());
			}
			autor.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			autorRepository.save(autor);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar autor");
			exe.addErrorMessage("Autor esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Autor não encontrado com o id: " + id);
		} else {
			autorRepository.deleteById(id);
		}
	
	}  public Long count() {
		return autorRepository.count();
	}
	
}
