package com.plazas.usuarios.infraestructure.output.jpa.adapter;

import com.plazas.usuarios.domain.model.Owner;
import com.plazas.usuarios.infraestructure.exception.OwnerAlreadyExistException;
import com.plazas.usuarios.infraestructure.output.jpa.entity.OwnerEntity;
import com.plazas.usuarios.infraestructure.output.jpa.mapper.OwnerEntityMapper;
import com.plazas.usuarios.infraestructure.output.jpa.repository.IOwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class OwnerJpaAdapterTest {

    @Mock
    private IOwnerRepository ownerRepository;

    @Mock
    private OwnerEntityMapper ownerEntityMapper;

    @InjectMocks
    private OwnerJpaAdapter ownerJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Save owner should fail with OwnerAlreadyExistException")
    void saveOwnerAlreadyExist() {

        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setName("Cristian");
        ownerEntity.setLastName("Botina");
        ownerEntity.setEmail("cristian@hotmail.com");
        ownerEntity.setId(1L);

        Mockito.when(ownerRepository.findByEmail("cristian@hotmail.com")).thenReturn(Optional.of(ownerEntity));

        OwnerEntity savedJpaEntity = new OwnerEntity();
        savedJpaEntity.setId(1L);
        savedJpaEntity.setEmail("cristian@hotmail.com");
        savedJpaEntity.setName("Cristian");

        when(ownerRepository.save(any())).thenReturn(any());

        LocalDate birthDate = LocalDate.of(1989,3,23);
        Owner owner = new Owner("Cristian", "Botina", 123456L, "3155828235",
                birthDate, "cristian@hotmail.com", "34567");


        OwnerAlreadyExistException exception = assertThrows(OwnerAlreadyExistException.class, () -> {
            ownerJpaAdapter.saveOwner(owner);
        });


        verify(ownerRepository, never()).save(savedJpaEntity);
        String messageValidation = "El propietario ya fue creado con el correo";
        assertTrue(exception.getMessage().contains(messageValidation));

    }
}