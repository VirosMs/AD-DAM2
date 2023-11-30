package org.example;

import com.mongodb.client.MongoCollection;
import org.example.entities.Driver;

import static com.mongodb.client.model.Filters.eq;

public class OperacionesCRUDPilotos {

    public void crearPiloto(Driver driver, MongoCollection<Driver> collection){
        try {
            collection.insertOne(driver);
        } catch (Exception e) {
            System.out.println("Error al insertar el piloto");
        }
    }

    public Driver leerPiloto(int driverid, MongoCollection<Driver> collection){
        return collection.find(eq("driverid", driverid)).first();
    }

    public void printPilotos(MongoCollection<Driver> collection){
        collection.find().forEach(System.out::println);
    }
}


