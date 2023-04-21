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

import br.com.trabif.domain.Avaliador;
import br.com.trabif.repository.AvaliadorRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvaliadorRepositoryTests {

    @Mock
    private AvaliadorRepository avaliadorRepository;

    @Test
    void findByNome() {
        Avaliador avaliador1 = new Avaliador();
        avaliador1.setNome("Avaliador 1");

        Avaliador avaliador2 = new Avaliador();
        avaliador2.setNome("Avaliador 2");
        Avaliador avaliador3 = new Avaliador();
        avaliador3.setNome("Avaliador 3");

        Page<Avaliador> page = new PageImpl<>(Arrays.asList(avaliador1, avaliador2, avaliador3));

        Mockito.when(avaliadorRepository.findByNome("Ava", Pageable.unpaged())).thenReturn(page);

        Page<Avaliador> result = avaliadorRepository.findByNome("Ava", Pageable.unpaged());

        assertEquals(3, result.getTotalElements());
        assertEquals("Avaliador 1", result.getContent().get(0).getNome());
    }
}