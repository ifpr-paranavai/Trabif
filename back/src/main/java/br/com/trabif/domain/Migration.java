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

@Entity
@Table(name = "migration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Migration implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Schema(description = "Nome do arquivo de migration", example = "Migration1")
	@NotBlank
	private String nomeArquivo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExecucao;
	
	public Migration() {}

}
