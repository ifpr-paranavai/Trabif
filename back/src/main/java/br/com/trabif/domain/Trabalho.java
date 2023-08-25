package br.com.trabif.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private String titulo;

	@ManyToOne
	@Schema(description = "Categoria do trabalho", example = "")
	@JoinColumn(name = "categoria")
	@NotNull
	private Categoria categoria;

	@ManyToOne
	@Schema(description = "Evento do trabalho", example = "")
	@JoinColumn(name = "evento")
	@NotNull
	private Evento evento;

	@Schema(description = "Resultado final do trabalho", example = "Aprovado")
	private String resultado;

	@Lob
	@Column(name = "pdf")
	private byte[] pdf;
	
	public Trabalho() {}

}
