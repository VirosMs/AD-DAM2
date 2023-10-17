package org.example.atleta;

import lombok.Data;

import java.util.List;
@Data
public class AtletaFeminina {

    private String nombre;
    private List<String> prueba;
    private int edad;
    private String pais;
}
