package com.agenda.contactos.Agenda_contactos_springboot.controller;

import com.agenda.contactos.Agenda_contactos_springboot.modelo.Contacto;
import com.agenda.contactos.Agenda_contactos_springboot.service.ContactoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test; // JUnit 5 estándar
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactoRestController.class)
@ActiveProfiles("test")// Carga solo la capa web, ignorando la BD
class ContactoRestControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simula el navegador o Swagger haciendo peticiones HTTP

    @MockitoBean
    private ContactoService contactoService; // Mockito simula el comportamiento del servicio

    @Test
    @DisplayName("Debería retornar la lista de contactos en formato JSON mediante HTTP GET")
    void deberiaListarContactos() throws Exception {
        // 1. Arrange: Simulamos una respuesta ficticia del servicio
        Contacto c1 = new Contacto();
        c1.setNombre("Prueba SOLID");
        c1.setEmail("solid@test.com");

        Mockito.when(contactoService.listarTodos()).thenReturn(List.of(c1));

        // 2. Act & Assert: Hacemos la petición ficticia y verificamos el JSON
        mockMvc.perform(get("/api/v1/contactos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera un 200 OK
                .andExpect(jsonPath("$[0].nombre").value("Prueba SOLID")) // Verifica el contenido del JSON
                .andExpect(jsonPath("$[0].email").value("solid@test.com"));
    }
}