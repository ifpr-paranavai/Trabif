package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.AreaTrabalho;
import lombok.Data;

@Data
public class AreaTrabalhoDTO {
	private long id;
	private AreaDTO areaDTO;
	private TrabalhoDTO trabalhoDTO;
	
	public AreaTrabalhoDTO converter(AreaTrabalho areaTrabalho) {
		AreaTrabalhoDTO areaTrabalhoDTO = new AreaTrabalhoDTO();
		BeanUtils.copyProperties(areaTrabalho, areaTrabalhoDTO);
		areaTrabalhoDTO.setAreaDTO(areaDTO.converter(areaTrabalho.getArea()));
		areaTrabalhoDTO.setTrabalhoDTO(trabalhoDTO.converter(areaTrabalho.getTrabalho()));
		return areaTrabalhoDTO;
	}
	
	public Page<AreaTrabalhoDTO> converterListaAreaTrabalhoDTO(Page<AreaTrabalho> pageAreaTrabalho) {
		Page<AreaTrabalhoDTO> listaDTO = pageAreaTrabalho.map(this::converter);
		return listaDTO;
	}
	
}
