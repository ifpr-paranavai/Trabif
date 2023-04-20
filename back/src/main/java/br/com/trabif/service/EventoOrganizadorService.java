package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.EventoOrganizador;
import br.com.trabif.dto.EventoOrganizadorDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.EventoOrganizadorRepository;

@Service
public class EventoOrganizadorService {
	
	@Autowired
	private EventoOrganizadorRepository eventoOrganizadorRepository;
	
	private boolean existsById(Long id) {
		return eventoOrganizadorRepository.existsById(id);
	}
	
	public EventoOrganizador findById(Long id) throws ResourceNotFoundException {
		EventoOrganizador eventoOrganizador = eventoOrganizadorRepository.findById(id).orElse(null);
		
		if(eventoOrganizador == null) {
			throw new ResourceNotFoundException("EventoOrganizador não encontrado com o id: " + id);
		} else {
			return eventoOrganizador;
		}
	}
	
	public Page<EventoOrganizadorDTO> findAll(Pageable pageable) {
		
		return new EventoOrganizadorDTO().converterListaEventoOrganizadorDTO(eventoOrganizadorRepository.findAll(pageable)) ;
	}
	
	public Page<EventoOrganizadorDTO> findAllByIdEvento(Long id, Pageable page) {
		Page<EventoOrganizador> eventoOrganizadores = eventoOrganizadorRepository.findByEvento(id, page);
		return new EventoOrganizadorDTO().converterListaEventoOrganizadorDTO(eventoOrganizadores);
	}

	public Page<EventoOrganizadorDTO> findAllByIdOrganizador(Long id, Pageable page) {
		Page<EventoOrganizador> eventoOrganizadores = eventoOrganizadorRepository.findByOrganizador(id, page);
		return new EventoOrganizadorDTO().converterListaEventoOrganizadorDTO(eventoOrganizadores);
	}
	
	public EventoOrganizador save(EventoOrganizador eventoOrganizador) throws BadResourceException, ResourceAlreadyExistsException {
		if (eventoOrganizador.getEvento() != null && eventoOrganizador.getOrganizador() != null) {
			if(existsById(eventoOrganizador.getId())) {
				throw new ResourceAlreadyExistsException("EventoOrganizador com id: " + eventoOrganizador.getId() + " já existe.");
			}			
			eventoOrganizador.setStatus('A');
			eventoOrganizador.setDataCadastro(Calendar.getInstance().getTime());
			EventoOrganizador eventoOrganizadorNovo = eventoOrganizadorRepository.save(eventoOrganizador);
			return eventoOrganizadorNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar eventoOrganizador");
			exe.addErrorMessage("EventoOrganizador esta vazio ou nulo");
			throw exe;
		}		
	}
	
	public void update(EventoOrganizador eventoOrganizador) throws BadResourceException, ResourceNotFoundException {
		if (eventoOrganizador.getEvento() != null && eventoOrganizador.getOrganizador() != null) {
			if (!existsById(eventoOrganizador.getId())) {
				throw new ResourceNotFoundException("EventoOrganizador não encontrado com o id: " + eventoOrganizador.getId());
			}
			eventoOrganizador.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			eventoOrganizadorRepository.save(eventoOrganizador);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar eventoOrganizador");
			exe.addErrorMessage("EventoOrganizador esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("EventoOrganizador não encontrado com o id: " + id);
		} else {
			eventoOrganizadorRepository.deleteById(id);
		}
	
	}  public Long count() {
		return eventoOrganizadorRepository.count();
	}
	
}
