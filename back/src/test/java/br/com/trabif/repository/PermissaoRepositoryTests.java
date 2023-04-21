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

import br.com.trabif.domain.Permissao;
import br.com.trabif.repository.PermissaoRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PermissaoRepositoryTests {

    @Mock
    private PermissaoRepository permissaoRepository;

    @Test
    void findByDescricao() {
        Permissao permissao1 = new Permissao();
        permissao1.setDescricao("Permissao 1");

        Permissao permissao2 = new Permissao();
        permissao2.setDescricao("Permissao 2");
        Permissao permissao3 = new Permissao();
        permissao3.setDescricao("Permissao 3");

        Page<Permissao> page = new PageImpl<>(Arrays.asList(permissao1, permissao2, permissao3));

        Mockito.when(permissaoRepository.findByDescricao("Pe", Pageable.unpaged())).thenReturn(page);

        Page<Permissao> result = permissaoRepository.findByDescricao("Pe", Pageable.unpaged());

        assertEquals(3, result.getTotalElements());
        assertEquals("Permissao 1", result.getContent().get(0).getDescricao());
    }
}