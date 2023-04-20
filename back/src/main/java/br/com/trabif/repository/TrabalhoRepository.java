package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.Trabalho;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
	@Query(value = "select p from Trabalho p where p.titulo like %?1%")
	Page<Trabalho> findByTitulo(String titulo, Pageable page);

	@Query(value = "select p from Trabalho p where p.categoria.id=?1")
	Page<Trabalho> findByCategoria(Long id, Pageable page);
}

