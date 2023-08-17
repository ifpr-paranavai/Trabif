package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.trabif.domain.AutorTrabalho;

public interface AutorTrabalhoRepository extends JpaRepository<AutorTrabalho, Long> {
	@Query(value = "select p from AutorTrabalho p where p.usuario.id=?1")
	Page<AutorTrabalho> findByAutor(Long id, Pageable page);

	@Query(value = "select p from AutorTrabalho p where p.trabalho.id=?1")
	Page<AutorTrabalho> findByTrabalho(Long id, Pageable page);

	@Query(value = "select p from AutorTrabalho p where p.usuario.id = :idUsuario and p.trabalho.evento.id = :idEvento")
	Page<AutorTrabalho> findByUsuarioAndEvento(@Param("idUsuario") Long idUsuario, @Param("idEvento") Long idEvento, Pageable page);
}