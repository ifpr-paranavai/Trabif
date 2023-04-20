package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.AreaTrabalho;

public interface AreaTrabalhoRepository extends JpaRepository<AreaTrabalho, Long> {
	@Query(value = "select p from AreaTrabalho p where p.area.id=?1")
	Page<AreaTrabalho> findByArea(Long id, Pageable page);

	@Query(value = "select p from AreaTrabalho p where p.trabalho.id=?1")
	Page<AreaTrabalho> findByTrabalho(Long id, Pageable page);
}