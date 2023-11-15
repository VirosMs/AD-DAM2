package org.example;

import java.sql.*;

public class Utils {

    private static String urlConexion = "jdbc:postgresql://virosms-f1bbdd.cpnousokn5wn.us-east-1.rds.amazonaws.com:5432/f12006";
    private static final String user = "postgres";
    private static final String pass = "12345678";
    public void createConstructors(Constructors constructor){

        try(Connection connection = DriverManager.getConnection(urlConexion, user, pass)){
            try{
                connection.setAutoCommit(false);

                String insertConstructor = "INSERT INTO constructor (constructorref, name, nationality, url)"
                        + "VALUES (?, ?, ?, ?)";

                PreparedStatement ps = connection.prepareStatement(insertConstructor, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, constructor.getConstructorref());
                ps.setString(2, constructor.getName());
                ps.setString(3, constructor.getNationality());
                ps.setString(4, constructor.getUrl());

                ps.executeUpdate();

                ps.close();

                connection.commit();

                connection.setAutoCommit(true);

            }catch (SQLException e2){
                System.out.println(e2.getClass().getName() + ":" + e2.getMessage());
                try{
                    connection.rollback();
                    System.err.println("ROLLBACK ejecutado");
                }catch (SQLException e3){
                    System.err.println("Error en rollback: " + e3.getMessage());
                }
            }
        }catch (SQLException e1){
            System.out.println(e1.getMessage());
        }
    }

    public void createDriver(Drivers driver){

        try(Connection connection = DriverManager.getConnection(urlConexion, user, pass)){
            try {
                connection.setAutoCommit(false);

                String insertDriver = "INSERT INTO drivers (code, forename, surname, dob, nationality, constructorid, url)"
                        + "VALUES (?, ?, ?, ?, ?, ?,?)";

                PreparedStatement ps = connection.prepareStatement(insertDriver, PreparedStatement.RETURN_GENERATED_KEYS);

                ps.setString(1, driver.getCode());
                ps.setString(2, driver.getForename());
                ps.setString(3, driver.getSurname());
                ps.setDate(4, Date.valueOf(driver.getDob()));
                ps.setString(5, driver.getNationality());
                ps.setInt(6, driver.getConstructors().getConstructorid());
                ps.setString(7, driver.getUrl());

                ps.executeUpdate();

                ps.close();

                connection.commit();

                connection.setAutoCommit(true);

            }catch (SQLException e2){
                System.out.println(e2.getClass().getName() + ":" + e2.getMessage());
                try{
                    connection.rollback();
                    System.err.println("ROLLBACK ejecutado");
                }catch (SQLException e3){
                    System.err.println("Error en rollback: " + e3.getMessage());
                }
            }
        }catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void selectDrivers(){
        try(Connection connection = DriverManager.getConnection(urlConexion, user, pass)){
            String select = "SELECT * FROM drivers";

            PreparedStatement ps = connection.prepareStatement(select);


            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString("code"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
