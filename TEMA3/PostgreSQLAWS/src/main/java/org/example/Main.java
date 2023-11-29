package org.example;

import org.example.entitys.Constructors;
import org.example.entitys.Drivers;
import org.example.exeptions.DuplicateCodeDriverException;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();

        databaseManager.deleteDriver("NOR");
        databaseManager.deleteDriver("ICC");
        databaseManager.deleteDriver("RIC");
        databaseManager.deleteConstructor("seat");


        Constructors constructor = new Constructors("seat", "Seat F1", "Spain", "test");
        Drivers driver = new Drivers
                ("nor", "Lando", "Norris", "2023-11-16", "test", constructor, "test");

        Drivers driver2 = new Drivers
                ("nor", "Daniel", "Ricciardo", "2023-11-16", "test", constructor, "test");

        Drivers drivers3 = new Drivers("ric", "Daniel", "Ricciardo", "2023-11-16", "test", constructor, "test");


        int constructorId = databaseManager.createConstructors(driver.getConstructors());

        try{
            databaseManager.createDriver(driver, constructorId);
            databaseManager.createDriver(driver2, constructorId);
            databaseManager.createDriver(drivers3, constructorId);
        }catch (DuplicateCodeDriverException e){
            System.err.println(e.getMessage());
        }

        databaseManager.selectDrivers();
        databaseManager.selectConstructors();

        databaseManager.callDriversStandings();

        databaseManager.callResultsByDriver("alo");

    }
}