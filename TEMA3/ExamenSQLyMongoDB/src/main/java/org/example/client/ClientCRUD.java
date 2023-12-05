package org.example.client;

import com.mongodb.client.MongoCollection;
import org.example.account.Accont;
import org.example.account.AccountCRUD;
import org.example.db.PostgresSQLConnection;

import static com.mongodb.client.model.Filters.eq;

public class ClientCRUD {

    public void createClient(Client client, MongoCollection<Client> collection) {
        try{
            collection.insertOne(client);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void borrarClient(Client client, MongoCollection<Client> collection) {
        try{
            AccountCRUD accountCRUD = new AccountCRUD();
            accountCRUD.deleteAccount(client);
            collection.deleteOne(eq("clientid", client.getClientid()));
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void modificarClient(Client client, MongoCollection<Client> collection) {
        try{
            collection.replaceOne(eq("clientid", client.getClientid()), client);
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    public void mostrarClients(MongoCollection<Client> collection){
        try{
            collection.find().forEach(System.out::println);
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
