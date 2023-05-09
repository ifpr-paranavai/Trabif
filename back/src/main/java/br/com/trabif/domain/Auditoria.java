package br.com.trabif.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditoria {
    @CreatedBy
    @Schema(description = "Usuário que realizou o cadastro")
    @Column(name = "usuario_cadastro", updatable = false)
    private String usuarioCadastro;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de cadstro")
    @Column(name = "data_cadastro", updatable = false)
    private Date dataCadastro;

    @LastModifiedBy
    @Schema(description = "Usuario que realizou a ultima alteração")
    @Column(name = "usuario_ultima_alteracao")
    private String usuarioUltimaAlteracao;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data da ultima alteração")
    @Column(name = "data_ultima_alteracao")
    private Date dataUltimaAlteracao;

    @Value("A")
    @Schema(description = "Status do usuário", example = "A")
	private char status;
}
