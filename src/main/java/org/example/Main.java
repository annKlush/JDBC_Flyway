package org.example;

import database.ClientService;
import database.Database;
import database.DatabaseInitService;
import table.Client;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        new DatabaseInitService().initDb();//create DB and fill it

        Connection date = new Database().getConnection();
        System.out.println("client = " + new ClientService(date).listAll().toString());

        long id = 5;
        Client client0 = new Client();
        client0.setId(id);
        client0.setName(new ClientService(date).getById(id));
        System.out.println("client = " + client0);

        System.out.println("Last id is " + new ClientService(date).create(86, "Gigant"));

        new ClientService(date).setName(5, "kok");

        new ClientService(date).deleteById(1);

        System.out.println("client = " + new ClientService(date).listAll().toString());
    }
}
