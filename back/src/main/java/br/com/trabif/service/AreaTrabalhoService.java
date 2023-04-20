package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.AreaTrabalho;
import br.com.trabif.dto.AreaTrabalhoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.AreaTrabalhoRepository;

@Service
public class AreaTrabalhoService {
	
	@Autowired
	private AreaTrabalhoRepository areaTrabalhoRepository;
	
	private boolean existsById(Long id) {
		return areaTrabalhoRepository.existsById(id);
	}
	
	public AreaTrabalho findById(Long id) throws ResourceNotFoundException {
		AreaTrabalho areaTrabalho = areaTrabalhoRepository.findById(id).orElse(null);
		
		if(areaTrabalho == null) {
			throw new ResourceNotFoundException("AreaTrabalho não encontrado com o id: " + id);
		} else {
			return areaTrabalho;
		}
	}
	
	public Page<AreaTrabalhoDTO> findAll(Pageable pageable) {
		
		return new AreaTrabalhoDTO().converterListaAreaTrabalhoDTO(areaTrabalhoRepository.findAll(pageable)) ;
	}
	
	public Page<AreaTrabalhoDTO> findAllByIdArea(Long id, Pageable page) {
		Page<AreaTrabalho> areaTrabalhos = areaTrabalhoRepository.findByArea(id, page);
		return new AreaTrabalhoDTO().converterListaAreaTrabalhoDTO(areaTrabalhos);
	}

	public Page<AreaTrabalhoDTO> findAllByIdTrabalho(Long id, Pageable page) {
		Page<AreaTrabalho> areaTrabalhos = areaTrabalhoRepository.findByTrabalho(id, page);
		return new AreaTrabalhoDTO().converterListaAreaTrabalhoDTO(areaTrabalhos);
	}
	
	public AreaTrabalho save(AreaTrabalho areaTrabalho) throws BadResourceException, ResourceAlreadyExistsException {
		if (areaTrabalho.getArea() != null && areaTrabalho.getTrabalho() != null) {
			if(existsById(areaTrabalho.getId())) {
				throw new ResourceAlreadyExistsException("AreaTrabalho com id: " + areaTrabalho.getId() + " já existe.");
			}			
			areaTrabalho.setStatus('A');
			areaTrabalho.setDataCadastro(Calendar.getInstance().getTime());
			AreaTrabalho areaTrabalhoNovo = areaTrabalhoRepository.save(areaTrabalho);
			return areaTrabalhoNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar areaTrabalho");
			exe.addErrorMessage("AreaTrabalho esta vazio ou nulo");
			throw exe;
		}		
	}
	
	public void update(AreaTrabalho areaTrabalho) throws BadResourceException, ResourceNotFoundException {
		if (areaTrabalho.getArea() != null && areaTrabalho.getTrabalho() != null) {
			if (!existsById(areaTrabalho.getId())) {
				throw new ResourceNotFoundException("AreaTrabalho não encontrado com o id: " + areaTrabalho.getId());
			}
			areaTrabalho.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			areaTrabalhoRepository.save(areaTrabalho);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar areaTrabalho");
			exe.addErrorMessage("AreaTrabalho esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("AreaTrabalho não encontrado com o id: " + id);
		} else {
			areaTrabalhoRepository.deleteById(id);
		}
	
	}  public Long count() {
		return areaTrabalhoRepository.count();
	}
	
}
