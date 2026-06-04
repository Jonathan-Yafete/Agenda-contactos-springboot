package com.agenda.contactos.Agenda_contactos_springboot.service;

import com.agenda.contactos.Agenda_contactos_springboot.dto.ContactoDTO;
import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto; // ¡Corregido con _ !
import com.agenda.contactos.Agenda_contactos_springboot.repository.ContactoRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    @Override
    public Contacto guardarDesdeDto(ContactoDTO dto) {
        // Convertimos los datos del DTO a la Entidad JPA real
        Contacto contactoEntity = new Contacto();
        contactoEntity.setNombre(dto.getNombre());
        contactoEntity.setCelular(dto.getCelular());
        contactoEntity.setEmail(dto.getEmail());
        contactoEntity.setFechaNacimiento(dto.getFechaNacimiento());

        // Guardamos de forma persistente en MySQL
        return contactoRespository.save(contactoEntity);
    }
}
