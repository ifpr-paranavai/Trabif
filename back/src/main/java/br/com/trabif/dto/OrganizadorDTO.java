package br.com.trabif.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import br.com.trabif.domain.Organizador;
import lombok.Data;

@Data
public class OrganizadorDTO {
	private long id;
	private String nome;
	private String cpf;
	private String email;
	private Date dataCadastro;
	private Date anoOrganizacao;
	
	public OrganizadorDTO converter(Organizador organizador) {
		OrganizadorDTO organizadorDTO = new OrganizadorDTO();
		BeanUtils.copyProperties(organizador, organizadorDTO);
		return organizadorDTO;
	}
	
	public Page<OrganizadorDTO> converterListaOrganizadorDTO(Page<Organizador> pageOrganizador) {
		Page<OrganizadorDTO> listaDTO = pageOrganizador.map(this::converter);
		return listaDTO;
	}
	
}
