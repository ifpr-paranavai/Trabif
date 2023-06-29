package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.TrabalhoAvaliador;
import lombok.Data;

@Data
public class TrabalhoAvaliadorDTO extends AuditoriaDTO {
	private long id;
	private TrabalhoDTO trabalhoDTO;
	private UsuarioDTO avaliadorDTO;
	private ResultadoSubmissaoDTO resultadoSubmissaoDTO;
	
	public TrabalhoAvaliadorDTO converter(TrabalhoAvaliador trabalhoAvaliador) {
		TrabalhoAvaliadorDTO trabalhoAvaliadorDTO = new TrabalhoAvaliadorDTO();
		BeanUtils.copyProperties(trabalhoAvaliador, trabalhoAvaliadorDTO);
		trabalhoAvaliadorDTO.setTrabalhoDTO(trabalhoDTO.converter(trabalhoAvaliador.getTrabalho()));
		trabalhoAvaliadorDTO.setAvaliadorDTO(avaliadorDTO.converter(trabalhoAvaliador.getUsuario()));
		trabalhoAvaliadorDTO.setResultadoSubmissaoDTO(resultadoSubmissaoDTO.converter(trabalhoAvaliador.getResultadoSubmissao()));
		return trabalhoAvaliadorDTO;
	}
	
	public Page<TrabalhoAvaliadorDTO> converterListaTrabalhoAvaliadorDTO(Page<TrabalhoAvaliador> pageTrabalhoAvaliador) {
		return pageTrabalhoAvaliador.map(this::converter);
	}
	
}
