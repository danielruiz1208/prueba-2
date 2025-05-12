package com.uritec.prueba3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @Column(name = "ru")
    private int ru;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido paterno no puede estar vacío")
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno no puede estar vacío")
    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe ser un correo válido")
    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "paralelo")
    private String paralelo;
}
