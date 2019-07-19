package com.google.pso;

import com.simba.cloudspanner.core.jdbc42.CloudSpanner42Driver;
import org.flywaydb.core.Flyway;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {



        public static void main(String[] args) throws SQLException {
            // Create the Flyway instance and point it to the database

            String conn = "jdbc:cloudspanner://localhost;Project=radoslaws-playground;Instance=flywaydemo;Database=example-db";
            DriverManager.registerDriver(new CloudSpanner42Driver());




            Flyway flyway = Flyway.configure().dataSource(conn,null,null).load();

            // Start the migration
            flyway.migrate();
        }

}
