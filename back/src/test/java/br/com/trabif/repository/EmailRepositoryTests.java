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
import br.com.trabif.repository.EmailRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmailRepositoryTests {

    @Mock
    private EmailRepository emailRepository;

    @Test
    void findByTitulo() {
        // cria 3 objetos Email
        Email email1 = new Email();
        email1.setTitulo("Titulo1");

        Email email2 = new Email();
        email2.setTitulo("Titulo2");
        Email email3 = new Email();
        email3.setTitulo("Titulo3");

        // cria um objeto Page de Email com os 3 objetos Email
        Page<Email> page = new PageImpl<>(Arrays.asList(email1, email2, email3));

        // configura o mock do EmailRepository para retornar o objeto Page criado acima
        Mockito.when(emailRepository.findByTitulo("ti", Pageable.unpaged())).thenReturn(page);

        // chama o método findByTitulo com um título que deve encontrar apenas 1 objeto Email
        Page<Email> result = emailRepository.findByTitulo("ti", Pageable.unpaged());

        // verifica se o resultado contém apenas 1 objeto Email e se é o objeto esperado
        assertEquals(3, result.getTotalElements());
        assertEquals("Titulo1", result.getContent().get(0).getTitulo());
    }
}