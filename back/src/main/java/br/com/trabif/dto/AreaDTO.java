package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.Area;
import lombok.Data;

@Data
public class AreaDTO extends AuditoriaDTO {
	private long id;
	private String descricao;
	
	public AreaDTO converter(Area area) {
		AreaDTO areaDTO = new AreaDTO();
		BeanUtils.copyProperties(area, areaDTO);
		return areaDTO;
	}
	
	public Page<AreaDTO> converterListaAreaDTO(Page<Area> pageArea) {
		Page<AreaDTO> listaDTO = pageArea.map(this::converter);
		return listaDTO;
	}
	
}
