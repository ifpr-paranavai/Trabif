package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.ResultadoSubmissao;

public interface ResultadoSubmissaoRepository extends JpaRepository<ResultadoSubmissao, Long> {
	@Query(value = "select p from ResultadoSubmissao p where p.resultado=?1")
	Page<ResultadoSubmissao> findByResultado(int resultado, Pageable page);

	@Query(value = "select p from ResultadoSubmissao p where p.confianca=?1")
	Page<ResultadoSubmissao> findByConfianca(int confianca, Pageable page);
}