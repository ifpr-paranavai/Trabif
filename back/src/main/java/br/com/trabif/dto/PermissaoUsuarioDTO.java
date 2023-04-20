package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.PermissaoUsuario;
import lombok.Data;

@Data
public class PermissaoUsuarioDTO {
	private long id;
	private PermissaoDTO permissaoDTO;
	private UsuarioDTO usuarioDTO;
	
	public PermissaoUsuarioDTO converter(PermissaoUsuario permissaoUsuario) {
		PermissaoUsuarioDTO permissaoUsuarioDTO = new PermissaoUsuarioDTO();
		BeanUtils.copyProperties(permissaoUsuario, permissaoUsuarioDTO);
		permissaoUsuarioDTO.setPermissaoDTO(permissaoDTO.converter(permissaoUsuario.getPermissao()));
		permissaoUsuarioDTO.setUsuarioDTO(usuarioDTO.converter(permissaoUsuario.getUsuario()));
		return permissaoUsuarioDTO;
	}
	
	public Page<PermissaoUsuarioDTO> converterListaPermissaoUsuarioDTO(Page<PermissaoUsuario> pagePermissaoUsuario) {
		Page<PermissaoUsuarioDTO> listaDTO = pagePermissaoUsuario.map(this::converter);
		return listaDTO;
	}
	
}
