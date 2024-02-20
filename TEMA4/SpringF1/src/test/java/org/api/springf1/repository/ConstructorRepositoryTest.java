package org.api.springf1.repository;

import org.api.springf1.model.Constructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
class ConstructorRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgre:15.5");

    @Autowired
    ConstructorRepository constructorRepository;

    Constructor constructor;

    @BeforeEach
    void setUp() {
        constructor = new Constructor();
        constructor.setRef("ref");
        constructorRepository.save(constructor);
    }

    @Test
    void findByRefIgnoreCase() {


        assertNotNull(constructor);
        assertEquals("ref", constructor.getRef());
    }

    @Test
    void deleteByRefIgnoreCase() {
        constructorRepository.deleteByRefIgnoreCase("ref");


        Constructor found = constructorRepository.findByRefIgnoreCase("ref").orElse(null);
        assertNull(found);
    }
}