package com.agenda.contactos.Agenda_contactos_springboot.repository;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactoRespository extends JpaRepository<Contacto,Integer> {
    // Este método busca un contacto usando el campo 'email' de tu entidad
    Optional<Contacto> findByEmail(String email);
}
