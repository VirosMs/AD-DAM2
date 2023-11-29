package org.example;

import com.mongodb.client.MongoCollection;
import org.example.entities.Drivers;

import javax.management.Query;
import static com.mongodb.client.model.Filters.eq;

public class OperacionesCRUDPilotos {

    public void crearPiloto(Drivers driver, MongoCollection<Drivers> collection){
        collection.insertOne(driver);
    }

    public Drivers leerPiloto(int driverid, MongoCollection<Drivers> collection){
        return collection.find(eq("driverid", driverid)).first();
    }
}


