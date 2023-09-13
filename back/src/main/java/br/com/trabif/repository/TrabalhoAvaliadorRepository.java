package br.com.trabif.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.trabif.domain.PermissaoUsuario;
import br.com.trabif.domain.TrabalhoAvaliador;

public interface TrabalhoAvaliadorRepository extends JpaRepository<TrabalhoAvaliador, Long> {
	@Query(value = "select p from TrabalhoAvaliador p where p.trabalho.id=?1")
	Page<TrabalhoAvaliador> findByTrabalho(Long id, Pageable page);

	@Query(value = "select p from TrabalhoAvaliador p where p.usuario.id=?1")
	Page<TrabalhoAvaliador> findByAvaliador(Long id, Pageable page);

	@Query(value = "select p from TrabalhoAvaliador p where p.resultadoSubmissao.id=?1")
	Page<TrabalhoAvaliador> findByResultadoSubmissao(Long id, Pageable page);

	@Query(value = "select p from TrabalhoAvaliador p where p.trabalho.id = :idTrabalho and p.usuario.id = :idUsuario")
	List<TrabalhoAvaliador> findByTrabalhoAndUsuario(@Param("idTrabalho") Long idTrabalho, @Param("idUsuario") Long idUsuario);
}