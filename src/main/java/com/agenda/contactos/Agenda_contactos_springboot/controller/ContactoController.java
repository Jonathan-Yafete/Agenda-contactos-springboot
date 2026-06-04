package com.agenda.contactos.Agenda_contactos_springboot.controller;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import com.agenda.contactos.Agenda_contactos_springboot.repository.ContactoRespository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contactos") // Ruta exclusiva para no chocar con tus vistas web
@Tag(name = "API de Contactos", description = "Endpoints para el consumo y consulta de datos de la agenda en formato JSON")
public class ContactoController {

    private final ContactoRespository contactoRespository;

    public ContactoController(ContactoRespository contactoRespository) {
        this.contactoRespository = contactoRespository;
    }

    @Operation(summary = "Obtener lista completa de contactos", description = "Realiza una consulta a MySQL y devuelve todos los registros de la agenda en formato JSON.")
    @GetMapping
    public List<Contacto> obtenerTodosLosContactos() {
        return contactoRespository.findAll(); // Esto SÍ devolverá los datos de tu BD, no un texto plano
    }

    @GetMapping({"/"," "})
    public String verPaginaInicio(Model model){
        List<Contacto>contactos = contactoRespository.findAll();
        model.addAttribute("contactos",contactos);
        return "index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistrarContacto(Model modelo){
        modelo.addAttribute("contacto", new Contacto());
        return "nuevo";
    }

    @Operation(summary = "Crear un contacto nuevo",description = "Recibe un objeto JSON con los datos del contacto y lo" +
            " almacena de forma persistente en MySQL.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Devuelve un código de estado 201 Created
    public Contacto crearContacto(@Valid @RequestBody Contacto contacto) {
        return contactoRespository.save(contacto);
    }

    @PostMapping("/nuevo")
    public String guardarContacto(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirect
            , Model model){
        if (bindingResult.hasErrors()){
            return "nuevo";
        }
        contactoRespository.save(contacto);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido agregado con exito");
        return "redirect:/";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarContacto(@PathVariable Integer id, Model modelo){
        Contacto contacto = contactoRespository.getById(id);
        modelo.addAttribute("contacto", contacto);
        return "nuevo";
    }

    @Operation(summary = "Actualizar un contacto existente", description = "Busca un contacto por su ID y actualiza sus " +
            "campos (nombre, celular, email, fecha de nacimiento) en la base de datos.")
    @PutMapping("/{id}")
    public ResponseEntity<Contacto> actualizarContacto(@PathVariable Integer id, @Valid @RequestBody Contacto datosActualizados) {
        return contactoRespository.findById(id)
                .map(contactoDB -> {
                    contactoDB.setNombre(datosActualizados.getNombre());
                    contactoDB.setCelular(datosActualizados.getCelular());
                    contactoDB.setEmail(datosActualizados.getEmail());
                    contactoDB.setFechaNacimiento(datosActualizados.getFechaNacimiento());
                    Contacto guardado = contactoRespository.save(contactoDB);
                    return ResponseEntity.ok(guardado); // Retorna 200 OK con el contacto actualizado
                })
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 si el ID no existe
    }

    @PostMapping("/{id}/editar")
    public String actualizarContacto(@PathVariable Integer id,@Validated Contacto contacto, BindingResult bindingResult,
                                     RedirectAttributes redirect, Model model){
        Contacto  contactoDB = contactoRespository.getById(id);
        if (bindingResult.hasErrors()){
            model.addAttribute("contacto", contacto);
            return "nuevo";
        }
        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setCelular(contacto.getCelular());
        contactoDB.setEmail(contacto.getEmail());
        contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());
        contactoRespository.save(contactoDB);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido Actualizado Exitosamente");
        return "redirect:/";
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Integer id) {
        if (!contactoRespository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna 404 si el ID no existe
        }
        contactoRespository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content (Éxito sin cuerpo de respuesta)
    }

    @Operation(summary = "Eliminar un contacto por su ID", description = "Remueve de forma permanente un contacto de la " +
            "base de datos MySQL utilizando su identificador único.")



    @PostMapping("/{id}/eliminar")
    public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirect){
        contactoRespository.deleteById(id);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido Eliminado Correctamente");
        return "redirect:/";
    }



}
