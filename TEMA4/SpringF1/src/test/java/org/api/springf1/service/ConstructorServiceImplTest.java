package org.api.springf1.service;

import org.api.springf1.dto.ConstructorDTO;
import org.api.springf1.model.Constructor;
import org.api.springf1.repository.ConstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConstructorServiceImplTest {

    @Mock
    ConstructorRepository constructorRepository;

    @InjectMocks
    ConstructorServiceImpl constructorService;

    Constructor constructor;
    ConstructorDTO constructorDTO;

    @BeforeEach
    void setUp() {
        constructor = Constructor.builder().id(1L).ref("ref").name("name").nationality("BR").build();
        constructorDTO = ConstructorDTO.builder().id(1L).ref("ref").name("name").build();
    }

    @Test
    void shouldReturnConstructorDTOWhenCreateConstructor(){
        when(constructorRepository.save(any(Constructor.class))).thenReturn(constructor);

        ConstructorDTO constructorDTOResponse = constructorService.saveConstructor(constructor);

        assertNotNull(constructorDTOResponse);
        assertEquals("ref", constructorDTOResponse.ref());

        verify(constructorRepository, times(1)).save(any(Constructor.class));
    }

    @Test
    void shouldReturnConstructorDTOWhenFindConstructorByRef(){
        when(constructorRepository.findByRefIgnoreCase(any())).thenReturn(Optional.of(constructor));

        ConstructorDTO constructorDTOResponse = constructorService.getConstructorByRef("ref");

        assertNotNull(constructorDTOResponse);
        assertEquals("ref", constructorDTOResponse.ref());
    }

    @Test
    void shouldReturnConstructorDTOWhenUpdateConstructor(){
        when(constructorRepository.findByRefIgnoreCase(any())).thenReturn(Optional.of(constructor));
        when(constructorRepository.save(any(Constructor.class))).thenReturn(constructor);

        ConstructorDTO constructorDTOResponse = constructorService.updateConstructor(constructor);

        assertNotNull(constructorDTOResponse);
        assertEquals("ref", constructorDTOResponse.ref());
    }

    @Test
    void shouldReturnConstructorDTOWhenDeleteConstructorByRef(){
        when(constructorRepository.findByRefIgnoreCase(any())).thenReturn(Optional.of(constructor));

        constructorService.deleteConstructorByRef("ref");

        verify(constructorRepository, times(1)).delete(any(Constructor.class));
    }

    @Test
    void shouldReturnConstructorDTOListWhenGetConstructors(){
        when(constructorRepository.findAll()).thenReturn(List.of(constructor));

        var constructorDTOResponse = constructorService.getConstructors();

        assertNotNull(constructorDTOResponse);
        assertEquals(1, constructorDTOResponse.size());
    }

}