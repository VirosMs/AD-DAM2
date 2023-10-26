package org.example;


import org.example.ultis.OperandoJson;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Url de la api
        URL buddiesApi = new URL("https://valorant-api.com/v1/buddies");

        // Ruta donde se guardará el json
        Path ruta = Path.of(".", "src", "main", "resources", "buddies.json");

        // Conexión con la api
        HttpsURLConnection buddiesConnection = (HttpsURLConnection) buddiesApi.openConnection();

        // Método de la conexión
        buddiesConnection.setRequestMethod("GET");

        // Código de respuesta de la conexión
        if (buddiesConnection.getResponseCode() == 200) {
            System.out.println("Success!");
            // Leer el json de la api y guardarlo en una lista
            List<Buddies> listBuddies = OperandoJson.leerJsonApi(buddiesApi);

            // Comprobar si la lista está vacía, si lo está, imprime un mensaje si no, continua con el programa
            if(listBuddies.isEmpty()){
                System.out.println("Lista empty");
            }else {
                // Imprime un buddy en concreto
                System.out.println(listBuddies.stream().filter(buddies -> buddies.getDisplayName()
                        .equals("Closed Beta Coin Buddy")).findAny().orElse(null));

                // Escribir el json en la ruta especificada
                OperandoJson.escribirJsonApi(listBuddies, ruta);
            }



        } else {
            System.out.println("Error!");
        }
    }
}