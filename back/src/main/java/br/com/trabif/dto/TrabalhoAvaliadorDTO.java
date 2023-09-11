package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.TrabalhoAvaliador;
import lombok.Data;

@Data
public class TrabalhoAvaliadorDTO extends AuditoriaDTO {
	private long id;
	private TrabalhoDTO trabalhoDTO;
	private UsuarioDTO usuarioDTO;
	private ResultadoSubmissaoDTO resultadoSubmissaoDTO;
	
	public TrabalhoAvaliadorDTO converter(TrabalhoAvaliador trabalhoAvaliador) {
		TrabalhoAvaliadorDTO trabalhoAvaliadorDTO = new TrabalhoAvaliadorDTO();
		BeanUtils.copyProperties(trabalhoAvaliador, trabalhoAvaliadorDTO);
		this.trabalhoDTO = new TrabalhoDTO();
		this.usuarioDTO = new UsuarioDTO();
		this.resultadoSubmissaoDTO = new ResultadoSubmissaoDTO();
		trabalhoAvaliadorDTO.setTrabalhoDTO(trabalhoDTO.converter(trabalhoAvaliador.getTrabalho()));
		trabalhoAvaliadorDTO.setUsuarioDTO(usuarioDTO.converter(trabalhoAvaliador.getUsuario()));
		if (trabalhoAvaliador.getResultadoSubmissao() != null) {
			trabalhoAvaliadorDTO.setResultadoSubmissaoDTO(resultadoSubmissaoDTO.converter(trabalhoAvaliador.getResultadoSubmissao()));
		}
		return trabalhoAvaliadorDTO;
	}
	
	public Page<TrabalhoAvaliadorDTO> converterListaTrabalhoAvaliadorDTO(Page<TrabalhoAvaliador> pageTrabalhoAvaliador) {
		return pageTrabalhoAvaliador.map(this::converter);
	}
	
}
