package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.Trabalho;
import lombok.Data;

@Data
public class TrabalhoDTO {
	private long id;
	private String titulo;
	private CategoriaDTO categoriaDTO;
	private EventoDTO eventoDTO;
	private String resultado;
	
	public TrabalhoDTO converter(Trabalho trabalho) {
		TrabalhoDTO trabalhoDTO = new TrabalhoDTO();
		BeanUtils.copyProperties(trabalho, trabalhoDTO);
		trabalhoDTO.setCategoriaDTO(categoriaDTO.converter(trabalho.getCategoria()));
		trabalhoDTO.setEventoDTO(eventoDTO.converter(trabalho.getEvento()));
		return trabalhoDTO;
	}
	
	public Page<TrabalhoDTO> converterListaTrabalhoDTO(Page<Trabalho> pageTrabalho) {
		Page<TrabalhoDTO> listaDTO = pageTrabalho.map(this::converter);
		return listaDTO;
	}
	
}
