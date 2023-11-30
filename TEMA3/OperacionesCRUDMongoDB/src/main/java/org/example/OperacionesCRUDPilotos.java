package org.example;

import com.mongodb.client.MongoCollection;
import org.example.entities.Driver;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

import static org.example.StaticVariables.*;

public class OperacionesCRUDPilotos {

    public void crearPiloto(Driver driver, MongoCollection<Driver> collection){
        try {
            collection.insertOne(driver);
        } catch (Exception e) {
            System.out.println("Error al insertar el piloto");
        }
    }

    public Driver leerPiloto(int driverid, MongoCollection<Driver> collection){
        return collection.find(eq(DRIVER_ID, driverid)).first();
    }

    public List<Driver> leerPilotos(MongoCollection<Driver> collection){
        return collection.find().into(new ArrayList<>());
    }

    public void actualizarPiloto(Driver driver, MongoCollection<Driver> collection){
        collection.replaceOne(eq(DRIVER_ID, driver.getDriverid()), driver);
    }

    public void borrarPiloto(Driver driver, MongoCollection<Driver> collection){
        collection.deleteOne(eq(CODE, driver.getCode()));
    }

    public void mostrarPilotosOrdenadoresPorEdadDescendente(MongoCollection<Driver> collection){
        collection.find().sort("{dob: -1}").forEach(System.out::println);
    }

    public void printPilotos(MongoCollection<Driver> collection){
        collection.find().forEach(System.out::println);
    }
}


