package com.google.pso;

import com.simba.cloudspanner.core.jdbc42.CloudSpanner42DataSource;
import com.simba.cloudspanner.core.jdbc42.CloudSpanner42Driver;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {



        public static void main(String[] args) throws SQLException {
            // Create the Flyway instance and point it to the database

            String conn = "jdbc:cloudspanner://localhost;Project=radoslaws-playground;Instance=flywaydemo;Database=example-db;PvtKeyPath=/Users/radoslaws/repos/flyway/demo/key.json";
            DriverManager.registerDriver(new CloudSpanner42Driver());



//            CloudSpanner42DataSource ds = new
//                    com.simba.cloudspanner.
//                            core.jdbc42.CloudSpanner42DataSource();
//            DriverManager.getConnection(conn);
//            ds.setProject("radoslaws-playground");
//            ds.setDatabase("example-db");
//            ds.setInstance("flywaydemo");
//            ds.setCustomProperty("PvtKeyPath", "/Users/radoslaws/repos/flyway/demo/key.json");
            //ds.getConnection().createStatement();
            //Flyway flyway = Flyway.configure().dataSource(ds).load();
            Flyway flyway = Flyway.configure().dataSource(conn,null,null).load();

            // Start the migration
            flyway.migrate();
        }

}
