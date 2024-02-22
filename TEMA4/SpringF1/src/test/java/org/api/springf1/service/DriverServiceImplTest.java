package org.api.springf1.service;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.dto.DriverResponse;
import org.api.springf1.model.Driver;
import org.api.springf1.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldReturnDriverDTOWhenFindDriverByCode(){
        when(driverRepository.findByCodeIgnoreCase(any())).thenReturn(Optional.of(driver));

        DriverDTO driverDTOResponse = driverService.getDriverByCode("HAM");

        assertNotNull(driverDTOResponse);
        assertEquals("HAM", driverDTOResponse.code());

        verify(driverRepository, times(1)).findByCodeIgnoreCase("HAM");
    }

    @Test
    void shouldReturnDriverDTOWhenUpdateDriver(){
        when(driverRepository.findById(any())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverDTO driverDTOResponse = driverService.updateDriver(driver);

        assertNotNull(driverDTOResponse);
        assertEquals("HAM", driverDTOResponse.code());

        verify(driverRepository, times(1)).findById(1L);
        verify(driverRepository, times(1)).save(any(Driver.class));
    }

    @Test
    void shouldReturnNothingWhenDeleteDriverByCode(){
        when(driverRepository.findByCodeIgnoreCase(any())).thenReturn(Optional.of(driver));

        driverService.deleteDriverByCode("HAM");

        verify(driverRepository, times(1)).findByCodeIgnoreCase("HAM");
        verify(driverRepository, times(1)).delete(driver);
    }

    @Test
    void shouldReturnDriverResponseWhenGetAllDrivers(){
        when(driverRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(driver)));

        DriverResponse drivers = driverService.getDrivers(0, 10);

        assertEquals(List.of(driverDTO), drivers.content());

        verify(driverRepository, times(1)).findAll(PageRequest.of(0, 10));
    }
}