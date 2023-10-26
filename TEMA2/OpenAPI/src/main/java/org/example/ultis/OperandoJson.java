package org.example.ultis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Buddies;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class OperandoJson {

    public static List<Buddies> leerJsonApi(URL buddiesApi){
        ObjectMapper objectMapper =  new ObjectMapper();
    try{
        JsonNode rootNode = objectMapper.readTree(buddiesApi);
        return objectMapper.readValue(rootNode.get("data").traverse(), new TypeReference<>() {});

    }catch (IOException e){
        throw new RuntimeException(e.getMessage());
    }
    }
}
