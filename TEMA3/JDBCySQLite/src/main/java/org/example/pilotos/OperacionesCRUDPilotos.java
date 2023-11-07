package org.example.pilotos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OperacionesCRUDPilotos {
    private static final Path dbURL = Path.of(".", "src", "main", "resources", "db", "f12006sqlite.db");

    public void crearPiloto(Piloto piloto) {
        if (!Files.exists(dbURL)) {
            System.out.println("La base de datos no existe");
            System.exit(0);
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbURL)) {
            String insercionSQL = "INSERT INTO drivers (code, forename, surname, dob, nationality, url) VALUES (?,?,?,?,?,?)";


            var sentencia = connection.prepareStatement(insercionSQL);

            sentencia.setString(1, piloto.getCode());
            sentencia.setString(2, piloto.getForename());
            sentencia.setString(3, piloto.getSurname());
            sentencia.setString(4, piloto.getDob().toString());
            sentencia.setString(5, piloto.getNationality());
            sentencia.setString(6, piloto.getUrl());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas == 1)
                System.out.println("Piloto creado correctamente");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}