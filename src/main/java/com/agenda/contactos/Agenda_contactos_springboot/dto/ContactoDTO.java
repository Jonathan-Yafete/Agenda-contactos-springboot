package com.agenda.contactos.Agenda_contactos_springboot.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactoDTO {
    @NotBlank(message = "Debe Ingresar su Nombre")
    private String nombre;

    @NotEmpty(message = "Debe Ingresar su Email")
    @Email(message = "El formato del correo es inválido")
    private String email;

    @NotBlank(message = "Debe Ingresar su Celular")
    private String celular;

    @NotNull(message = "Debe Ingresar su Fecha de Nacimiento")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;
}
