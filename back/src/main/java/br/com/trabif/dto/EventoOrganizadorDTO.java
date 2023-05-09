package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.EventoOrganizador;
import lombok.Data;

@Data
public class EventoOrganizadorDTO extends AuditoriaDTO {
	private long id;
	private EventoDTO eventoDTO;
	private OrganizadorDTO organizadorDTO;
	
	public EventoOrganizadorDTO converter(EventoOrganizador eventoOrganizador) {
		EventoOrganizadorDTO eventoOrganizadorDTO = new EventoOrganizadorDTO();
		BeanUtils.copyProperties(eventoOrganizador, eventoOrganizadorDTO);
		eventoOrganizadorDTO.setEventoDTO(eventoDTO.converter(eventoOrganizador.getEvento()));
		eventoOrganizadorDTO.setOrganizadorDTO(organizadorDTO.converter(eventoOrganizador.getOrganizador()));
		return eventoOrganizadorDTO;
	}
	
	public Page<EventoOrganizadorDTO> converterListaEventoOrganizadorDTO(Page<EventoOrganizador> pageEventoOrganizador) {
		Page<EventoOrganizadorDTO> listaDTO = pageEventoOrganizador.map(this::converter);
		return listaDTO;
	}
	
}
