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

import br.com.trabif.domain.Evento;
import br.com.trabif.repository.EventoRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventoRepositoryTests {

    @Mock
    private EventoRepository eventoRepository;

    @Test
    void findByNome() {
        Evento evento1 = new Evento();
        evento1.setNome("Evento 1");

        Evento evento2 = new Evento();
        evento2.setNome("Evento 2");
        Evento evento3 = new Evento();
        evento3.setNome("Evento 3");

        Page<Evento> page = new PageImpl<>(Arrays.asList(evento1, evento2, evento3));

        Mockito.when(eventoRepository.findByNome("Ev", Pageable.unpaged())).thenReturn(page);

        Page<Evento> result = eventoRepository.findByNome("Ev", Pageable.unpaged());

        assertEquals(3, result.getTotalElements());
        assertEquals("Evento 1", result.getContent().get(0).getNome());
    }
}