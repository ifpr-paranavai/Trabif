package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.EventoEmailTemplate;
import lombok.Data;

@Data
public class EventoEmailTemplateDTO extends AuditoriaDTO {
	private long id;
	private EventoDTO eventoDTO;
	private EmailTemplateDTO emailTemplateDTO;
	
	public EventoEmailTemplateDTO converter(EventoEmailTemplate eventoEmailTemplate) {
		EventoEmailTemplateDTO eventoEmailTemplateDTO = new EventoEmailTemplateDTO();
		BeanUtils.copyProperties(eventoEmailTemplate, eventoEmailTemplateDTO);
		this.eventoDTO = new EventoDTO();
		this.emailTemplateDTO = new EmailTemplateDTO();
		eventoEmailTemplateDTO.setEventoDTO(eventoDTO.converter(eventoEmailTemplate.getEvento()));
		eventoEmailTemplateDTO.setEmailTemplateDTO(emailTemplateDTO.converter(eventoEmailTemplate.getEmailTemplate()));
		return eventoEmailTemplateDTO;
	}
	
	public Page<EventoEmailTemplateDTO> converterListaEventoEmailTemplateDTO(Page<EventoEmailTemplate> pageEventoEmailTemplate) {
		return pageEventoEmailTemplate.map(this::converter);
	}
	
}
