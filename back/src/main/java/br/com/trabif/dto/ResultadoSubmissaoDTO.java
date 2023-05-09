package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.ResultadoSubmissao;
import lombok.Data;

@Data
public class ResultadoSubmissaoDTO extends AuditoriaDTO {
	private long id;
	private int resultado;
	private int confianca;
	private String comentarioAutor;
	private String comentarioOrganizador;
	
	public ResultadoSubmissaoDTO converter(ResultadoSubmissao resultadoSubmissao) {
		ResultadoSubmissaoDTO resultadoSubmissaoDTO = new ResultadoSubmissaoDTO();
		BeanUtils.copyProperties(resultadoSubmissao, resultadoSubmissaoDTO);
		return resultadoSubmissaoDTO;
	}
	
	public Page<ResultadoSubmissaoDTO> converterListaResultadoSubmissaoDTO(Page<ResultadoSubmissao> pageResultadoSubmissao) {
		Page<ResultadoSubmissaoDTO> listaDTO = pageResultadoSubmissao.map(this::converter);
		return listaDTO;
	}
	
}
