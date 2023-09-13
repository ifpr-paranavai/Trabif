package br.com.trabif.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.TrabalhoAvaliador;
import br.com.trabif.dto.TrabalhoAvaliadorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.TrabalhoAvaliadorRepository;

@Service
public class TrabalhoAvaliadorService {
	
	@Autowired
	private TrabalhoAvaliadorRepository trabalhoAvaliadorRepository;

	@Autowired
	private ResultadoSubmissaoService resultadoSubmissaoService;
	
	private boolean existsById(Long id) {
		return trabalhoAvaliadorRepository.existsById(id);
	}
	
	public TrabalhoAvaliador findById(Long id) throws ResourceNotFoundException {
		TrabalhoAvaliador trabalhoAvaliador = trabalhoAvaliadorRepository.findById(id).orElse(null);
		
		if(trabalhoAvaliador == null) {
			throw new ResourceNotFoundException("TrabalhoAvaliador não encontrado com o id: " + id);
		} else {
			return trabalhoAvaliador;
		}
	}
	
	public Page<TrabalhoAvaliadorDTO> findAll(Pageable pageable) {
		
		return new TrabalhoAvaliadorDTO().converterListaTrabalhoAvaliadorDTO(trabalhoAvaliadorRepository.findAll(pageable)) ;
	}
	
	public Page<TrabalhoAvaliadorDTO> findAllByIdTrabalho(Long id, Pageable page) {
		Page<TrabalhoAvaliador> trabalhoAvaliadores = trabalhoAvaliadorRepository.findByTrabalho(id, page);
		return new TrabalhoAvaliadorDTO().converterListaTrabalhoAvaliadorDTO(trabalhoAvaliadores);
	}

	public Page<TrabalhoAvaliadorDTO> findAllByIdAvaliador(Long id, Pageable page) {
		Page<TrabalhoAvaliador> trabalhoAvaliadores = trabalhoAvaliadorRepository.findByAvaliador(id, page);
		return new TrabalhoAvaliadorDTO().converterListaTrabalhoAvaliadorDTO(trabalhoAvaliadores);
	}

	public Page<TrabalhoAvaliadorDTO> findAllByIdResultadoSubmissao(Long id, Pageable page) {
		Page<TrabalhoAvaliador> trabalhoAvaliadores = trabalhoAvaliadorRepository.findByResultadoSubmissao(id, page);
		return new TrabalhoAvaliadorDTO().converterListaTrabalhoAvaliadorDTO(trabalhoAvaliadores);
	}
	
	public TrabalhoAvaliador save(TrabalhoAvaliador trabalhoAvaliador) throws BadResourceException, ResourceAlreadyExistsException {
		List<TrabalhoAvaliador> trabalhoAvaliadorList = this.trabalhoAvaliadorRepository.findByTrabalhoAndUsuario(trabalhoAvaliador.getTrabalho().getId(), trabalhoAvaliador.getUsuario().getId());
		if (trabalhoAvaliadorList != null && trabalhoAvaliadorList.size() > 0) {
			return trabalhoAvaliadorList.get(0);
		}
		if (trabalhoAvaliador.getTrabalho() != null && trabalhoAvaliador.getUsuario() != null) {
			if(existsById(trabalhoAvaliador.getId())) {
				throw new ResourceAlreadyExistsException("TrabalhoAvaliador com id: " + trabalhoAvaliador.getId() + " já existe.");
			}			
			trabalhoAvaliador.setStatus('A');
			trabalhoAvaliador.setDataCadastro(Calendar.getInstance().getTime());
			return trabalhoAvaliadorRepository.save(trabalhoAvaliador);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar trabalhoAvaliador");
			exe.addErrorMessage("TrabalhoAvaliador esta vazio ou nulo");
			throw exe;
		}		
	}
	
	public void update(TrabalhoAvaliador trabalhoAvaliador) throws BadResourceException, ResourceNotFoundException {
		if (trabalhoAvaliador.getResultadoSubmissao() != null && trabalhoAvaliador.getResultadoSubmissao().getId() <= 0) {
			try {
				trabalhoAvaliador.setResultadoSubmissao((this.resultadoSubmissaoService.save(trabalhoAvaliador.getResultadoSubmissao())));
			} catch (Exception e) {
				BadResourceException exe = new BadResourceException("Erro ao salvar trabalhoAvaliador");
				exe.addErrorMessage("TrabalhoAvaliador esta vazio ou nulo");
				throw exe;
			}
		}
		if (trabalhoAvaliador.getTrabalho() != null && trabalhoAvaliador.getUsuario() != null) {
			if (!existsById(trabalhoAvaliador.getId())) {
				throw new ResourceNotFoundException("TrabalhoAvaliador não encontrado com o id: " + trabalhoAvaliador.getId());
			}
			trabalhoAvaliador.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			trabalhoAvaliadorRepository.save(trabalhoAvaliador);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar trabalhoAvaliador");
			exe.addErrorMessage("TrabalhoAvaliador esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("TrabalhoAvaliador não encontrado com o id: " + id);
		} else {
			trabalhoAvaliadorRepository.deleteById(id);
		}
	
	}  public Long count() {
		return trabalhoAvaliadorRepository.count();
	}
	
}
