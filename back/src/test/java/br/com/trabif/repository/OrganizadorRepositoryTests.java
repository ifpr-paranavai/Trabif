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

import br.com.trabif.domain.Organizador;
import br.com.trabif.repository.OrganizadorRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrganizadorRepositoryTests {

    @Mock
    private OrganizadorRepository organizadorRepository;

    @Test
    void findByNome() {
        Organizador organizador1 = new Organizador();
        organizador1.setNome("Organizador 1");

        Organizador organizador2 = new Organizador();
        organizador2.setNome("Organizador 2");
        Organizador organizador3 = new Organizador();
        organizador3.setNome("Organizador 3");

        Page<Organizador> page = new PageImpl<>(Arrays.asList(organizador1, organizador2, organizador3));

        Mockito.when(organizadorRepository.findByNome("Or", Pageable.unpaged())).thenReturn(page);

        Page<Organizador> result = organizadorRepository.findByNome("Or", Pageable.unpaged());

        assertEquals(3, result.getTotalElements());
        assertEquals("Organizador 1", result.getContent().get(0).getNome());
    }
}