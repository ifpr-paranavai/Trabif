package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.EmailTemplate;
import br.com.trabif.dto.EmailTemplateDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.EmailTemplateRepository;

@Service
public class EmailTemplateService {
	
	@Autowired
	private EmailTemplateRepository emailTemplateRepository;
	
	private boolean existsById(Long id) {
		return emailTemplateRepository.existsById(id);
	}
	
	public EmailTemplate findById(Long id) throws ResourceNotFoundException {
		EmailTemplate emailTemplate = emailTemplateRepository.findById(id).orElse(null);
		
		if(emailTemplate == null) {
			throw new ResourceNotFoundException("EmailTemplate não encontrado com o id: " + id);
		} else {
			return emailTemplate;
		}
	}
	
	public Page<EmailTemplateDTO> findAll(Pageable pageable) {
		
		return new EmailTemplateDTO().converterListaEmailTemplateDTO(emailTemplateRepository.findAll(pageable)) ;
	}
	
	public Page<EmailTemplateDTO> findAllByTitulo(String titulo, Pageable page) {
		Page<EmailTemplate> emailTemplates = emailTemplateRepository.findByTitulo(titulo, page);
		return new EmailTemplateDTO().converterListaEmailTemplateDTO(emailTemplates);
	}
	
	public EmailTemplate save(EmailTemplate emailTemplate) throws BadResourceException, ResourceAlreadyExistsException {
		if(!emailTemplate.getMensagem().isEmpty()) {
			if(existsById(emailTemplate.getId())) {
				throw new ResourceAlreadyExistsException("EmailTemplate com id: " + emailTemplate.getId() + " já existe.");
			}			
			emailTemplate.setStatus('A');
			emailTemplate.setDataCadastro(Calendar.getInstance().getTime());
			EmailTemplate emailTemplateNovo = emailTemplateRepository.save(emailTemplate);
			return emailTemplateNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar emailTemplate");
			exe.addErrorMessage("EmailTemplate esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(EmailTemplate emailTemplate) throws BadResourceException, ResourceNotFoundException {
		if (!emailTemplate.getMensagem().isEmpty()) {
			if (!existsById(emailTemplate.getId())) {
				throw new ResourceNotFoundException("EmailTemplate não encontrado com o id: " + emailTemplate.getId());
			}
			emailTemplate.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			emailTemplateRepository.save(emailTemplate);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar emailTemplate");
			exe.addErrorMessage("EmailTemplate esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("EmailTemplate não encontrado com o id: " + id);
		} else {
			emailTemplateRepository.deleteById(id);
		}
	
	}  public Long count() {
		return emailTemplateRepository.count();
	}
	
}
