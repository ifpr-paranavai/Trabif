package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.EventoOrganizador;

public interface EventoOrganizadorRepository extends JpaRepository<EventoOrganizador, Long> {
	@Query(value = "select p from EventoOrganizador p where p.evento.id=?1")
	Page<EventoOrganizador> findByEvento(Long id, Pageable page);

	@Query(value = "select p from EventoOrganizador p where p.organizador.id=?1")
	Page<EventoOrganizador> findByOrganizador(Long id, Pageable page);
}