package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.EventoEmailTemplate;

public interface EventoEmailTemplateRepository extends JpaRepository<EventoEmailTemplate, Long> {
	@Query(value = "select p from EventoEmailTemplate p where p.evento.id=?1")
	Page<EventoEmailTemplate> findByEvento(Long id, Pageable page);

	@Query(value = "select p from EventoEmailTemplate p where p.emailTemplate.id=?1")
	Page<EventoEmailTemplate> findByEmailTemplate(Long id, Pageable page);
}