package br.com.trabif.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.Avaliador;
import lombok.Data;

@Data
public class AvaliadorDTO extends AuditoriaDTO {
	private long id;
	private String nome;
	private String cpf;
	private String email;
	
	public AvaliadorDTO converter(Avaliador avaliador) {
		AvaliadorDTO avaliadorDTO = new AvaliadorDTO();
		BeanUtils.copyProperties(avaliador, avaliadorDTO);
		return avaliadorDTO;
	}
	
	public Page<AvaliadorDTO> converterListaAvaliadorDTO(Page<Avaliador> pageAvaliador) {
		Page<AvaliadorDTO> listaDTO = pageAvaliador.map(this::converter);
		return listaDTO;
	}
	
}
