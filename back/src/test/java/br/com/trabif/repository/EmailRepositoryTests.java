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

import br.com.trabif.domain.Email;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmailRepositoryTests {

    @Mock
    private EmailRepository emailRepository;

    @Test
    void findByTitulo() {
        Email email1 = new Email();
        email1.setTitulo("Titulo1");

        Email email2 = new Email();
        email2.setTitulo("Titulo2");
        Email email3 = new Email();
        email3.setTitulo("Titulo3");

        Page<Email> page = new PageImpl<>(Arrays.asList(email1, email2, email3));

        Mockito.when(emailRepository.findByTitulo("ti", Pageable.unpaged())).thenReturn(page);

        Page<Email> result = emailRepository.findByTitulo("ti", Pageable.unpaged());

        assertEquals(3, result.getTotalElements());
        assertEquals("Titulo1", result.getContent().get(0).getTitulo());
    }
}