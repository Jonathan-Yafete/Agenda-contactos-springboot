package com.agenda.contactos.Agenda_contactos_springboot;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import com.agenda.contactos.Agenda_contactos_springboot.repository.ContactoRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.AutoConfigureDataJpa;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContactoRespositoryTest {
    @Autowired
    private ContactoRespository contactoRespository;

    @Test
    void testGuardarContacto(){
        //Preparamos el Dato
        Contacto contacto = new Contacto();
        contacto.setNombre("Prueba Unitaria");
        contacto.setEmail("test@gmail.com");
        contacto.setCelular("1234567891");
        contacto.setFechaNacimiento(LocalDate.of(2000, 1, 1));

        //Ejecutamos la accion
        Contacto guardado = contactoRespository.save(contacto);

        //Verificamos (Assertions)
        assertThat(guardado).isNotNull();
        assertThat(guardado.getId()).isGreaterThan(0);
        assertThat(guardado.getNombre()).isEqualTo("Prueba Unitaria");
    }
}
