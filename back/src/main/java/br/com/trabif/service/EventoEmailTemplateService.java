package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.EventoEmailTemplate;
import br.com.trabif.dto.EventoEmailTemplateDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.EventoEmailTemplateRepository;

@Service
public class EventoEmailTemplateService {
	
	@Autowired
	private EventoEmailTemplateRepository eventoEmailTemplateRepository;
	
	private boolean existsById(Long id) {
		return eventoEmailTemplateRepository.existsById(id);
	}
	
	public EventoEmailTemplate findById(Long id) throws ResourceNotFoundException {
		EventoEmailTemplate eventoEmailTemplate = eventoEmailTemplateRepository.findById(id).orElse(null);
		
		if(eventoEmailTemplate == null) {
			throw new ResourceNotFoundException("EventoEmailTemplate não encontrado com o id: " + id);
		} else {
			return eventoEmailTemplate;
		}
	}
	
	public Page<EventoEmailTemplateDTO> findAll(Pageable pageable) {
		
		return new EventoEmailTemplateDTO().converterListaEventoEmailTemplateDTO(eventoEmailTemplateRepository.findAll(pageable)) ;
	}
	
	public Page<EventoEmailTemplateDTO> findAllByIdEvento(Long id, Pageable page) {
		Page<EventoEmailTemplate> eventoEmailTemplates = eventoEmailTemplateRepository.findByEvento(id, page);
		return new EventoEmailTemplateDTO().converterListaEventoEmailTemplateDTO(eventoEmailTemplates);
	}

	public Page<EventoEmailTemplateDTO> findAllByIdEmailTemplate(Long id, Pageable page) {
		Page<EventoEmailTemplate> eventoEmailTemplates = eventoEmailTemplateRepository.findByEmailTemplate(id, page);
		return new EventoEmailTemplateDTO().converterListaEventoEmailTemplateDTO(eventoEmailTemplates);
	}
	
	public EventoEmailTemplate save(EventoEmailTemplate eventoEmailTemplate) throws BadResourceException, ResourceAlreadyExistsException {
		if (eventoEmailTemplate.getEvento() != null && eventoEmailTemplate.getEmailTemplate() != null) {
			if(existsById(eventoEmailTemplate.getId())) {
				throw new ResourceAlreadyExistsException("EventoEmailTemplate com id: " + eventoEmailTemplate.getId() + " já existe.");
			}			
			eventoEmailTemplate.setStatus('A');
			eventoEmailTemplate.setDataCadastro(Calendar.getInstance().getTime());
			EventoEmailTemplate eventoEmailTemplateNovo = eventoEmailTemplateRepository.save(eventoEmailTemplate);
			return eventoEmailTemplateNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar eventoEmailTemplate");
			exe.addErrorMessage("EventoEmailTemplate esta vazio ou nulo");
			throw exe;
		}		
	}
	
	public void update(EventoEmailTemplate eventoEmailTemplate) throws BadResourceException, ResourceNotFoundException {
		if (eventoEmailTemplate.getEvento() != null && eventoEmailTemplate.getEmailTemplate() != null) {
			if (!existsById(eventoEmailTemplate.getId())) {
				throw new ResourceNotFoundException("EventoEmailTemplate não encontrado com o id: " + eventoEmailTemplate.getId());
			}
			eventoEmailTemplate.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			eventoEmailTemplateRepository.save(eventoEmailTemplate);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar eventoEmailTemplate");
			exe.addErrorMessage("EventoEmailTemplate esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("EventoEmailTemplate não encontrado com o id: " + id);
		} else {
			eventoEmailTemplateRepository.deleteById(id);
		}
	
	}  public Long count() {
		return eventoEmailTemplateRepository.count();
	}
	
}
