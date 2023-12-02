package org.senai.ecommerce.service;

import org.senai.ecommerce.entity.Usuario;
import org.senai.ecommerce.entity.dto.NovoUsuario;
import org.senai.ecommerce.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario salvar(NovoUsuario novoUsuario){
        Usuario usuario = new Usuario();
        usuario.setNome(novoUsuario.getNome());
        usuario.setEmail(novoUsuario.getEmail());
        usuario.setPermissao(novoUsuario.getPermissao());
        String senhaCrypt = passwordEncoder.encode(novoUsuario.getSenha());
        usuario.setSenha(senhaCrypt);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> getUsuarioAutenticacao(String email){
        return usuarioRepository.findByEmail(email);
    }


}
