package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.EmailTemplate;
import lombok.Data;

@Data
public class EmailTemplateDTO {
	private long id;
	private String titulo;
	private String mensagem;
	
	public EmailTemplateDTO converter(EmailTemplate emailTemplate) {
		EmailTemplateDTO emailTemplateDTO = new EmailTemplateDTO();
		BeanUtils.copyProperties(emailTemplate, emailTemplateDTO);
		return emailTemplateDTO;
	}
	
	public Page<EmailTemplateDTO> converterListaEmailTemplateDTO(Page<EmailTemplate> pageEmailTemplate) {
		Page<EmailTemplateDTO> listaDTO = pageEmailTemplate.map(this::converter);
		return listaDTO;
	}
	
}
