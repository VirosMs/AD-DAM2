package org.example.account;

import org.example.client.Client;
import org.example.db.PostgresSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountCRUD {

    private final PostgresSQLConnection postgresSQLConnection = new PostgresSQLConnection();

    /**
     * Crea una cuenta en la base de datos.
     *
     * @param client  Cliente al que pertenece la cuenta
     * @param account Cuenta a crear
     */
    public void createAccount(Client client, Accont account) {
        try (Connection connection = postgresSQLConnection.postgresSQLConect()){

            String sql = "INSERT INTO accounts (iban, balance, clientid) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, account.getIban());
                statement.setDouble(2, account.getBalance());
                statement.setInt(3, client.getClientid());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Borra una cuenta de la base de datos.
     *
     * @param client Cliente al que pertenece la cuenta
     */
    public void deleteAccount(Client client) {

        String delete = "DELETE FROM accounts WHERE clientid = ?";

        try (Connection connection = postgresSQLConnection.postgresSQLConect()){


            try (PreparedStatement statement = connection.prepareStatement(delete)) {
                statement.setInt(1, client.getClientid());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza el saldo de una cuenta.
     *
     * @param account Cuenta a actualizar
     */
    public void updateAccount(Accont account) {

        String update = "UPDATE accounts SET balance = ? WHERE iban = ?";
        try (Connection connection = postgresSQLConnection.postgresSQLConect()){

            try (PreparedStatement statement = connection.prepareStatement(update)) {
                statement.setString(2, account.getIban());
                statement.setDouble(1, account.getBalance());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra todas las cuentas de la base de datos.
     */
    public void mostrarAcconts() {

        String select = "SELECT * FROM accounts";

        try (Connection connection = postgresSQLConnection.postgresSQLConect()){
                        try (PreparedStatement statement = connection.prepareStatement(select)) {
                statement.executeQuery();

                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {
                    System.out.println("Account ID: " + resultSet.getInt("accountid"));
                    System.out.println("IBAN: " + resultSet.getString("iban"));
                    System.out.println("Balance: " + resultSet.getDouble("balance"));
                    System.out.println("Client ID: " + resultSet.getInt("clientid"));
                    System.out.println();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica si una cuenta existe en la base de datos.
     *
     * @param account Cuenta a verificar
     * @return
     */
    public boolean findAccount(Accont account) {
        String select = "SELECT * FROM accounts WHERE iban = ?";
        try (Connection connection = postgresSQLConnection.postgresSQLConect();
             PreparedStatement statement = connection.prepareStatement(select)) {

            statement.setString(1, account.getIban());
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // Devuelve true si hay al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();
            // Aquí puedes manejar la excepción o lanzarla nuevamente si es apropiado en tu aplicación.
        }

        return false; // Si hubo algún problema, se considera que la cuenta no existe.
    }


    /**
     * Realiza una transferencia entre dos cuentas.
     *
     * @param envia    Cuenta que envía el dinero
     * @param recibe   Cuenta que recibe el dinero
     * @param cantidad Cantidad a transferir
     */
    public void transferencia(Accont envia, Accont recibe, double cantidad) {
        Connection connection = null;
        try {
            connection = postgresSQLConnection.postgresSQLConect();
            connection.setAutoCommit(false);

            // Verificar si hay suficiente dinero en la cuenta de origen
            if (envia.getBalance() - cantidad < 0 || findAccount(recibe)) {
                throw new Exception("No hay suficiente dinero en la cuenta de origen");
            }

            // Actualizar la cuenta de origen
            String updateEnvia = "UPDATE accounts SET balance = ? WHERE iban = ?";
            try (PreparedStatement enviando = connection.prepareStatement(updateEnvia)) {
                enviando.setDouble(1, envia.getBalance() - cantidad);
                enviando.setString(2, envia.getIban());
                enviando.executeUpdate();
            }

            // Actualizar la cuenta de destino
            String updateRecibe = "UPDATE accounts SET balance = ? WHERE iban = ?";
            try (PreparedStatement recibiendo = connection.prepareStatement(updateRecibe)) {
                recibiendo.setDouble(1, recibe.getBalance() + cantidad);
                recibiendo.setString(2, recibe.getIban());
                recibiendo.executeUpdate();
            }

            connection.commit();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                    System.err.println("ROLLBACK ejecutado");
                }
            } catch (SQLException e3) {
                System.err.println("Error en rollback: " + e3.getMessage());
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
