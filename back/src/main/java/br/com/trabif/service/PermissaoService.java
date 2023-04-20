package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Permissao;
import br.com.trabif.dto.PermissaoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.PermissaoRepository;

@Service
public class PermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	private boolean existsById(Long id) {
		return permissaoRepository.existsById(id);
	}
	
	public Permissao findById(Long id) throws ResourceNotFoundException {
		Permissao permissao = permissaoRepository.findById(id).orElse(null);
		
		if(permissao == null) {
			throw new ResourceNotFoundException("Permissao não encontrado com o id: " + id);
		} else {
			return permissao;
		}
	}
	
	public Page<PermissaoDTO> findAll(Pageable pageable) {
		
		return new PermissaoDTO().converterListaPermissaoDTO(permissaoRepository.findAll(pageable)) ;
	}
	
	public Page<PermissaoDTO> findAllByDescricao(String descricao, Pageable page) {
		Page<Permissao> permissoes = permissaoRepository.findByDescricao(descricao, page);
		
		
		return new PermissaoDTO().converterListaPermissaoDTO(permissoes);
	}
	
	public Permissao save(Permissao permissao) throws BadResourceException, ResourceAlreadyExistsException {
		if(!permissao.getDescricao().isEmpty()) {
			if(existsById(permissao.getId())) {
				throw new ResourceAlreadyExistsException("Permissao com id: " + permissao.getId() + " já existe.");
			}			
			permissao.setStatus('A');
			permissao.setDataCadastro(Calendar.getInstance().getTime());
			Permissao permissaoNovo = permissaoRepository.save(permissao);
			return permissaoNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar permissao");
			exe.addErrorMessage("Permissao esta vazia ou nula");
			throw exe;
		}
		
		
	}
	
	public void update(Permissao permissao) throws BadResourceException, ResourceNotFoundException {
		if (!permissao.getDescricao().isEmpty()) {
			if (!existsById(permissao.getId())) {
				throw new ResourceNotFoundException("Permissao não encontrada com o id: " + permissao.getId());
			}
			permissao.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			permissaoRepository.save(permissao);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar permissao");
			exe.addErrorMessage("Permissao esta vazia ou nula");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Permissao não encontrada com o id: " + id);
		} else {
			permissaoRepository.deleteById(id);
		}
	
	}  public Long count() {
		return permissaoRepository.count();
	}
	
}
