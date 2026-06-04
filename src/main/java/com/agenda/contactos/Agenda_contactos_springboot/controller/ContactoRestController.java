package com.agenda.contactos.Agenda_contactos_springboot.controller;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import com.agenda.contactos.Agenda_contactos_springboot.repository.ContactoRespository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Exclusivo para responder datos JSON a Swagger o aplicaciones móviles
@RequestMapping("/api/v1/contactos")
@Tag(name = "API de Contactos", description = "Endpoints para el consumo y consulta de datos de la agenda en formato JSON")
public class ContactoRestController {

    private final ContactoRespository contactoRespository;

    public ContactoRestController(ContactoRespository contactoRespository) {
        this.contactoRespository = contactoRespository;
    }

    @Operation(summary = "Obtener lista completa de contactos en JSON")
    @GetMapping
    public List<Contacto> obtenerTodosLosContactos() {
        return contactoRespository.findAll();
    }

    @Operation(summary = "Crear un contacto nuevo vía JSON")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contacto crearContacto(@Valid @RequestBody Contacto contacto) {
        return contactoRespository.save(contacto);
    }

    @Operation(summary = "Actualizar un contacto existente por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Contacto> actualizarContacto(@PathVariable Integer id, @Valid @RequestBody Contacto datosActualizados) {
        return contactoRespository.findById(id)
                .map(contactoDB -> {
                    contactoDB.setNombre(datosActualizados.getNombre());
                    contactoDB.setCelular(datosActualizados.getCelular());
                    contactoDB.setEmail(datosActualizados.getEmail());
                    contactoDB.setFechaNacimiento(datosActualizados.getFechaNacimiento());
                    Contacto guardado = contactoRespository.save(contactoDB);
                    return ResponseEntity.ok(guardado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un contacto por su ID de forma permanente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Integer id) {
        if (!contactoRespository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contactoRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
