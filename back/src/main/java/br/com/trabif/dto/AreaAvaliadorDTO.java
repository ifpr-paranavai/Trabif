package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.AreaAvaliador;
import lombok.Data;

@Data
public class AreaAvaliadorDTO extends AuditoriaDTO {
	private long id;
	private AreaDTO areaDTO;
	private UsuarioDTO usuarioDTO;
	
	public AreaAvaliadorDTO converter(AreaAvaliador areaAvaliador) {
		AreaAvaliadorDTO areaAvaliadorDTO = new AreaAvaliadorDTO();
		BeanUtils.copyProperties(areaAvaliador, areaAvaliadorDTO);
		this.areaDTO = new AreaDTO();
		this.usuarioDTO = new UsuarioDTO();
		areaAvaliadorDTO.setAreaDTO(areaDTO.converter(areaAvaliador.getArea()));
		areaAvaliadorDTO.setUsuarioDTO(usuarioDTO.converter(areaAvaliador.getUsuario()));
		return areaAvaliadorDTO;
	}
	
	public Page<AreaAvaliadorDTO> converterListaAreaAvaliadorDTO(Page<AreaAvaliador> pageAreaAvaliador) {
		return pageAreaAvaliador.map(this::converter);
	}
	
}
