package org.example;

import org.example.atletas.AtletaFemenina;
import org.example.atletas.AtletaFemeninaCollections;
import org.example.utils.OperateXML;

import java.nio.file.Path;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Path ruta = Path.of(".", "src", "main", "resources", "atletas_femeninas.xml");

        List<AtletaFemenina> atletas = List.of(new AtletaFemenina("Maria", 20, "España", List.of("100m", "200m", "400m")),
                new AtletaFemenina("Laura", 22, "España", List.of("100m", "200m", "400m")),
                new AtletaFemenina("Sara", 24, "España", List.of("100m", "200m", "400m")));

        AtletaFemeninaCollections atletasFemeninas = new AtletaFemeninaCollections(atletas);

        OperateXML.escribirListObejetosXml(atletasFemeninas, ruta);

        AtletaFemeninaCollections atletasFemeninasLeidos = new AtletaFemeninaCollections(OperateXML.leerListObjetosXml(ruta));

        System.out.println(atletasFemeninasLeidos);
    }
}