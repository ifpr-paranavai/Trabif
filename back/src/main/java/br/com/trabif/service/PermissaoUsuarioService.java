package br.com.trabif.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Permissao;
import br.com.trabif.domain.PermissaoUsuario;
import br.com.trabif.domain.Usuario;
import br.com.trabif.dto.PermissaoUsuarioDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.PermissaoUsuarioRepository;

@Service
public class PermissaoUsuarioService {
	
	@Autowired
	private PermissaoUsuarioRepository permissaoUsuarioRepository;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PermissaoService permissaoService;

	@Autowired
	private EmailService emailService;
	
	private boolean existsById(Long id) {
		return permissaoUsuarioRepository.existsById(id);
	}
	
	public PermissaoUsuario findById(Long id) throws ResourceNotFoundException {
		PermissaoUsuario permissaoUsuario = permissaoUsuarioRepository.findById(id).orElse(null);
		
		if(permissaoUsuario == null) {
			throw new ResourceNotFoundException("PermissaoUsuario não encontrado com o id: " + id);
		} else {
			return permissaoUsuario;
		}
	}
	
	public Page<PermissaoUsuarioDTO> findAll(Pageable pageable) {
		
		return new PermissaoUsuarioDTO().converterListaPermissaoUsuarioDTO(permissaoUsuarioRepository.findAll(pageable)) ;
	}
	
	public Page<PermissaoUsuarioDTO> findAllByIdPermissao(Long id, Pageable page) {
		Page<PermissaoUsuario> permissaoUsuarios = permissaoUsuarioRepository.findByPermissao(id, page);
		return new PermissaoUsuarioDTO().converterListaPermissaoUsuarioDTO(permissaoUsuarios);
	}

	public Page<PermissaoUsuarioDTO> findAllAutores(Long idEvento, Pageable page) {
		Long idPermissao = (long) 1;
		return this.findAllByIdPermissaoAndIdEvento(idPermissao, idEvento, page);
	}

	public Page<PermissaoUsuarioDTO> findAllOrganizadores(Long idEvento, Pageable page) {
		Long idPermissao = (long) 2;
		return this.findAllByIdPermissaoAndIdEvento(idPermissao, idEvento, page);
	}

	public Page<PermissaoUsuarioDTO> findAllAvaliadores(Long idEvento, Pageable page) {
		Long idPermissao = (long) 3;
		return this.findAllByIdPermissaoAndIdEvento(idPermissao, idEvento, page);
	}	

	public Page<PermissaoUsuarioDTO> findAllByIdUsuario(Long id, Pageable page) {
		Page<PermissaoUsuario> permissaoUsuarios = permissaoUsuarioRepository.findByUsuario(id, page);
		return new PermissaoUsuarioDTO().converterListaPermissaoUsuarioDTO(permissaoUsuarios);
	}

	public Page<PermissaoUsuarioDTO> findAllByIdEvento(Long id, Pageable page) {
		Page<PermissaoUsuario> permissaoUsuarios = permissaoUsuarioRepository.findByEvento(id, page);
		return new PermissaoUsuarioDTO().converterListaPermissaoUsuarioDTO(permissaoUsuarios);
	}

	public Page<PermissaoUsuarioDTO> findAllByIdUsuarioAndIdEvento(Long idUsuario, Long idEvento, Pageable page) {
		Page<PermissaoUsuario> permissaoUsuarios = permissaoUsuarioRepository.findByUsuarioAndEvento(idUsuario, idEvento, page);
		return new PermissaoUsuarioDTO().converterListaPermissaoUsuarioDTO(permissaoUsuarios);
	}

	public Page<PermissaoUsuarioDTO> findAllByIdPermissaoAndIdEvento(Long idPermissao, Long idEvento, Pageable page) {
		Page<PermissaoUsuario> permissaoUsuarios = permissaoUsuarioRepository.findByPermissaoAndEvento(idPermissao, idEvento, page);
		return new PermissaoUsuarioDTO().converterListaPermissaoUsuarioDTO(permissaoUsuarios);
	}
	
	public PermissaoUsuario save(PermissaoUsuario permissaoUsuario) throws BadResourceException, ResourceAlreadyExistsException {
		if (permissaoUsuario.getPermissao() != null && permissaoUsuario.getUsuario() != null) {
			if(existsById(permissaoUsuario.getId())) {
				throw new ResourceAlreadyExistsException("PermissaoUsuario com id: " + permissaoUsuario.getId() + " já existe.");
			}			
			permissaoUsuario.setStatus('A');
			permissaoUsuario.setDataCadastro(Calendar.getInstance().getTime());
			PermissaoUsuario permissaoUsuarioNovo = permissaoUsuarioRepository.save(permissaoUsuario);
			return permissaoUsuarioNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar permissaoUsuario");
			exe.addErrorMessage("PermissaoUsuario esta vazio ou nulo");
			throw exe;
		}		
	}

	public PermissaoUsuario saveAutor(PermissaoUsuario permissaoUsuario) throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
		Usuario usuario;
		Permissao avaliador = permissaoService.findById((long) 1);
		try {
			usuario = usuarioService.findByEmail(permissaoUsuario.getUsuario().getEmail());
		} catch (ResourceNotFoundException e) {
			usuario = usuarioService.save(permissaoUsuario.getUsuario());
		}
		permissaoUsuario.setUsuario(usuario);
		permissaoUsuario.setPermissao(avaliador);
		return this.save(permissaoUsuario);	
	}

	public PermissaoUsuario saveOrganizador(PermissaoUsuario permissaoUsuario) throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
		Usuario usuario;
		Permissao avaliador = permissaoService.findById((long) 2);
		try {
			usuario = usuarioService.findByEmail(permissaoUsuario.getUsuario().getEmail());
		} catch (ResourceNotFoundException e) {
			usuario = usuarioService.save(permissaoUsuario.getUsuario());
		}
		permissaoUsuario.setUsuario(usuario);
		permissaoUsuario.setPermissao(avaliador);
		return this.save(permissaoUsuario);		
	}

	public PermissaoUsuario saveAvaliador(PermissaoUsuario permissaoUsuario) throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
		Usuario usuario;
		Permissao avaliador = permissaoService.findById((long) 3);
		try {
			usuario = usuarioService.findByEmail(permissaoUsuario.getUsuario().getEmail());
		} catch (ResourceNotFoundException e) {
			usuario = usuarioService.save(permissaoUsuario.getUsuario());

			String titulo = permissaoUsuario.getEvento().getNome();
			Map<String, String> propriedades = new HashMap<>();
			propriedades.put("titulo", titulo);
			String mensagem = "<h1>Olá,</h1><p> Gostaríamos de informá-lo(a) que você foi cadastrado(a) como avaliador(a) do evento [evento-nome], acesse o site para finalizar seu cadastro: [site]. </p> <p> Atenciosamente, <br> Sistema </p>";
			propriedades.put("mensagem", mensagem);
			propriedades.put("evento-nome", titulo);
			propriedades.put("site", "http://localhost:4200/finalizar-cadastro/" + usuario.getId());

			emailService.enviarEmailTemplate(permissaoUsuario.getUsuario().getEmail(), null);
		}
		permissaoUsuario.setUsuario(usuario);
		permissaoUsuario.setPermissao(avaliador);
		return this.save(permissaoUsuario);
	}
	
	public void update(PermissaoUsuario permissaoUsuario) throws BadResourceException, ResourceNotFoundException {
		if (permissaoUsuario.getPermissao() != null && permissaoUsuario.getUsuario() != null) {
			if (!existsById(permissaoUsuario.getId())) {
				throw new ResourceNotFoundException("PermissaoUsuario não encontrado com o id: " + permissaoUsuario.getId());
			}
			permissaoUsuario.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			permissaoUsuarioRepository.save(permissaoUsuario);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar permissaoUsuario");
			exe.addErrorMessage("PermissaoUsuario esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("PermissaoUsuario não encontrado com o id: " + id);
		} else {
			permissaoUsuarioRepository.deleteById(id);
		}
	
	}  public Long count() {
		return permissaoUsuarioRepository.count();
	}
	
}
