package org.example;

import org.apache.ignite.client.ClientConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String igniteURL = "jdbc:ignite:thin://127.0.0.1";

        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            Connection connection = DriverManager.getConnection(igniteURL);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SUBSCRIBER");

            while (resultSet.next()) {
                int subscriberId = resultSet.getInt("SUBSC_ID");
                String subscriberName = resultSet.getString("SUBSC_NAME");
                String subscriberLastname = resultSet.getString("SUBSC_SURNAME");

                System.out.println(subscriberId + " " + subscriberName + " " + subscriberLastname);
            }

        } catch (ClientConnectionException | ClassNotFoundException ce) {
            System.out.println(ce.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}