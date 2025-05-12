package com.uritec.prueba3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @NotBlank(message = "El usuario no puede estar vacío")
    @Size(min = 3, max = 255, message = "El usuario debe tener entre 3 y 255 caracteres")
    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 4, max = 255, message = "La contraseña debe tener entre 4 y 255 caracteres")
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol;

    @Column(name = "ru", nullable = false)
    private int ru;

    public enum Rol {
        Administrador, Estudiante, Docente
    }
}