package org.example.client;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@Data
public class Client {
    private int clientid;
    private String dni;
    private String nombre;
    private String apellidos;
    private String nacionalidad;
    private String telefono;
    private String email;
    private String contrasenya;

    public Client(int clientid, String dni, String nombre, String apellidos, String nacionalidad, String telefono, String email, String contrasenya) {
        this.clientid = clientid;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.email = email;
        this.contrasenya = contrasenya;
    }
}
