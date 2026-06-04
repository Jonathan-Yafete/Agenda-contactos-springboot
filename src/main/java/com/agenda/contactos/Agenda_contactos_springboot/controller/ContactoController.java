package com.agenda.contactos.Agenda_contactos_springboot.controller;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import com.agenda.contactos.Agenda_contactos_springboot.service.ContactoService; // Inyectamos la interfaz
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class ContactoController {

    // REFACTOR SOLID: Dependemos únicamente de la abstracción del servicio
    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping("/")
    public String verPaginaInicio(Model model){
        List<Contacto> contactos = contactoService.listarTodos(); // Cambiado a método del servicio
        model.addAttribute("contactos", contactos);
        return "index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistrarContacto(Model modelo){
        modelo.addAttribute("contacto", new Contacto());
        return "nuevo";
    }

    @PostMapping("/nuevo")
    public String guardarContacto(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirect, Model model){
        if (bindingResult.hasErrors()){
            return "nuevo";
        }
        contactoService.guardar(contacto); // Cambiado a método del servicio
        redirect.addFlashAttribute("msgExito", "El contacto ha sido agregado con éxito");
        return "redirect:/";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarContacto(@PathVariable Integer id, Model modelo){
        // Manejamos el Optional de forma segura si no se encuentra el ID
        Contacto contacto = contactoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de contacto inválido: " + id));
        modelo.addAttribute("contacto", contacto);
        return "nuevo";
    }

    @PostMapping("/{id}/editar")
    public String actualizarContacto(@PathVariable Integer id, @Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirect, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("contacto", contacto);
            return "nuevo";
        }

        contactoService.buscarPorId(id).ifPresent(contactoDB -> {
            contactoDB.setNombre(contacto.getNombre());
            contactoDB.setCelular(contacto.getCelular());
            contactoDB.setEmail(contacto.getEmail());
            contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());
            contactoService.guardar(contactoDB); // Cambiado a método del servicio
        });

        redirect.addFlashAttribute("msgExito", "El contacto ha sido Actualizado Exitosamente");
        return "redirect:/";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirect){
        contactoService.eliminar(id); // Cambiado a método del servicio
        redirect.addFlashAttribute("msgExito", "El contacto ha sido Eliminado Correctamente");
        return "redirect:/";
    }
}