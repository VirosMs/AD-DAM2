package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.account.Accont;
import org.example.account.AccountCRUD;
import org.example.client.Client;
import org.example.client.ClientCRUD;
import org.example.db.PostgresSQLConnection;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    private static final String IPMongoDb = "ec2-54-146-188-92.compute-1.amazonaws.com";

    public static void main(String[] args) {
        try(MongoClient dbClient = MongoClients.create("mongodb://" + "arrsan" + ":" + "qwerty1234" + "@" + IPMongoDb + ":27017" )){

            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            MongoDatabase db = dbClient.getDatabase("arrsan").withCodecRegistry(pojoCodecRegistry);

            MongoCollection<Client> collection = db.getCollection("clients", Client.class);


            ClientCRUD clientCRUD = new ClientCRUD();
            AccountCRUD accountCRUD = new AccountCRUD();
            PostgresSQLConnection postgresSQLConnection = new PostgresSQLConnection();

            Client client1 = new Client(50, "12345678A", "Pepito", "Grillo", "Española", "666666666", "iauhdb@aujnwd", "1234");
            Client client2 = new Client(50, "12345678A", "Juan", "PEPE", "BR", "666666666", "iauhdb@aujnwd", "1234");
            Client client3 = new Client(60, "1234d", "ahhaha", "aiuhbd", "Española", "34312", "iauhdb@aujnwd", "1234");

            Accont account1 = new Accont("ES123456789", 1000.0d, client1.getClientid());
            Accont account2 = new Accont("ES123456789", 1000.0d, client3.getClientid());


            System.out.println("Creado");
            clientCRUD.createClient(client1, collection);
            clientCRUD.createClient(client3, collection);
            accountCRUD.mostrarAcconts();


            System.out.println("\nModificado");
            clientCRUD.modificarClient(client2, collection);
            accountCRUD.mostrarAcconts();

            System.out.println("\nTransaccion");

            accountCRUD.transferencia(account1, account2, 500.0d);
            accountCRUD.mostrarAcconts();


            System.out.println("\nBorrado");
            clientCRUD.borrarClient(client1, collection);
            clientCRUD.borrarClient(client3, collection);
            accountCRUD.deleteAccount(client1);
            accountCRUD.deleteAccount(client3);
            clientCRUD.mostrarClients(collection);
            accountCRUD.mostrarAcconts();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}