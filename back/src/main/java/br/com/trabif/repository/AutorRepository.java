package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	@Query(value = "select p from Autor p where p.nome like %?1%")
	Page<Autor> findByNome(String nome, Pageable page);
	
	@Query(value = "select p from Autor p where p.cpf=?1")
	Autor findByCpf(String cpf);
	
	@Query(value = "select p from Autor p where p.email=?1")
	Autor findByEmail(String email);
}

