package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.Categoria;
import lombok.Data;

@Data
public class CategoriaDTO {
	private long id;
	private String descricao;
	
	public CategoriaDTO converter(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		BeanUtils.copyProperties(categoria, categoriaDTO);
		return categoriaDTO;
	}
	
	public Page<CategoriaDTO> converterListaCategoriaDTO(Page<Categoria> pageCategoria) {
		Page<CategoriaDTO> listaDTO = pageCategoria.map(this::converter);
		return listaDTO;
	}
	
}
