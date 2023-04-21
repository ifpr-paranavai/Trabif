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

import br.com.trabif.domain.Categoria;
import br.com.trabif.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoriaRepositoryTests {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    void findByDescricao() {
        Categoria categoria1 = new Categoria();
        categoria1.setDescricao("Categoria 1");

        Categoria categoria2 = new Categoria();
        categoria2.setDescricao("Categoria 2");
        Categoria categoria3 = new Categoria();
        categoria3.setDescricao("Categoria 3");

        Page<Categoria> page = new PageImpl<>(Arrays.asList(categoria1, categoria2, categoria3));

        Mockito.when(categoriaRepository.findByDescricao("Ca", Pageable.unpaged())).thenReturn(page);

        Page<Categoria> result = categoriaRepository.findByDescricao("Ca", Pageable.unpaged());

        assertEquals(3, result.getTotalElements());
        assertEquals("Categoria 1", result.getContent().get(0).getDescricao());
    }
}