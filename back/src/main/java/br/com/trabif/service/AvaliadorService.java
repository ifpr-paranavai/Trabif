package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Avaliador;
import br.com.trabif.dto.AvaliadorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.AvaliadorRepository;

@Service
public class AvaliadorService {
	
	@Autowired
	private AvaliadorRepository avaliadorRepository;
	
	private boolean existsById(Long id) {
		return avaliadorRepository.existsById(id);
	}
	
	public Avaliador findById(Long id) throws ResourceNotFoundException {
		Avaliador avaliador = avaliadorRepository.findById(id).orElse(null);
		
		if(avaliador == null) {
			throw new ResourceNotFoundException("Avaliador não encontrado com o id: " + id);
		} else {
			return avaliador;
		}
	}
	
	public Page<AvaliadorDTO> findAll(Pageable pageable) {
		
		return new AvaliadorDTO().converterListaAvaliadorDTO(avaliadorRepository.findAll(pageable)) ;
	}
	
	public Page<AvaliadorDTO> findAllByNome(String descricao, Pageable page) {
		Page<Avaliador> avaliadores = avaliadorRepository.findByNome(descricao, page);
		
		
		return new AvaliadorDTO().converterListaAvaliadorDTO(avaliadores);
	}
	
	public Avaliador save(Avaliador avaliador) throws BadResourceException, ResourceAlreadyExistsException {
		if(!avaliador.getNome().isEmpty()) {
			avaliador.setSenha(new BCryptPasswordEncoder().encode(avaliador.getSenha()));
			if(existsById(avaliador.getId())) {
				throw new ResourceAlreadyExistsException("Avaliador com id: " + avaliador.getId() + " já existe.");
			}			
			avaliador.setStatus('A');
			avaliador.setDataCadastro(Calendar.getInstance().getTime());
			return avaliadorRepository.save(avaliador);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar avaliador");
			exe.addErrorMessage("Avaliador esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(Avaliador avaliador) throws BadResourceException, ResourceNotFoundException {
		if (!avaliador.getNome().isEmpty()) {
			if (!existsById(avaliador.getId())) {
				throw new ResourceNotFoundException("Avaliador não encontrado com o id: " + avaliador.getId());
			}
			avaliador.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			avaliadorRepository.save(avaliador);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar avaliador");
			exe.addErrorMessage("Avaliador esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Avaliador não encontrado com o id: " + id);
		} else {
			avaliadorRepository.deleteById(id);
		}
	
	}  public Long count() {
		return avaliadorRepository.count();
	}
	
}
