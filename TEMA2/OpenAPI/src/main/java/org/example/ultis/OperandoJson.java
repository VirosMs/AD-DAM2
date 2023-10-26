package org.example.ultis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.Buddies;


import java.io.DataOutput;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

/**
 * This class is used to operate with the Apis and Json.
 */
public class OperandoJson {

    /**
     * This method is used to read the Json from the Api.
     * @param buddiesApi Api from where the Json is read.
     * @return List of Buddies.
     */
    public static List<Buddies> leerJsonApi(URL buddiesApi){
        ObjectMapper objectMapper =  new ObjectMapper();
    try{
        JsonNode rootNode = objectMapper.readTree(buddiesApi);
        return objectMapper.readValue(rootNode.get("data").traverse(), new TypeReference<>() {});

    }catch (IOException e){
        throw new RuntimeException(e.getMessage());
    }
    }

    /**
     * This method is used to write the Json from the Api.
     * @param buddiesList List of Buddies.
     * @param path Path where the Json is written.
     */
    public static void escribirJsonApi(List<Buddies> buddiesList, Path path){
        ObjectMapper objectMapper =  new ObjectMapper();
        try{
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(path.toFile(), buddiesList);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
