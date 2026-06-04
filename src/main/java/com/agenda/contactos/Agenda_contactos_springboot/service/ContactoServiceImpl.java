package com.agenda.contactos.Agenda_contactos_springboot.service;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import com.agenda.contactos.Agenda_contactos_springboot.repository.ContactoRespository;

import java.util.List;
import java.util.Optional;

public class ContactoServiceImpl implements  ContactoService{
    private final ContactoRespository contactoRespository;

    public ContactoServiceImpl(ContactoRespository contactoRespository){
        this.contactoRespository = contactoRespository;
    }
    @Override
    public List<Contacto> listarTodos() {
        return contactoRespository.findAll();
    }

    @Override
    public Contacto guardar(Contacto contacto) {
        return contactoRespository.save(contacto);
    }

    @Override
    public Optional<Contacto> buscarPorId(Integer id) {
        return contactoRespository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        contactoRespository.deleteById(id);

    }
}
