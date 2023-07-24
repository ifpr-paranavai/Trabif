package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.Migration;

public interface MigrationRepository extends JpaRepository<Migration, Long> {
    @Query(value = "select p from Migration p where p.nomeArquivo like %?1%")
	Page<Migration> findByDescricao(String descricao, Pageable page);
}

