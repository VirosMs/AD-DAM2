package org.example;

import java.sql.*;

public class Utils {

    private static String urlConexion = "virosms-f1bbdd.cpnousokn5wn.us-east-1.rds.amazonaws.com";
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
                System.out.println(e2.getMessage());
            }
        }catch (SQLException e1){
            System.out.println(e1.getMessage());
        }
    }

    public void createDrivers(){

    }

    public void selectDrivers(){
        try(Connection connection = DriverManager.getConnection(urlConexion, user, pass)){
            String select = "SELECT * FROM drivers";

            PreparedStatement ps = connection.prepareStatement(select);

            ResultSet rs = ps.getResultSet();

            while(rs.next()){
                System.out.println(rs);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
