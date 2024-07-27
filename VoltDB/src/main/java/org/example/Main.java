package org.example;

import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;
import org.voltdb.client.ClientResponse;
import org.voltdb.VoltTable;

public class Main {
    public static void main(String[] args) {
        try {
            // VoltDB client configuration with no user and password for simplicity
            ClientConfig clientConfig = new ClientConfig();
            Client client = ClientFactory.createClient(clientConfig);

            // Connect to VoltDB nodes
            System.out.println("Attempting to connect to VoltDB nodes...");
            client.createConnection("localhost:55017"); // Node 1
            System.out.println("Connected to VoltDB node.");

            // Call the SelectAllSubscribers stored procedure
            System.out.println("Attempting to call the stored procedure...");
            ClientResponse response = client.callProcedure("xxxx");

            // Process and print the results
            if (response.getStatus() == ClientResponse.SUCCESS) {
                VoltTable results = response.getResults()[0];
                while (results.advanceRow()) {
                    System.out.println("SUBSC_ID: " + results.getLong("SUBSC_ID") +
                            ", SUBSC_NAME: " + results.getString("SUBSC_NAME") +
                            ", SUBSC_SURNAME: " + results.getString("SUBSC_SURNAME") +
                            ", MSISDN: " + results.getString("MSISDN") +
                            ", TARIFF_ID: " + results.getLong("TARIFF_ID") +
                            ", START_DATE: " + results.getTimestampAsLong("START_DATE"));
                }
            } else {
                System.out.println("Procedure call failed: " + response.getStatusString());
            }

            // Close the client
            System.out.println("Closing client connection...");
            client.close();
            System.out.println("Client connection closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Program completed.");
    }
}
