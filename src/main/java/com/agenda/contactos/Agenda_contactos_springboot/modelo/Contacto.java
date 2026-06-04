package com.agenda.contactos.Agenda_contactos_springboot.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Debe Ingresar su Nombre")
    private String nombre;

    @NotEmpty(message = "Debe Ingresar su Email")
    @Email(message = "El formato del correo es inválido")
    private String email;

    @NotBlank(message = "Debe Ingresar su Celular")
    private String celular;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @NotNull(message = "Debe Ingresar su Fecha de Nacimiento")
    private LocalDate fechaNacimiento;

    private LocalDateTime fechaRegistro;

    @PrePersist
    public void asignarFechaRegistro(){
        this.fechaRegistro = LocalDateTime.now();
    }
}