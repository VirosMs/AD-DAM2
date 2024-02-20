package org.api.springf1.repository;

import org.api.springf1.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DriverRepositoryTest {

    @Autowired
    DriverRepository driverRepository;

    Driver driver;

    @BeforeEach
    void setUp() {
        driver = Driver.builder()
                .code("HAM")
                .forename("Lewis Hamilton")
                .surname("Hamilton")
                .build();
    }

    @Test
    void shouldReturnSavedDriverWhenSabe(){
        Driver driverSaved = driverRepository.save(driver);

        assertThat(driverSaved).isNotNull();
        assertThat(driverSaved.getId()).isGreaterThan(0);
    }

    @Test
    void shouldReturnMoreThanOneDriverWhenSaveTwoDrivers(){
        Driver driver2 = Driver.builder()
                .code("BOT")
                .forename("Valtteri")
                .surname("Bottas")
                .build();

        driverRepository.save(driver);
        driverRepository.save(driver2);

        assertThat(driverRepository.count()).isEqualTo(2);
    }

    @Test
    void shouldReturnDriverNotNullWhenFindByCode(){
        driverRepository.save(driver);

        Driver driverFound = driverRepository.findByCodeIgnoreCase("HAM").orElse(null);

        assertThat(driverFound).isNotNull();
    }

    @Test
    void shouldReturnDriverNotNullWhenUpdateDriver(){
        driverRepository.save(driver);

        Driver driverFound = driverRepository.findByCodeIgnoreCase("HAM").orElse(null);
        driverFound.setForename("Lewis");

        driverRepository.save(driverFound);

        Driver driverUpdated = driverRepository.findByCodeIgnoreCase("HAM").orElse(null);

        assertThat(driverUpdated).isNotNull();
        assertThat(driverUpdated.getForename()).isEqualTo("Lewis");
    }

    @Test
    void shouldReturnNullDriberWhenDelete(){
        driverRepository.save(driver);

        driverRepository.deleteByCodeIgnoreCase("HAM");

        Driver driverFound = driverRepository.findByCodeIgnoreCase("HAM").orElse(null);

        assertThat(driverFound).isNull();
    }
}