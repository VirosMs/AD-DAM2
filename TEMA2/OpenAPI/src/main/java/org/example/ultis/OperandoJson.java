package org.example.ultis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Buddies;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.util.List;

public class OperandoJson {

    public static List<Buddies> leerJsonApi(HttpsURLConnection buddiesConnection){
    try{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readValue(buddiesConnection.getInputStream(), new TypeReference<>() {});
        return objectMapper.readerFor(new TypeReference<>() {}).readValue(rootNode.get("data"));

    }catch (IOException e){
        throw new RuntimeException(e.getMessage());
    }
    }
}
