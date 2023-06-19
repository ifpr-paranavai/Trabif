package br.com.trabif.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "trabalho_avaliador")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode(callSuper=true)
@Data
public class TrabalhoAvaliador extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@Schema(description = "Trabalho", example = "")
	@JoinColumn(name = "id_trabalho")
	private Trabalho trabalho;

	@ManyToOne
	@Schema(description = "Usuario", example = "")
	@JoinColumn(name = "id_usuario")
	private Usuario avaliador;

	@ManyToOne
	@Schema(description = "Resultado Submissao", example = "")
	@JoinColumn(name = "id_resultado_submissao")
	private ResultadoSubmissao resultadoSubmissao;
	
	public TrabalhoAvaliador() {}

}
