package br.com.trabif.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.trabif.domain.Usuario;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuarioRepositoryTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void findByNome() {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Usuario 1");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Usuario 2");

        Page<Usuario> page = new PageImpl<>(Arrays.asList(usuario1, usuario2));

        Mockito.when(usuarioRepository.findByNome("Us", Pageable.unpaged())).thenReturn(page);

        Page<Usuario> result = usuarioRepository.findByNome("Us", Pageable.unpaged());

        assertEquals(2, result.getTotalElements());
        assertEquals("Usuario 1", result.getContent().get(0).getNome());
    }
}