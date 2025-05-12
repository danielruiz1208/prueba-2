package com.uritec.prueba3.Repository;

import com.uritec.prueba3.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuarioAndContrasena(String usuario, String contrasena);
    Optional<Usuario> findByUsuario(String usuario);
    Optional<Usuario> findByRu(int ru);
}