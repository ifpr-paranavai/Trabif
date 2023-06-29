package br.com.trabif.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode(callSuper=true)
@Data
public class Email extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Schema(description = "Rementete do e-mail", example = "")
	@NotBlank
	private String remetente;

	@Schema(description = "Destinatário do e-mail", example = "")
	@NotBlank
	private String destinatario;

	@Schema(description = "Título do e-mail", example = "")
	@NotBlank
	private String titulo;

	@Schema(description = "Mensagem do e-mail", example = "")
	@NotBlank
	private String mensagem;
	
	public Email() {}

	public Email(String remetente, String destinatario, String titulo, String messagem) {
	}

}
