package com.cursosdedesarrollo.backendauth.controllers;

import com.cursosdedesarrollo.backendauth.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("Aceptacion")
public class UserControllerTests {

    @Autowired
    private UsersController usersController;

    @Autowired
    private MockMvc mockMvc;

    private final static String baseURL = "/api/v1/users/";

    /*
        Pruebas del contexto de pruebas
     */
    @Test
    public void pruebaContexto(){
        assertThat(usersController).isNotNull();
    }

    // Realizar las pruebas del API Rest Como Cliente del API
    // Pruebas de Aceptación
    // Listado
    @Test
    public void indexShouldReturnSomething() throws Exception {
        // Realiza una petición al servidor
        this.mockMvc
                // A la misma ruta del controlador para el listado
                //  "/api/v1/users/"
                .perform(get(baseURL))
                // imprime el resultado por pantalla
                .andDo(print())
                // Comprobación (Assert)
                .andExpect(
                        // Devuelve como status code 200 OK
                        status().isOk()
                );
    }

    /*
        Objeto que permite transferir de Objeto a JSON
     */
    @Autowired
    private ObjectMapper mapper;
    // Añadir Usuario
    @Test
    public void createUserShouldReturnOk() throws Exception {
        String username = "pepesan";
        String password = "12345678";
        String email = "p@p.com";
        Boolean active = false;
        // Crear el objeto del usuario que queremos añadir
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setActive(active);

        // Definir el objeto que lanza la petición
        final ResultActions result =
                this.mockMvc.perform(
                        // Realizar una petición Post
                        post(baseURL)
                                // pasar como Body el objeto user
                                .content(mapper.writeValueAsBytes(user))
                                // definir el del Content Type a application/json
                                .contentType(MediaType.APPLICATION_JSON_VALUE));
        // imprime por pantalla el resultado
        result.andDo(print())
                // comprueba que el status code es 200 Ok
                .andExpect(status().isOk())
                // Comprobar los datos que nos devuelve
                .andExpect(jsonPath("username", Matchers.is(username)))
                .andExpect(jsonPath("password", Matchers.is(password)))
                .andExpect(jsonPath("email", Matchers.is(email)))
                .andExpect(jsonPath("active", Matchers.is(active)))
                // .andExpect(jsonPath("type", Matchers.is(null)))
                // .andExpect(jsonPath("activationToken", Matchers.is(null)))
        ;
    }


    // Obtener Usuario
    @Test
    public void testGetUserFromId() throws Exception{
        final ResultActions result =
                this.mockMvc
                        // Realizar la petición
                        .perform(get(baseURL+"1"))
                        // devuelve un código 200 OK
                        .andExpect(status().isOk());
        result.andDo(print());
        // comprobamos que nos devuelve un dato concreto
        result
                // comprobamos el nombre de usuario
                .andExpect(jsonPath("id", Matchers.is(1)))
                // comprobamos el nombre de usuario
                .andExpect(jsonPath("username", Matchers.is("administrador")))
                // comprobamos la password
                .andExpect(jsonPath("password", Matchers.is("administrador")))
                // comprobamos el email
                .andExpect(jsonPath("email", Matchers.is("admin@example.com")))
        ;
    }

    // Modificar Usuario

    @Test
    public void testUpdateUser() throws Exception{
        Integer id = 52;
        String username = "pepesan27";
        String password = "pepesan27XD";
        String email = "p2@p.com";
        Boolean active = true;
        String type = "admin";
        String activationToken = "TOKEN";
        User user = new User();
        user.setId(52L);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setActive(active);
        user.setType(type);
        user.setActivationToken(activationToken);
        final ResultActions result =
                this.mockMvc.perform(
                        put(baseURL+"52")
                                .content(mapper.writeValueAsBytes(user))
                                .contentType(MediaType.APPLICATION_JSON_VALUE));
        result.andDo(print()).andExpect(status().is2xxSuccessful());
        result.andDo(print()).andExpect(jsonPath("id", Matchers.is(id)));
        result.andDo(print()).andExpect(jsonPath("username", Matchers.is(username)));
        result.andDo(print()).andExpect(jsonPath("password", Matchers.is(password)));
        result.andDo(print()).andExpect(jsonPath("email", Matchers.is(email)));
        result.andDo(print()).andExpect(jsonPath("active", Matchers.is(active)));
        result.andDo(print()).andExpect(jsonPath("type", Matchers.is(type)));
        result.andDo(print()).andExpect(jsonPath("activationToken", Matchers.is(activationToken)));
        //result.andExpect(jsonPath("_embedded.pokemons[0].name", is("Bulbasaur")));

    }
    // Borrar Usuario
    @Test
    public void testDeleteUserFromId() throws Exception{
        final ResultActions result =
                this.mockMvc
                        .perform(delete(baseURL+"52"))
                .andExpect(status().is2xxSuccessful());
        result.andDo(print());
        //result.andExpect(jsonPath("name", Matchers.is("Bulbasaur")));
    }
}
