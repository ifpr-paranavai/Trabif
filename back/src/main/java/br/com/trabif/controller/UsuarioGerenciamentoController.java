package br.com.trabif.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trabif.domain.Usuario;
import br.com.trabif.security.JwtUtil;
import br.com.trabif.service.UsuarioGerenciamentoSerivce;


@RestController
@RequestMapping("/api/usuario-gerenciamento")
@CrossOrigin
public class UsuarioGerenciamentoController {
    
    @Autowired
    private UsuarioGerenciamentoSerivce usuarioGerenciamentoService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/senha-codigo")
    @CrossOrigin("http://localhost:4200")
    public String recuperarCodigo(@RequestBody Usuario usuario){
       return usuarioGerenciamentoService.solicitarCodigo(usuario.getEmail());
    }

    @PostMapping("/senha-alterar")
    @CrossOrigin("http://localhost:4200")
    public String alterarSenha(@RequestBody Usuario usuario){
       return usuarioGerenciamentoService.alterarSenha(usuario);
    }

    @PostMapping("/login")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<?> login(@RequestBody Usuario usuario){
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      Usuario autenticado = (Usuario) authentication.getPrincipal();
      String token = jwtUtil.gerarTokenUsername(autenticado);
      HashMap<String, Object> map = new HashMap<>();
      map.put("token", token);
      map.put("usuario", usuario);
      return ResponseEntity.ok(map);

    }



}