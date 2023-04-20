package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.AreaAvaliador;

public interface AreaAvaliadorRepository extends JpaRepository<AreaAvaliador, Long> {
	@Query(value = "select p from AreaAvaliador p where p.area.id=?1")
	Page<AreaAvaliador> findByArea(Long id, Pageable page);

	@Query(value = "select p from AreaAvaliador p where p.avaliador.id=?1")
	Page<AreaAvaliador> findByAvaliador(Long id, Pageable page);
}