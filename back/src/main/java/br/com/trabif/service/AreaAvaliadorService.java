package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.AreaAvaliador;
import br.com.trabif.dto.AreaAvaliadorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.AreaAvaliadorRepository;

@Service
public class AreaAvaliadorService {
	
	@Autowired
	private AreaAvaliadorRepository areaAvaliadorRepository;
	
	private boolean existsById(Long id) {
		return areaAvaliadorRepository.existsById(id);
	}
	
	public AreaAvaliador findById(Long id) throws ResourceNotFoundException {
		AreaAvaliador areaAvaliador = areaAvaliadorRepository.findById(id).orElse(null);
		
		if(areaAvaliador == null) {
			throw new ResourceNotFoundException("AreaAvaliador não encontrado com o id: " + id);
		} else {
			return areaAvaliador;
		}
	}
	
	public Page<AreaAvaliadorDTO> findAll(Pageable pageable) {
		
		return new AreaAvaliadorDTO().converterListaAreaAvaliadorDTO(areaAvaliadorRepository.findAll(pageable)) ;
	}
	
	public Page<AreaAvaliadorDTO> findAllByIdArea(Long id, Pageable page) {
		Page<AreaAvaliador> areaAvaliadors = areaAvaliadorRepository.findByArea(id, page);
		return new AreaAvaliadorDTO().converterListaAreaAvaliadorDTO(areaAvaliadors);
	}

	public Page<AreaAvaliadorDTO> findAllByIdAvaliador(Long id, Pageable page) {
		Page<AreaAvaliador> areaAvaliadors = areaAvaliadorRepository.findByAvaliador(id, page);
		return new AreaAvaliadorDTO().converterListaAreaAvaliadorDTO(areaAvaliadors);
	}
	
	public AreaAvaliador save(AreaAvaliador areaAvaliador) throws BadResourceException, ResourceAlreadyExistsException {
		if (areaAvaliador.getArea() != null && areaAvaliador.getUsuario() != null) {
			if(existsById(areaAvaliador.getId())) {
				throw new ResourceAlreadyExistsException("AreaAvaliador com id: " + areaAvaliador.getId() + " já existe.");
			}			
			areaAvaliador.setStatus('A');
			areaAvaliador.setDataCadastro(Calendar.getInstance().getTime());
			AreaAvaliador areaAvaliadorNovo = areaAvaliadorRepository.save(areaAvaliador);			
			return areaAvaliadorNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar areaAvaliador");
			exe.addErrorMessage("AreaAvaliador esta vazio ou nulo");
			throw exe;
		}		
	}
	
	public void update(AreaAvaliador areaAvaliador) throws BadResourceException, ResourceNotFoundException {
		if (areaAvaliador.getArea() != null && areaAvaliador.getUsuario() != null) {
			if (!existsById(areaAvaliador.getId())) {
				throw new ResourceNotFoundException("AreaAvaliador não encontrado com o id: " + areaAvaliador.getId());
			}
			areaAvaliador.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			areaAvaliadorRepository.save(areaAvaliador);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar areaAvaliador");
			exe.addErrorMessage("AreaAvaliador esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("AreaAvaliador não encontrado com o id: " + id);
		} else {
			areaAvaliadorRepository.deleteById(id);
		}
	
	}  public Long count() {
		return areaAvaliadorRepository.count();
	}
	
}
