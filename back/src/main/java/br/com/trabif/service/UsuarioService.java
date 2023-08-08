package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private boolean existsById(Long id) {
		return usuarioRepository.existsById(id);
	}
	
	public Usuario findById(Long id) throws ResourceNotFoundException {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		usuario.setSenha(null);
		
		if(usuario == null) {
			throw new ResourceNotFoundException("Usuario não encontrado com o id: " + id);
		} else {
			return usuario;
		}
	}

	public Usuario findByEmail(String email) throws ResourceNotFoundException {
		return usuarioRepository.findByEmail(email);
	}

	public Usuario findByCpf(String cpf) throws ResourceNotFoundException {
		return usuarioRepository.findByCpf(cpf);
	}
	
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		
		return new UsuarioDTO().converterListaUsuarioDTO(usuarioRepository.findAll(pageable)) ;
	}
	
	public Page<UsuarioDTO> findAllByNome(String descricao, Pageable page) {
		Page<Usuario> usuarios = usuarioRepository.findByNome(descricao, page);
		
		
		return new UsuarioDTO().converterListaUsuarioDTO(usuarios);
	}
	
	public Usuario save(Usuario usuario) throws BadResourceException, ResourceAlreadyExistsException {
		if((usuario.getNome() != null && !usuario.getNome().isEmpty()) ||
		 (usuario.getEmail() != null && !usuario.getEmail().isEmpty())) {
			if(existsById(usuario.getId())) {
				throw new ResourceAlreadyExistsException("Usuario com id: " + usuario.getId() + " já existe.");
			}			
			usuario.setStatus('A');
			usuario.setDataCadastro(Calendar.getInstance().getTime());
			if(usuario.getSenha() != null) {
				usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			}
			Usuario usuarioNovo = usuarioRepository.save(usuario);
			usuarioNovo.setSenha(null);
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
	
	public UsuarioDTO loginUsuario(Usuario usuario) {
		UsuarioDTO usuarioDTO = null;
		if(usuario.getEmail() == null)
			return usuarioDTO;
		if (usuario.getSenha() == null)
			return usuarioDTO;
		Usuario usuarioDb = usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioDb == null)
			return usuarioDTO;
		if(passwordEncoder.matches(usuario.getSenha(), usuarioDb.getSenha())) {
			usuarioDTO = new UsuarioDTO().converter(usuarioDb);
		}
		return usuarioDTO;
	}
 
}
