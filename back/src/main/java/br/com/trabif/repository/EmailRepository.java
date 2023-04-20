package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
	@Query(value = "select p from Email p where p.titulo like %?1%")
	Page<Email> findByTitulo(String titulo, Pageable page);
}

