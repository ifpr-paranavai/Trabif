package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO extends AuditoriaDTO {
	private long id;
	private String nome;
	private String cpf;
	private String email;
	
	public UsuarioDTO converter(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		BeanUtils.copyProperties(usuario, usuarioDTO);
		return usuarioDTO;
	}
	
	public Page<UsuarioDTO> converterListaUsuarioDTO(Page<Usuario> pageUsuario) {
		Page<UsuarioDTO> listaDTO = pageUsuario.map(this::converter);
		return listaDTO;
	}
	
}
