package org.example;

import org.example.entitys.Constructors;
import org.example.entitys.Drivers;
import org.example.exeptions.DuplicateCodeDriverException;

import java.sql.*;

public class DatabaseManager {

    private static final String user = "postgres";
    private static final String pass = "12345678";
    private static final String urlConexion = "jdbc:postgresql://virosms-f1bbdd.cpnousokn5wn.us-east-1.rds.amazonaws.com:5432/f12006";

    public int createConstructors(Constructors constructor) {

        try (Connection connection = DriverManager.getConnection(urlConexion, user, pass)) {
            connection.setAutoCommit(false);

            String insertConstructor = "INSERT INTO constructors (constructorref, name, nationality, url)"
                    + "VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(insertConstructor, PreparedStatement.RETURN_GENERATED_KEYS)) {


                ps.setString(1, constructor.getConstructorref().toLowerCase());
                ps.setString(2, constructor.getName());
                ps.setString(3, constructor.getNationality());
                ps.setString(4, constructor.getUrl());

                ps.executeUpdate();

                connection.commit();
                connection.setAutoCommit(true);

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                constructor.setConstructorid(rs.getInt(1));

                return rs.getInt(1);

            } catch (SQLException e2) {
                handleSQLException(e2, connection);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        return constructor.getConstructorid();
    }

    public void createDriver(Drivers driver, int constructorId) throws DuplicateCodeDriverException {

        System.out.println(constructorId);

        try (Connection connection = DriverManager.getConnection(urlConexion, user, pass)) {

            connection.setAutoCommit(false);

            String insertDriver = "INSERT INTO drivers (code, forename, surname, dob, nationality, constructorid, url)"
                    + "VALUES (?, ?, ?, ?, ?, ?,?)";

            try (PreparedStatement ps = connection.prepareStatement(insertDriver)) {

                ps.setString(1, driver.getCode().toUpperCase());
                ps.setString(2, driver.getForename());
                ps.setString(3, driver.getSurname());
                ps.setDate(4, Date.valueOf(driver.getDob()));
                ps.setString(5, driver.getNationality());
                ps.setInt(6, constructorId);
                ps.setString(7, driver.getUrl());

                try {
                    ps.executeUpdate();
                    connection.commit();
                } catch (SQLException e) {
                    handleDuplicateCodeDriverException(e, driver, connection);
                }

            } catch (SQLException e2) {
                handleSQLException(e2, connection);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e1) {
            System.err.println(e1.getMessage());
        }
    }


    /**
     * Select all drivers
     */
    public void selectDrivers() {
        try (Connection connection = DriverManager.getConnection(urlConexion, user, pass)) {
            String select = "SELECT * FROM drivers";

            PreparedStatement ps = connection.prepareStatement(select);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("code"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectConstructors() {
        try (Connection connection = DriverManager.getConnection(urlConexion, user, pass)) {
            String select = "SELECT * FROM constructors";

            try (PreparedStatement ps = connection.prepareStatement(select)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString("constructorref"));
                }
            } catch (SQLException e2) {
                handleSQLException(e2, connection);
            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
    }


    /**
     * Delete driver
     *
     * @param code code
     */
    public void deleteDriver(String code) {
        try (Connection connection = DriverManager.getConnection(urlConexion, user, pass)) {
            try {
                connection.setAutoCommit(false);

                String delete = "DELETE FROM drivers WHERE code = ?";

                PreparedStatement ps = connection.prepareStatement(delete);

                ps.setString(1, code);

                ps.executeUpdate();

                connection.commit();

            } catch (SQLException e2) {
                handleSQLException(e2, connection);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
    }

    /**
     * Delete constructor
     *
     * @param constructorref constructorref
     */
    public void deleteConstructor(String constructorref) {
        try (Connection connection = DriverManager.getConnection(urlConexion, user, pass)) {

            connection.setAutoCommit(false);

            String delete = "DELETE FROM constructors WHERE constructorref = ?";

            PreparedStatement ps = connection.prepareStatement(delete);

            ps.setString(1, constructorref);

            try {
                ps.executeUpdate();

                connection.commit();

            } catch (SQLException e2) {
                handleSQLException(e2, connection);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
    }

    // ******* PROCEDIMIENTOS ALMACENADOS *******

    public void callResultsByDriver(String code){
        try(Connection connection = DriverManager.getConnection(urlConexion, user, pass)){
            try (CallableStatement cs = connection.prepareCall("{call get_results_by_driver(?)}")){
                cs.setString(1, code.toUpperCase());

                try(ResultSet rs = cs.executeQuery()){
                    System.out.printf("%" + (69 + " RESULTS BY DRIVER ".length()) / 2 + "s\n", " RESULTS BY DRIVER ");

                    System.out.println("---------------------------------------------------------------------");
                    System.out.printf("| %-5s | %-25s | %-7s | %-6s | %-10s |\n", "Round", "Circuit", "Result", "Points", "Date");
                    System.out.println("---------------------------------------------------------------------");
                    while (rs.next()){
                        int round = rs.getInt("round");
                        String circuit = rs.getString("circuit");
                        int result = rs.getInt("result");
                        int points = rs.getInt("points");
                        Date date = rs.getDate("date");

                        // Formatear e imprimir cada fila como una tabla
                        System.out.printf("| %-5d | %-25s | %-7d | %-6d | %-10s |\n",
                                round, circuit, result, points, date);
                    }
                    System.out.println("---------------------------------------------------------------------");

                }
            }
        }catch (SQLException e1){
            System.out.println(e1.getMessage());
        }
    }


    /**
     * Llama al procedimiento almacenado get_drivers_standings y muestra por consola los resultados
     */
    public void callDriversStandings() {
        try (Connection connection = DriverManager.getConnection(urlConexion, user, pass)) {

            try (CallableStatement cs = connection.prepareCall("{call get_drivers_standings()}")) {

                try (ResultSet rs = cs.executeQuery()) {
                    System.out.println("######## DRIVERS STANDINGS ########");
                    while (rs.next()) {
                        String driverName = rs.getString("driver");
                        long points = rs.getLong("points");

                        // Haz lo que necesites con la información obtenida
                        System.out.println("Driver: " + driverName + ", Points: " + points);
                    }
                }
            }

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
    }


    // ******** METODOS PRIVADOS ********


    // ######### METODOS DE INTERNOS #########

    /**
     * Genera un nuevo codigo para el piloto
     *
     * @param driver driver
     * @return codigo  String con el codigo generado
     */
    private String generarNuevoCodigo(Drivers driver) {
        String surname = driver.getSurname();
        String forename = driver.getForename();
        String code;

        if (surname.length() > 3) {
            code = surname.substring(1, 4).toUpperCase();
        } else if (surname.length() == 3) {
            code = forename.substring(0, 1).toUpperCase()
                    + surname.substring(0, 2).toUpperCase();
        } else {
            code = forename.substring(0, 1).toUpperCase()
                    + surname.substring(0, 1).toUpperCase()
                    + forename.substring(1, 2).toUpperCase();
        }
        return code;
    }

    // ######### METODOS DE EXCEPCIONES #########

    /**
     * Maneja las excepciones de codigo duplicado
     *
     * @param e          e
     * @param driver     driver
     * @param connection connection
     * @throws DuplicateCodeDriverException DuplicateCodeDriverException
     */
    private void handleDuplicateCodeDriverException(SQLException e, Drivers driver, Connection connection) throws DuplicateCodeDriverException {
        if (e.getMessage().contains("duplicate key value violates unique constraint \"drivers_pkey\"")) {
            throw new DuplicateCodeDriverException("El código del piloto ya existe");
        } else if (e.getMessage().contains("duplicate key value violates unique constraint \"drivers_code_key\"")) {
            driver.setCode(generarNuevoCodigo(driver));
            createDriver(driver, driver.getConstructors().getConstructorid());
        } else {
            handleSQLException(e, connection);
        }

    }

    /**
     * Handle SQLException haz rollback y muestra el error por consola
     * si no se puede hacer rollback muestra otro error por consola
     *
     * @param e          e
     * @param connection connection
     */
    private void handleSQLException(SQLException e, Connection connection) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        try {
            connection.rollback();
            System.err.println("ROLLBACK ejecutado");
        } catch (SQLException e3) {
            System.err.println("Error en rollback: " + e3.getMessage());
        }
    }
}



