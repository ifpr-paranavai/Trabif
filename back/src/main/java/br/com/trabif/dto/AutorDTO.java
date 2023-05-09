package br.com.trabif.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.Autor;
import lombok.Data;

@Data
public class AutorDTO extends AuditoriaDTO {
	private long id;
	private String nome;
	private String cpf;
	private String email;
	
	public AutorDTO converter(Autor autor) {
		AutorDTO autorDTO = new AutorDTO();
		BeanUtils.copyProperties(autor, autorDTO);
		return autorDTO;
	}
	
	public Page<AutorDTO> converterListaAutorDTO(Page<Autor> pageAutor) {
		Page<AutorDTO> listaDTO = pageAutor.map(this::converter);
		return listaDTO;
	}
	
}
