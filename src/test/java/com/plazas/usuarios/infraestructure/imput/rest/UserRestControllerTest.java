package com.plazas.usuarios.infraestructure.imput.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.plazas.usuarios.application.dto.OwnerRequest;
import com.plazas.usuarios.application.handler.IOwnerHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserRestController.class
        , excludeAutoConfiguration = SecurityAutoConfiguration.class
)
//@SpringBootTest
//@ImportAutoConfiguration(exclude = {WebClientAutoConfiguration.class})
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IOwnerHandler ownerHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void Setup(){
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    //@Test
    @DisplayName("Save owner should response status ok")
    void testSaveOwner() throws Exception {

        OwnerRequest ownerRequest = new OwnerRequest();
        ownerRequest.setName("Cristian");
        ownerRequest.setLastName("Botina");
        ownerRequest.setPhoneNumber("+573155828235");
        ownerRequest.setBirthDate(LocalDate.of(1989,3,23));
        ownerRequest.setEmail("cristian_botina@hotmail.com");

        doNothing().when(ownerHandler).saveOwner(any());

        mockMvc.perform(post("/api/user/owner/")
                .contentType(MediaType.APPLICATION_JSON)
                .content((objectMapper.writeValueAsString(ownerRequest))))
                .andExpect(status().isCreated());

        verify(ownerHandler, times(1)).saveOwner(any(OwnerRequest.class));


    }

}