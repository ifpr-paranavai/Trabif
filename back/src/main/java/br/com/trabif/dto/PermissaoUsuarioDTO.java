package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.PermissaoUsuario;
import lombok.Data;

@Data
public class PermissaoUsuarioDTO extends AuditoriaDTO {
	private long id;
	private PermissaoDTO permissaoDTO;
	private UsuarioDTO usuarioDTO;
	private EventoDTO eventoDTO;
	
	public PermissaoUsuarioDTO converter(PermissaoUsuario permissaoUsuario) {
		PermissaoUsuarioDTO permissaoUsuarioDTO = new PermissaoUsuarioDTO();
		BeanUtils.copyProperties(permissaoUsuario, permissaoUsuarioDTO);
		this.permissaoDTO = new PermissaoDTO();
		this.usuarioDTO = new UsuarioDTO();
		this.eventoDTO = new EventoDTO();
		permissaoUsuarioDTO.setPermissaoDTO(permissaoDTO.converter(permissaoUsuario.getPermissao()));
		permissaoUsuarioDTO.setUsuarioDTO(usuarioDTO.converter(permissaoUsuario.getUsuario()));
		permissaoUsuarioDTO.setEventoDTO(eventoDTO.converter(permissaoUsuario.getEvento()));
		return permissaoUsuarioDTO;
	}
	
	public Page<PermissaoUsuarioDTO> converterListaPermissaoUsuarioDTO(Page<PermissaoUsuario> pagePermissaoUsuario) {
		Page<PermissaoUsuarioDTO> listaDTO = pagePermissaoUsuario.map(this::converter);
		return listaDTO;
	}
	
}
