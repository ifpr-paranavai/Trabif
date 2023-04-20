package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Area;
import br.com.trabif.dto.AreaDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.AreaRepository;

@Service
public class AreaService {
	
	@Autowired
	private AreaRepository areaRepository;
	
	private boolean existsById(Long id) {
		return areaRepository.existsById(id);
	}
	
	public Area findById(Long id) throws ResourceNotFoundException {
		Area area = areaRepository.findById(id).orElse(null);
		
		if(area == null) {
			throw new ResourceNotFoundException("Area não encontrada com o id: " + id);
		} else {
			return area;
		}
	}
	
	public Page<AreaDTO> findAll(Pageable pageable) {
		
		return new AreaDTO().converterListaAreaDTO(areaRepository.findAll(pageable)) ;
	}
	
	public Page<AreaDTO> findAllByDescricao(String descricao, Pageable page) {
		Page<Area> areas = areaRepository.findByDescricao(descricao, page);
		
		
		return new AreaDTO().converterListaAreaDTO(areas);
	}
	
	public Area save(Area area) throws BadResourceException, ResourceAlreadyExistsException {
		if(!area.getDescricao().isEmpty()) {
			if(existsById(area.getId())) {
				throw new ResourceAlreadyExistsException("Area com id: " + area.getId() + " já existe.");
			}			
			area.setStatus('A');
			area.setDataCadastro(Calendar.getInstance().getTime());
			Area areaNovo = areaRepository.save(area);
			return areaNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar area");
			exe.addErrorMessage("Area esta vazia ou nula");
			throw exe;
		}
		
		
	}
	
	public void update(Area area) throws BadResourceException, ResourceNotFoundException {
		if (!area.getDescricao().isEmpty()) {
			if (!existsById(area.getId())) {
				throw new ResourceNotFoundException("Area não encontrada com o id: " + area.getId());
			}
			area.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			areaRepository.save(area);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar area");
			exe.addErrorMessage("Area esta vazia ou nula");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Area não encontrada com o id: " + id);
		} else {
			areaRepository.deleteById(id);
		}
	
	}  public Long count() {
		return areaRepository.count();
	}
	
}
