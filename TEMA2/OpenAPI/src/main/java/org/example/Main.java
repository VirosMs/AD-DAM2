package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ultis.OperandoJson;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        URL buddiesApi = new URL("https://valorant-api.com/v1/buddies");

        HttpsURLConnection buddiesConnection = (HttpsURLConnection) buddiesApi.openConnection();

        buddiesConnection.setRequestMethod("GET");

        if (buddiesConnection.getResponseCode() == 200) {
            System.out.println("Success!");
            List<Buddies> listBuddies = OperandoJson.leerJsonApi(buddiesApi);

            if(listBuddies.isEmpty()){
                System.out.println("Lista empty");
            }else {
                System.out.println(listBuddies.stream().filter(buddies -> buddies.getDisplayName().equals("Closed Beta Coin Buddy")).findAny().orElse(null));
                //listBuddies.forEach(System.out::println);
            }

        } else {
            System.out.println("Error!");
        }
    }
}