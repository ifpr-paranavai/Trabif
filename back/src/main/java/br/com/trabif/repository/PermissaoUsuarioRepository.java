package br.com.trabif.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.trabif.domain.PermissaoUsuario;

public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario, Long> {
	@Query(value = "select p from PermissaoUsuario p where p.permissao.id=?1")
	Page<PermissaoUsuario> findByPermissao(Long id, Pageable page);

	@Query(value = "select p from PermissaoUsuario p where p.usuario.id=?1")
	Page<PermissaoUsuario> findByUsuario(Long id, Pageable page);
}