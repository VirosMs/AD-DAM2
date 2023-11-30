package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.entities.Constructor;
import org.example.entities.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.example.StaticVariables.*;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class Main {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("org.mongodb.driver");
        OperacionesCRUDPilotos operacionesCRUDPilotos = new OperacionesCRUDPilotos();

        try(MongoClient dbClient = MongoClients.create("mongodb://" + USER + ":" + PASSWORD + "@" + IP + ":27017/" + DB_NAME + "?authSource=" + DB_NAME)){
            ;
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            MongoDatabase db = dbClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Driver> collection = db.getCollection("drivers", Driver.class);




            Constructor constructor = new Constructor( "red_bull", "Red Bull", "Austrian","http://en.wikipedia.org/wiki/Red_Bull_Racing");
            Driver driver = new Driver("CHA", constructor, java.sql.Date.valueOf(LocalDate.of(1999, 11, 13)), 50, "Charles", "Arruda", "Spanish", "https://en.wikipedia.org/wiki/Fernando_Alonso");

            operacionesCRUDPilotos.crearPiloto(driver, collection);

            System.out.println(driver);

            //operacionesCRUDPilotos.printPilotos(collection);

            Driver driver2 = operacionesCRUDPilotos.leerPiloto(2, collection);

            System.out.println(driver2);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}