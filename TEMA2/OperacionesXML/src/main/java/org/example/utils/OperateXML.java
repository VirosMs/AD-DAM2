package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.atletas.AtletaFemenina;

import java.nio.file.Path;
import java.util.List;

public class OperateXML {
    public static AtletaFemenina leerObjetoXml(Path ruta){
        try{
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(ruta.toFile(), AtletaFemenina.class);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<AtletaFemenina> leerListObjetosXml(Path ruta){
        try{
            return new XmlMapper().readValue(ruta.toFile(), new TypeReference<>(){});
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String escribirObjetoXml(AtletaFemenina atletaFemenina){
        try{
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return xmlMapper.writeValueAsString(atletaFemenina);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String escrebirListObjetosXml1v2(List<AtletaFemenina> atletasFemeninas){
        try{
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return xmlMapper.writeValueAsString(atletasFemeninas);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirListObejetosXml2v2(List<AtletaFemenina> atletasFemeninas, Path ruta){
        try{
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(ruta.toFile(), atletasFemeninas);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
