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
@NoArgsConstructor @AllArgsConstructor
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Debe Ingresar su Nombre")
    private String nombre;
    @NotEmpty(message = "Debe Ingresar su Email")
    @Email
    private String email;
    @NotBlank(message = "Debe Ingresar su Celular")
    private String celular;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull(message = "Debe Ingresa su Fecha de Nacimiento")
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegitro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @PrePersist
    public void asignarFechaRegistro(){
        fechaRegistro = LocalDateTime.now();
    }
}
