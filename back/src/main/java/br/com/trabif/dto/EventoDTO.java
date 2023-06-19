package br.com.trabif.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.Evento;
import lombok.Data;

@Data
public class EventoDTO extends AuditoriaDTO {
	private long id;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	
	public EventoDTO converter(Evento evento) {
		EventoDTO eventoDTO = new EventoDTO();
		BeanUtils.copyProperties(evento, eventoDTO);
		return eventoDTO;
	}
	
	public Page<EventoDTO> converterListaEventoDTO(Page<Evento> pageEvento) {
		Page<EventoDTO> listaDTO = pageEvento.map(this::converter);
		return listaDTO;
	}
	
}
