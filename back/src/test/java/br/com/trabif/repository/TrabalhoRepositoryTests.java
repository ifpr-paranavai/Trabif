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

import br.com.trabif.domain.Trabalho;
import br.com.trabif.repository.TrabalhoRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrabalhoRepositoryTests {

    @Mock
    private TrabalhoRepository trabalhoRepository;

    @Test
    void findByTitulo() {
        // cria 3 objetos Trabalho
        Trabalho trabalho1 = new Trabalho();
        trabalho1.setTitulo("Titulo1");

        Trabalho trabalho2 = new Trabalho();
        trabalho2.setTitulo("Titulo2");
        Trabalho trabalho3 = new Trabalho();
        trabalho3.setTitulo("Titulo3");

        Page<Trabalho> page = new PageImpl<>(Arrays.asList(trabalho1, trabalho2, trabalho3));

        Mockito.when(trabalhoRepository.findByTitulo("ti", Pageable.unpaged())).thenReturn(page);

        Page<Trabalho> result = trabalhoRepository.findByTitulo("ti", Pageable.unpaged());

        assertEquals(3, result.getTotalElements());
        assertEquals("Titulo1", result.getContent().get(0).getTitulo());
    }
}