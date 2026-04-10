package com.agenda.contactos.Agenda_contactos_springboot;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactoTest {
    @Test
    void testContacto(){
        Contacto contacto = new Contacto();
        contacto.setNombre("Jonathan");

        assertEquals("Jonathan", contacto.getNombre());
        assertNotNull(contacto);
    }
}
