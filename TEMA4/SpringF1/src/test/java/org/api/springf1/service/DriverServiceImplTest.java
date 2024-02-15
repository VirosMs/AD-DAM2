package org.api.springf1.service;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.model.Driver;
import org.api.springf1.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

    @Mock
    DriverRepository driverRepository;

    @InjectMocks
    DriverServiceImpl driverService;

    Driver driver;
    DriverDTO driverDTO;

    @BeforeEach
    void setUp() {
        driver = Driver.builder().id(1L).code("HAM").forename("Lewis").surname("Hamilton").build();
        driverDTO = DriverDTO.builder().id(1L).code("HAM").forename("Lewis").surname("Hamilton").build();
    }

    @Test
    void shouldReturnDriverDTOWhenCreateDriver(){
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverDTO driverDTOResponse = driverService.saveDriver(driver);

        assertNotNull(driverDTOResponse);
        assertEquals("HAM", driverDTOResponse.code());

        verify(driverRepository, times(1)).save(any(Driver.class));
    }
}