package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.PermissaoUsuario;
import br.com.trabif.dto.PermissaoUsuarioDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.PermissaoUsuarioRepository;

@Service
public class PermissaoUsuarioService {
	
	@Autowired
	private PermissaoUsuarioRepository permissaoUsuarioRepository;
	
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

	public Page<PermissaoUsuarioDTO> findAllByIdUsuario(Long id, Pageable page) {
		Page<PermissaoUsuario> permissaoUsuarios = permissaoUsuarioRepository.findByUsuario(id, page);
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
