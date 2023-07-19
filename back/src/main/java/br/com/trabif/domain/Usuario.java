package br.com.trabif.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode(callSuper=true)
@Data
public class Usuario extends Auditoria implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Schema(description = "Nome do usuário", example = "José da Silva")
	@NotBlank
	private String nome;
	
	@Schema(description = "CPF do usuário", example = "00.000.000-00")
	@Column(unique = true)
	private String cpf;
	
	@Schema(description = "E-mail do usuário, usado para acessar o sistema", example = "josesilva@gmail.com")
	@Column(unique = true)
	private String email;
	
	@Schema(description = "Código usado para recuperar senha do email informado")
	private String codigoRecuperacaoSenha;
	
	@Schema(description = "Senha do usuário, usada para acessar o sistema", example = "123456")
	private String senha;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de envio do código de recuperação")
	private Date dataEnvioCodigo;
	
	public Usuario() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {       
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
