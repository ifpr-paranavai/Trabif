package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	@Query(value = "select p from Categoria p where p.descricao like %?1%")
	Page<Categoria> findByDescricao(String descricao, Pageable page);
}

