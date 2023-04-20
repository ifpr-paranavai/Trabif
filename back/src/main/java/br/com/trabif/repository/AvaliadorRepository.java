package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.Avaliador;

public interface AvaliadorRepository extends JpaRepository<Avaliador, Long> {
	@Query(value = "select p from Avaliador p where p.nome like %?1%")
	Page<Avaliador> findByNome(String nome, Pageable page);
	
	@Query(value = "select p from Avaliador p where p.cpf=?1")
	Avaliador findByCpf(String cpf);
	
	@Query(value = "select p from Avaliador p where p.email=?1")
	Avaliador findByEmail(String email);
	
	Avaliador findByEmailAndCodigoRecuperacaoSenha(String email, String codigoRecuperacaoSenha);
}

