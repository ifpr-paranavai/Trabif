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

import br.com.trabif.domain.Autor;
import br.com.trabif.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AutorRepositoryTests {

    @Mock
    private AutorRepository autorRepository;

    @Test
    void findByNome() {
        Autor autor1 = new Autor();
        autor1.setNome("Autor 1");

        Autor autor2 = new Autor();
        autor2.setNome("Autor 2");

        Page<Autor> page = new PageImpl<>(Arrays.asList(autor1, autor2));

        Mockito.when(autorRepository.findByNome("Au", Pageable.unpaged())).thenReturn(page);

        Page<Autor> result = autorRepository.findByNome("Au", Pageable.unpaged());

        assertEquals(2, result.getTotalElements());
        assertEquals("Autor 1", result.getContent().get(0).getNome());
    }
}