package org.example;

import java.sql.*;
import java.util.List;
import java.util.Random;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.collection.IList;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        int n = 100000;

        oracleTest(n, random);

        hazelcastTest(n, random);
    }

    public static void oracleTest(int n, Random random) {
        String url = "jdbc:oracle:thin:@//localhost:1521/PDBORCL";
        String user = "SEFA";
        String password = "12345";

        Statement statement;

        String insertSQL = "INSERT INTO number_table (value) VALUES (?)";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            statement = connection.createStatement();
            String selectSQL = "SELECT * FROM number_table";

            long startTime = System.currentTimeMillis();

            for (int i = 0; i < n; i++) {
                preparedStatement.setInt(1, random.nextInt(100));
                preparedStatement.executeUpdate();
            }

            long endTime = System.currentTimeMillis();

            long elapsedTime = endTime - startTime;

            System.out.println("elapsed time for " + n + " insertions to oracle: " + elapsedTime + " milliseconds");

            startTime = System.currentTimeMillis();

            statement.executeQuery(selectSQL);

            endTime = System.currentTimeMillis();

            elapsedTime = endTime - startTime;

            System.out.println("elapsed time for " + n + " selection from oracle: " + elapsedTime + " milliseconds");

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void hazelcastTest(int n, Random random) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("127.0.0.1:5701");

        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IList<Integer> list = client.getList("my-distributed-list");

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(100));
        }

        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;

        System.out.println("elapsed time for " + n + " insertions to hazelcast: " + elapsedTime + " milliseconds");

        startTime = System.currentTimeMillis();

        List<Integer> allNumbers = list.subList(0, list.size());

        endTime = System.currentTimeMillis();

        elapsedTime = endTime - startTime;

        System.out.println("elapsed time for " + n + " selection from hazelcast: " + elapsedTime + " milliseconds");

        client.shutdown();
    }
}