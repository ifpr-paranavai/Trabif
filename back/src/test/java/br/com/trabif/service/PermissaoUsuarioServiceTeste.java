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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.trabif.domain.Evento;
import br.com.trabif.domain.Permissao;
import br.com.trabif.domain.PermissaoUsuario;
import br.com.trabif.domain.Usuario;
import br.com.trabif.dto.PermissaoUsuarioDTO;
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

    @Test
    void findPermissaoUsuarioByIdUsuarioAndIdEvento() throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("usuario");
        usuario.setEmail("usuario@gmail.com");

        Permissao permissao = new Permissao();
        permissao.setId(1);
        permissao.setDescricao("Autor");

        Evento evento = new Evento();
        evento.setId(1);
        evento.setNome("evento");

        PermissaoUsuario permissaoUsuario = new PermissaoUsuario();
        permissaoUsuario.setUsuario(usuario);
        permissaoUsuario.setPermissao(permissao);
        permissaoUsuario.setEvento(evento);

        List<PermissaoUsuario> listaResposta = new ArrayList<>();
        listaResposta.add(permissaoUsuario);

        Page<PermissaoUsuario> pageEntidade = new PageImpl<>(listaResposta);

        Page<PermissaoUsuarioDTO> pageResposta = new PermissaoUsuarioDTO().converterListaPermissaoUsuarioDTO(pageEntidade);

        Mockito.when(permissaoUsuarioService.findAllByIdUsuarioAndIdEvento(usuario.getId(), evento.getId(), Pageable.unpaged())).thenReturn(pageResposta);
        
        Page<PermissaoUsuarioDTO> resultado = permissaoUsuarioService.findAllByIdUsuarioAndIdEvento(usuario.getId(), evento.getId(), Pageable.unpaged());

        assertEquals(pageResposta, resultado);
    }

}
