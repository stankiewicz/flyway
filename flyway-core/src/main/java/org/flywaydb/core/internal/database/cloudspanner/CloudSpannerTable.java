package org.flywaydb.core.internal.database.cloudspanner;

import org.flywaydb.core.internal.database.base.Database;
import org.flywaydb.core.internal.database.base.Table;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloudSpannerTable extends Table<CloudSpannerDatabase, CloudSpannerSchema> {


    /**
     * Creates a new table.
     *
     * @param jdbcTemplate The Jdbc Template for communicating with the DB.
     * @param database     The database-specific support.
     * @param schema       The schema this table lives in.
     * @param name         The name of the table.
     */
    public CloudSpannerTable(JdbcTemplate jdbcTemplate, CloudSpannerDatabase database, CloudSpannerSchema schema, String name) {
        super(jdbcTemplate, database, schema, name);
    }

    @Override
    protected boolean doExists() throws SQLException {
        try (Connection c = database.getNewRawConnection()){
            Statement s = c.createStatement();
            s.execute("SET READONLY = true");
            s.close();
            try(ResultSet tables = c.getMetaData().getTables("", "", this.name, null)){
                return tables.next();
            }
        }
    }

    @Override
    protected void doLock() throws SQLException {
    }

    @Override
    protected void doDrop() throws SQLException {
        try(Statement statement = jdbcTemplate.getConnection().createStatement()){
            statement.execute("DROP TABLE " + database.quote(name));
        }
    }

    @Override
    public String toString() {
            return database.quote(name);
    }
}
