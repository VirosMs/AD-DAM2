package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.entities.Constructors;
import org.example.entities.Drivers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.example.StaticVariables.*;

public class Main {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("org.mongodb.driver");
        OperacionesCRUDPilotos operacionesCRUDPilotos = new OperacionesCRUDPilotos();
        try(MongoClient mongoClient = MongoClients.create("mongodb://" + user + ":" + password + "@" + dirDns + ":" + port + "/" + dbName + "?authSource="+ dbName)) {
            logger.info("Conexión establecida con la base de datos");
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));



            MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Drivers> collection = database.getCollection(collectionName, Drivers.class);


            Constructors constructor = new Constructors( "red_bull", "Red Bull", "Austrian","http://en.wikipedia.org/wiki/Red_Bull_Racing");
            Drivers driver = new Drivers("CHA", constructor, java.sql.Date.valueOf(LocalDate.of(1999, 11, 13)), 50, "Charles", "Arruda", "Spanish", "https://en.wikipedia.org/wiki/Fernando_Alonso");

            operacionesCRUDPilotos.crearPiloto(driver, collection);

            System.out.println(driver);

            Drivers driver2 = operacionesCRUDPilotos.leerPiloto(2, collection);

            System.out.println(driver2);

        } catch (Exception e) {
            logger.error("Error al conectar con la base de datos");
        }
    }
}