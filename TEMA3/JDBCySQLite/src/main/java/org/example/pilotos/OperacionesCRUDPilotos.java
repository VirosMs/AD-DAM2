package org.example.pilotos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperacionesCRUDPilotos {
    private static final Path dbURL = Path.of(".", "src", "main", "resources", "db", "f12006sqlite.db");
    public static final String JDBC_SQLITE = "jdbc:sqlite:";

    public void crearPiloto(Piloto piloto) {
        compUrl();

        try (Connection connection = DriverManager.getConnection(JDBC_SQLITE + dbURL)) {
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



    public Piloto leerPiloto(int id) {
        compUrl();


        try(Connection connection = DriverManager.getConnection(JDBC_SQLITE + dbURL)){

            String consultaSQL = "SELECT * FROM drivers WHERE driverId = ?";

            var sentencia = connection.prepareStatement(consultaSQL);

            sentencia.setInt(1, id);

            ResultSet result = sentencia.executeQuery();

            if (!result.next())
                throw new RuntimeException("No se ha encontrado el piloto con id " + id);

            return new Piloto(result.getInt("driverid"),
                    result.getString("code"),
                    result.getString("forename"),
                    result.getString("surname"),
                    result.getString("dob"),
                    result.getString("nationality"),
                    result.getString("url"));

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Piloto> leerPilotos(){
        compUrl();
        List<Piloto> pilotos;

        try(Connection connection = DriverManager.getConnection(JDBC_SQLITE + dbURL)){

            String consultaSQL = "SELECT * FROM drivers";

            var sentencia = connection.prepareStatement(consultaSQL);

            ResultSet result = sentencia.executeQuery();

            pilotos = new ArrayList<>();

            while(result.next()){
                pilotos.add(new Piloto(result.getInt("driverid"),
                        result.getString("code"),
                        result.getString("forename"),
                        result.getString("surname"),
                        result.getString("dob"),
                        result.getString("nationality"),
                        result.getString("url")));
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pilotos;
    }

    public void actualizarPiloto(Piloto piloto) {
        compUrl();
        int filasAfectadas;
        try (Connection connection = DriverManager.getConnection(JDBC_SQLITE + dbURL)){

            String consultaSQL = "UPDATE drivers SET code = ?, forename = ?, surname = ?, dob = ?, " +
                    "nationality = ?, url = ? WHERE driverId = ?";

            var sentencia = connection.prepareStatement(consultaSQL);

            sentencia.setString(1, piloto.getCode());
            sentencia.setString(2, piloto.getForename());
            sentencia.setString(3, piloto.getSurname());
            sentencia.setString(4, piloto.getDob().toString());
            sentencia.setString(5, piloto.getNationality());
            sentencia.setString(6, piloto.getUrl());
            sentencia.setInt(7, piloto.getDriverId());

            filasAfectadas = sentencia.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(filasAfectadas > 0)
            System.out.println("Piloto actualizado correctamente");
        else
            System.out.println("No se ha podido actualizar el piloto");
    }

    public void borrarPiloto(int id) {
        compUrl();
        int filasAfectadas;
        try (Connection connection = DriverManager.getConnection(JDBC_SQLITE + dbURL)) {

            String consultaSQL = "DELETE FROM drivers WHERE driverId = ?";

            var sentencia = connection.prepareStatement(consultaSQL);

            sentencia.setInt(1, id);

            filasAfectadas = sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (filasAfectadas > 0)
            System.out.println("Piloto borrado correctamente");
        else
            System.out.println("No se ha podido borrar el piloto");
    }

    public void mostrarClasificacionPiloto(){
        compUrl();

        try(Connection connection = DriverManager.getConnection(JDBC_SQLITE + dbURL)){
            String consultaSQL = "SELECT d.code, SUM(r.points) AS puntos FROM drivers d " +
                    "INNER JOIN results r ON d.driverid = r.driverid " +
                    "GROUP BY d.driverid " +
                    "ORDER BY puntos DESC";

            var sentencia = connection.prepareStatement(consultaSQL);

            ResultSet result = sentencia.executeQuery();

            while(result.next()){
                System.out.println(result.getString("code") + " " + result.getInt("puntos"));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarClasificacionConstructores(){
        compUrl();

        try(Connection connection = DriverManager.getConnection(JDBC_SQLITE + dbURL)){
            String consultaSQL = "SELECT c.name, SUM(r.points) AS puntos FROM constructors c " +
                    "INNER JOIN drivers d ON c.constructorid = d.constructorid " +
                    "INNER JOIN results r ON d.driverid = r.driverid " +
                    "GROUP BY c.constructorid " +
                    "ORDER BY puntos DESC";

            var sentencia = connection.prepareStatement(consultaSQL);

            ResultSet result = sentencia.executeQuery();

            while(result.next()){
                System.out.println(result.getString("name") + " " + result.getInt("puntos"));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void compUrl() {
        if (!Files.exists(dbURL)) {
            System.out.println("La base de datos no existe");
            System.exit(0);
        }
    }
}