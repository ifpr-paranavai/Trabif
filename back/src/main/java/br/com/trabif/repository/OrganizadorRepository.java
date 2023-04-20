package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.Organizador;

public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {
	@Query(value = "select p from Organizador p where p.nome like %?1%")
	Page<Organizador> findByNome(String nome, Pageable page);
	
	@Query(value = "select p from Organizador p where p.cpf=?1")
	Organizador findByCpf(String cpf);
	
	@Query(value = "select p from Organizador p where p.email=?1")
	Organizador findByEmail(String email);
	
	Organizador findByEmailAndCodigoRecuperacaoSenha(String email, String codigoRecuperacaoSenha);
}

