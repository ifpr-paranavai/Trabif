package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
	@Query(value = "select p from EmailTemplate p where p.titulo like %?1%")
	Page<EmailTemplate> findByTitulo(String titulo, Pageable page);
}

