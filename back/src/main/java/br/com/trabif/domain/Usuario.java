package br.com.trabif.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode(callSuper=true)
@Data
public class Usuario extends Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Schema(description = "Nome do usuário", example = "José da Silva")
	@NotBlank
	private String nome;
	
	@Schema(description = "CPF do usuário", example = "00.000.000-00")
	private String cpf;
	
	@Schema(description = "E-mail do usuário, usado para acessar o sistema", example = "josesilva@gmail.com")
	private String email;
	
	@Schema(description = "Código usado para recuperar senha do email informado")
	private String codigoRecuperacaoSenha;
	
	@Schema(description = "Senha do usuário, usada para acessar o sistema", example = "123456")
	private String senha;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de envio do código de recuperação")
	private Date dataEnvioCodigo;
	
	public Usuario() {}

}
