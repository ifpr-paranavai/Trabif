package br.com.trabif.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.trabif.domain.PermissaoUsuario;
import br.com.trabif.exception.BadResourceException;
import br.com.trabif.exception.ResourceAlreadyExistsException;
import br.com.trabif.exception.ResourceNotFoundException;
import br.com.trabif.repository.PermissaoUsuarioRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PermissaoUsuarioServiceTeste {
    

    @Mock
    private PermissaoUsuarioService permissaoUsuarioService;

    @Mock
    private PermissaoUsuarioRepository permissaoUsuarioRepository;
    
    @Mock
    private EmailService emailService;

    @Mock
    private UsuarioService usuarioService;

    @Test
    void saveAutor() throws ResourceNotFoundException, BadResourceException, ResourceAlreadyExistsException {
        PermissaoUsuario result = permissaoUsuarioService.saveAutor(new PermissaoUsuario());

        assertEquals(null, result);
    }

    @Test
    void saveOrganizador() throws ResourceNotFoundException, BadResourceException, ResourceAlreadyExistsException {
        PermissaoUsuario result = permissaoUsuarioService.saveOrganizador(new PermissaoUsuario());
        assertEquals(null, result);
    }

    @Test
    void saveAvaliador() throws ResourceNotFoundException, BadResourceException, ResourceAlreadyExistsException {
        PermissaoUsuario result = permissaoUsuarioService.saveAvaliador(new PermissaoUsuario());
        assertEquals(null, result);
    }

}
