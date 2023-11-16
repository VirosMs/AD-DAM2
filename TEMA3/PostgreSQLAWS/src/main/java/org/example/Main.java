package org.example;

import org.example.entitys.Constructors;
import org.example.entitys.Drivers;

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();

        Constructors constructor = new Constructors("seat", "Seat F1", "Spain", "test");
        Drivers driver = new Drivers
                ("nor", "Lando", "Norris", "2023-11-16", "test", constructor, "test");

        Drivers driver2 = new Drivers
                ("nor", "Daniel", "Ricciardo", "2023-11-16", "test", constructor, "test");

        Drivers drivers3 = new Drivers
                ("nor", "Daniel", "Ricciardo", "2023-11-16", "test", constructor, "test");


        int constructorId = utils.createConstructors(driver.getConstructors());


        utils.createDriver(driver, constructorId);
        utils.createDriver(driver2, constructorId);

        utils.selectDrivers();

    }
}