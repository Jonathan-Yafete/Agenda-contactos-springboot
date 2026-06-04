package com.agenda.contactos.Agenda_contactos_springboot.controller;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import com.agenda.contactos.Agenda_contactos_springboot.service.ContactoService; // Dependemos de la interfaz
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contactos")
@Tag(name = "API de Contactos", description = "Endpoints optimizados bajo principios SOLID")
public class ContactoRestController {

    // REFACTOR SOLID: Acoplado a la interfaz, no al repositorio directamente
    private final ContactoService contactoService;

    public ContactoRestController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @Operation(summary = "Obtener lista completa de contactos en JSON")
    @GetMapping
    public List<Contacto> obtenerTodosLosContactos() {
        return contactoService.listarTodos();
    }

    @Operation(summary = "Crear un contacto nuevo vía JSON")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contacto crearContacto(@Valid @RequestBody Contacto contacto) {
        return contactoService.guardar(contacto);
    }

    @Operation(summary = "Actualizar un contacto existente por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Contacto> actualizarContacto(@PathVariable Integer id, @Valid @RequestBody Contacto datosActualizados) {
        return contactoService.buscarPorId(id)
                .map(contactoDB -> {
                    contactoDB.setNombre(datosActualizados.getNombre());
                    contactoDB.setCelular(datosActualizados.getCelular());
                    contactoDB.setEmail(datosActualizados.getEmail());
                    contactoDB.setFechaNacimiento(datosActualizados.getFechaNacimiento());
                    Contacto guardado = contactoService.guardar(contactoDB);
                    return ResponseEntity.ok(guardado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un contacto por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Integer id) {
        if (contactoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        contactoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}