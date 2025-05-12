package com.uritec.prueba3.Service;

import com.uritec.prueba3.Model.Usuario;
import com.uritec.prueba3.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(String usuario, String contrasena) {
        return usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena).orElse(null);
    }
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent() ||
                usuarioRepository.findByRu(usuario.getRu()).isPresent()) {
            throw new RuntimeException("Usuario o RU ya existe");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> encontrarPorId(int id) {
        return usuarioRepository.findById(id);
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}