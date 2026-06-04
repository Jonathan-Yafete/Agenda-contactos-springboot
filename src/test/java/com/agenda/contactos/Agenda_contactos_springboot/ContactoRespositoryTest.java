package com.agenda.contactos.Agenda_contactos_springboot;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import com.agenda.contactos.Agenda_contactos_springboot.repository.ContactoRespository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test; // 2. Corregido: era api.Test, no ap1.test
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
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

    @Test
    @DisplayName("Debería encontrar un contacto correctamente al buscar por su Email")
    void deberiaBuscarContactoPorEmail() {
        // 1. Arrange: Guardamos un contacto con un email único en la base de datos
        Contacto contactoPrueba = new Contacto();
        contactoPrueba.setNombre("Beatriz Ortiz");
        contactoPrueba.setCelular("5559876543");
        contactoPrueba.setEmail("beatriz.test@example.com"); // El email que buscaremos
        contactoPrueba.setFechaNacimiento(LocalDate.of(1993, 10, 12));

        contactoRespository.save(contactoPrueba);

        // 2. Act: Ejecutamos la búsqueda en el repositorio usando el email
        Optional<Contacto> contactoEncontrado = contactoRespository.findByEmail("beatriz.test@example.com");

        // 3. Assert: Verificamos con JUnit 5 que el contacto realmente exista y sus datos sean correctos
        assertThat(contactoEncontrado).isPresent(); // Comprueba que el Optional no esté vacío
        assertThat(contactoEncontrado.get().getNombre()).isEqualTo("Beatriz Ortiz");
        assertThat(contactoEncontrado.get().getEmail()).isEqualTo("beatriz.test@example.com");
    }
}
