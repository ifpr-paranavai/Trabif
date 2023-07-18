package br.com.trabif.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.trabif.domain.Usuario;
import br.com.trabif.repository.UsuarioRepository;

@Service
public class UsuarioDetailService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if(usuario==null){
            throw new UsernameNotFoundException("Usuario não encontrada pelo email");
        }
        return usuario;
    }
    
}