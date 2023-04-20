package br.com.trabif.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "avaliador")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode(callSuper=true)
@Data
public class Avaliador extends Usuario {

	private static final long serialVersionUID = 1L;	

	public Avaliador() {}
}
