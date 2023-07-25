package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Evento;
import br.com.trabif.domain.PermissaoUsuario;
import br.com.trabif.domain.Usuario;
import br.com.trabif.dto.EventoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.EventoRepository;
import br.com.trabif.repository.PermissaoRepository;
import br.com.trabif.repository.UsuarioRepository;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PermissaoUsuarioService permissaoUsuarioService;
	
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
	
	public Evento save(Evento evento, Long usuarioId) throws BadResourceException, ResourceAlreadyExistsException {
		if(!evento.getNome().isEmpty()) {
			if(existsById(evento.getId())) {
				throw new ResourceAlreadyExistsException("Evento com id: " + evento.getId() + " já existe.");
			}			
			evento.setStatus('A');
			evento.setDataCadastro(Calendar.getInstance().getTime());
			Evento eventoNovo = eventoRepository.save(evento);
			PermissaoUsuario permissaoUsuario = new PermissaoUsuario();
			Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
			usuario.setSenha(null);
			Long permissaoId = (long) 2;
			permissaoUsuario.setEvento(eventoNovo);
			permissaoUsuario.setUsuario(usuario);
			permissaoUsuario.setPermissao(permissaoRepository.findById(permissaoId).orElse(null));
			permissaoUsuarioService.save(permissaoUsuario);
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
