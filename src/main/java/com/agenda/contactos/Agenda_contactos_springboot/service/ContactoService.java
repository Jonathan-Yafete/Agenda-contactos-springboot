package com.agenda.contactos.Agenda_contactos_springboot.service;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;

import java.util.List;
import java.util.Optional;

public interface ContactoService {
    List<Contacto> listarTodos();
    Contacto guardar(Contacto contacto);
    Optional<Contacto> buscarPorId(Integer id);
    void eliminar(Integer id);
}
