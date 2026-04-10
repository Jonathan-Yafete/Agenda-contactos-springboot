package com.agenda.contactos.Agenda_contactos_springboot.controller;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import com.agenda.contactos.Agenda_contactos_springboot.repository.ContactoRespository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactoController {
    @Autowired
    private final ContactoRespository contactoRespository;

    public ContactoController(ContactoRespository contactoRespository) {
        this.contactoRespository = contactoRespository;
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

    @PostMapping("/{id}/eliminar")
    public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirect){
        contactoRespository.deleteById(id);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido Eliminado Correctamente");
        return "redirect:/";
    }



}
