package com.agenda.contactos.Agenda_contactos_springboot.repository;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRespository extends JpaRepository<Contacto,Integer> {
}
