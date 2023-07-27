package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.AutorTrabalho;
import lombok.Data;

@Data
public class AutorTrabalhoDTO extends AuditoriaDTO {
	private long id;
	private UsuarioDTO usuarioDTO;
	private TrabalhoDTO trabalhoDTO;
	
	public AutorTrabalhoDTO converter(AutorTrabalho autorTrabalho) {
		AutorTrabalhoDTO autorTrabalhoDTO = new AutorTrabalhoDTO();
		BeanUtils.copyProperties(autorTrabalho, autorTrabalhoDTO);
		this.usuarioDTO = new UsuarioDTO();
		this.trabalhoDTO = new TrabalhoDTO();
		autorTrabalhoDTO.setUsuarioDTO(usuarioDTO.converter(autorTrabalho.getUsuario()));
		autorTrabalhoDTO.setTrabalhoDTO(trabalhoDTO.converter(autorTrabalho.getTrabalho()));
		return autorTrabalhoDTO;
	}
	
	public Page<AutorTrabalhoDTO> converterListaAutorTrabalhoDTO(Page<AutorTrabalho> pageAutorTrabalho) {
		return pageAutorTrabalho.map(this::converter);
	}
	
}
