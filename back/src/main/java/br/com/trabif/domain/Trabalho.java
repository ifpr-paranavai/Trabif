package br.com.trabif.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "trabalho")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode(callSuper=true)
@Data
public class Trabalho extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Schema(description = "Título do trabalho", example = "A linguagem no turismo")
	@NotBlank
	private String titulo;

	@Schema(description = "Categoria do trabalho", example = "")
	@JoinColumn(name = "categoria")
	@NotBlank
	private Categoria categoria;

	@Schema(description = "Evento do trabalho", example = "")
	@JoinColumn(name = "evento")
	@NotBlank
	private Evento evento;

	@Schema(description = "Resultado final do trabalho", example = "Aprovado")
	@NotBlank
	private String resultado;
	
	public Trabalho() {}

}
