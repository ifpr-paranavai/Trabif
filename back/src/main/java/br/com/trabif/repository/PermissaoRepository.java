package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	@Query(value = "select p from Permissao p where p.descricao like %?1%")
	Page<Permissao> findByDescricao(String descricao, Pageable page);
}

