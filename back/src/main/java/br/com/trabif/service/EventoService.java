package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Evento;
import br.com.trabif.dto.EventoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	private boolean existsById(Long id) {
		return eventoRepository.existsById(id);
	}
	
	public Evento findById(Long id) throws ResourceNotFoundException {
		Evento evento = eventoRepository.findById(id).orElse(null);
		
		if(evento == null) {
			throw new ResourceNotFoundException("Evento não encontrado com o id: " + id);
		} else {
			return evento;
		}
	}
	
	public Page<EventoDTO> findAll(Pageable pageable) {
		
		return new EventoDTO().converterListaEventoDTO(eventoRepository.findAll(pageable)) ;
	}
	
	public Page<EventoDTO> findAllByNome(String descricao, Pageable page) {
		Page<Evento> eventos = eventoRepository.findByNome(descricao, page);
		
		
		return new EventoDTO().converterListaEventoDTO(eventos);
	}

	public Page<EventoDTO> findAllByIdOrganizador(Long id, Pageable page) {
		Page<Evento> eventos = eventoRepository.findByOrganizador(id, page);
		return new EventoDTO().converterListaEventoDTO(eventos);
	}
	
	public Evento save(Evento evento) throws BadResourceException, ResourceAlreadyExistsException {
		if(!evento.getNome().isEmpty()) {
			if(existsById(evento.getId())) {
				throw new ResourceAlreadyExistsException("Evento com id: " + evento.getId() + " já existe.");
			}			
			evento.setStatus('A');
			evento.setDataCadastro(Calendar.getInstance().getTime());
			Evento eventoNovo = eventoRepository.save(evento);
			return eventoNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar evento");
			exe.addErrorMessage("Evento esta vazio ou nulo");
			throw exe;
		}		
	}
	
	public void update(Evento evento) throws BadResourceException, ResourceNotFoundException {
		if (!evento.getNome().isEmpty()) {
			if (!existsById(evento.getId())) {
				throw new ResourceNotFoundException("Evento não encontrado com o id: " + evento.getId());
			}
			evento.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			eventoRepository.save(evento);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar evento");
			exe.addErrorMessage("Evento esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Evento não encontrado com o id: " + id);
		} else {
			eventoRepository.deleteById(id);
		}
	
	}  public Long count() {
		return eventoRepository.count();
	}
	
}
