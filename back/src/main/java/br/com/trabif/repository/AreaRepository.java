package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {
	@Query(value = "select p from Area p where p.descricao like %?1%")
	Page<Area> findByDescricao(String descricao, Pageable page);
}

