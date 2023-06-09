package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Usuario;
import br.com.trabif.dto.UsuarioDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private boolean existsById(Long id) {
		return usuarioRepository.existsById(id);
	}
	
	public Usuario findById(Long id) throws ResourceNotFoundException {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		
		if(usuario == null) {
			throw new ResourceNotFoundException("Usuario não encontrado com o id: " + id);
		} else {
			return usuario;
		}
	}
	
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		
		return new UsuarioDTO().converterListaUsuarioDTO(usuarioRepository.findAll(pageable)) ;
	}
	
	public Page<UsuarioDTO> findAllByNome(String descricao, Pageable page) {
		Page<Usuario> usuarios = usuarioRepository.findByNome(descricao, page);
		
		
		return new UsuarioDTO().converterListaUsuarioDTO(usuarios);
	}
	
	public Usuario save(Usuario usuario) throws BadResourceException, ResourceAlreadyExistsException {
		if(!usuario.getNome().isEmpty()) {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			if(existsById(usuario.getId())) {
				throw new ResourceAlreadyExistsException("Usuario com id: " + usuario.getId() + " já existe.");
			}			
			usuario.setStatus('A');
			usuario.setDataCadastro(Calendar.getInstance().getTime());
			Usuario usuarioNovo = usuarioRepository.save(usuario);
			return usuarioNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar usuario");
			exe.addErrorMessage("Usuario esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(Usuario usuario) throws BadResourceException, ResourceNotFoundException {
		if (!usuario.getNome().isEmpty()) {
			if (!existsById(usuario.getId())) {
				throw new ResourceNotFoundException("Usuario não encontrado com o id: " + usuario.getId());
			}
			usuario.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			usuarioRepository.save(usuario);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar usuario");
			exe.addErrorMessage("Usuario esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Usuario não encontrado com o id: " + id);
		} else {
			usuarioRepository.deleteById(id);
		}
	
	}  public Long count() {
		return usuarioRepository.count();
	}
	
}
