package com.google.pso;

import com.simba.cloudspanner.core.jdbc42.CloudSpanner42Driver;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.core.internal.logging.console.ConsoleLog;
import org.flywaydb.core.internal.logging.console.ConsoleLogCreator;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {



        public static void main(String[] args) throws SQLException {
            // Create the Flyway instance and point it to the database
            LogFactory.setFallbackLogCreator(new ConsoleLogCreator(ConsoleLog.Level.DEBUG));
            String conn = "jdbc:cloudspanner://localhost;Project=radoslaws-playground;Instance=flywaydemo;Database=example-db";
            DriverManager.registerDriver(new CloudSpanner42Driver());




            Flyway flyway = Flyway.configure().dataSource(conn,null,null).load();
            flyway.repair();
            // Start the migration
            flyway.migrate();
        }

}
