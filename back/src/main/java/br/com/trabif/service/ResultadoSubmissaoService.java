package br.com.trabif.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.ResultadoSubmissao;
import br.com.trabif.dto.ResultadoSubmissaoDTO;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.ResultadoSubmissaoRepository;

@Service
public class ResultadoSubmissaoService {
	
	@Autowired
	private ResultadoSubmissaoRepository resultadoSubmissaoRepository;
	
	private boolean existsById(Long id) {
		return resultadoSubmissaoRepository.existsById(id);
	}
	
	public ResultadoSubmissao findById(Long id) throws ResourceNotFoundException {
		ResultadoSubmissao resultadoSubmissao = resultadoSubmissaoRepository.findById(id).orElse(null);
		
		if(resultadoSubmissao == null) {
			throw new ResourceNotFoundException("ResultadoSubmissao não encontrado com o id: " + id);
		} else {
			return resultadoSubmissao;
		}
	}
	
	public Page<ResultadoSubmissaoDTO> findAll(Pageable pageable) {
		
		return new ResultadoSubmissaoDTO().converterListaResultadoSubmissaoDTO(resultadoSubmissaoRepository.findAll(pageable)) ;
	}
	
	public Page<ResultadoSubmissaoDTO> findAllByResultado(int resultado, Pageable page) {
		Page<ResultadoSubmissao> resultadoSubmissoes = resultadoSubmissaoRepository.findByResultado(resultado, page);
		return new ResultadoSubmissaoDTO().converterListaResultadoSubmissaoDTO(resultadoSubmissoes);
	}

	public Page<ResultadoSubmissaoDTO> findAllByConfianca(int confianca, Pageable page) {
		Page<ResultadoSubmissao> resultadoSubmissoes = resultadoSubmissaoRepository.findByConfianca(confianca, page);
		return new ResultadoSubmissaoDTO().converterListaResultadoSubmissaoDTO(resultadoSubmissoes);
	}
	
	public ResultadoSubmissao save(ResultadoSubmissao resultadoSubmissao) throws BadResourceException, ResourceAlreadyExistsException {
		if (resultadoSubmissao.getResultado() > 0 && resultadoSubmissao.getConfianca() > 0) {
			if(existsById(resultadoSubmissao.getId())) {
				throw new ResourceAlreadyExistsException("ResultadoSubmissao com id: " + resultadoSubmissao.getId() + " já existe.");
			}			
			resultadoSubmissao.setStatus('A');
			resultadoSubmissao.setDataCadastro(Calendar.getInstance().getTime());
			if(resultadoSubmissao.getResultado() < 1) {
				throw new IllegalArgumentException("O valor do resultado é menor que 1");
			}
			if(resultadoSubmissao.getResultado() > 5) {
				throw new IllegalArgumentException("O valor do resultado é maior que 5");
			}
			ResultadoSubmissao resultadoSubmissaoNovo = resultadoSubmissaoRepository.save(resultadoSubmissao);
			return resultadoSubmissaoNovo;
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar resultadoSubmissao");
			exe.addErrorMessage("ResultadoSubmissao esta vazio ou nulo");
			throw exe;
		}		
	}
	
	public void update(ResultadoSubmissao resultadoSubmissao) throws BadResourceException, ResourceNotFoundException {
		if (resultadoSubmissao.getResultado() > 0 && resultadoSubmissao.getConfianca() > 0) {
			if (!existsById(resultadoSubmissao.getId())) {
				throw new ResourceNotFoundException("ResultadoSubmissao não encontrado com o id: " + resultadoSubmissao.getId());
			}
			resultadoSubmissao.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			resultadoSubmissaoRepository.save(resultadoSubmissao);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar resultadoSubmissao");
			exe.addErrorMessage("ResultadoSubmissao esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("ResultadoSubmissao não encontrado com o id: " + id);
		} else {
			resultadoSubmissaoRepository.deleteById(id);
		}
	
	}  public Long count() {
		return resultadoSubmissaoRepository.count();
	}
	
}
