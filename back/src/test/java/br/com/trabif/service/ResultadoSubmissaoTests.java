package br.com.trabif.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.trabif.domain.ResultadoSubmissao;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResultadoSubmissaoTests {

    @Mock
    private ResultadoSubmissaoService resultadoSubmissaoService;


    @Test
    void saveValidGrade() {
        ResultadoSubmissao resultadoSubmissao = new ResultadoSubmissao();
        resultadoSubmissao.setComentarioAutor("Trabalho bem feito");
        resultadoSubmissao.setComentarioOrganizador("Nada a comentar o autor foi pontual em suas afirmações");
        resultadoSubmissao.setConfianca(5);
        resultadoSubmissao.setResultado(5);
        

        try {
            Mockito.when(resultadoSubmissaoService.save(resultadoSubmissao)).thenReturn(resultadoSubmissao);
            ResultadoSubmissao resultado = resultadoSubmissaoService.save(resultadoSubmissao);
    
            assertEquals(resultadoSubmissao, resultado);
        } catch (BadResourceException | ResourceAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}