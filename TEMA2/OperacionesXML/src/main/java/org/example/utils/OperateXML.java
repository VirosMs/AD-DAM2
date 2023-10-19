package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.atletas.AtletaFemenina;
import org.example.atletas.AtletaFemeninaCollections;

import java.nio.file.Path;
import java.util.List;

public class OperateXML {

    public static List<AtletaFemenina> leerListObjetosXml(Path ruta){
        try{
            return new XmlMapper().readValue(ruta.toFile(), new TypeReference<>(){});
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void escribirListObejetosXml(AtletaFemeninaCollections atletasFemeninas, Path ruta){
        try{
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(ruta.toFile(), atletasFemeninas);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
