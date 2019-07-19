package org.flywaydb.core.internal.database.cloudspanner;

import org.flywaydb.core.internal.database.base.Schema;
import org.flywaydb.core.internal.database.base.Table;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;

public class CloudSpannerSchema extends Schema<CloudSpannerDatabase, CloudSpannerTable> {
    /**
     * Creates a new schema.
     *
     * @param jdbcTemplate The Jdbc Template for communicating with the DB.
     * @param database     The database-specific support.
     * @param name         The name of the schema.
     */
    public CloudSpannerSchema(JdbcTemplate jdbcTemplate, CloudSpannerDatabase database, String name) {
        super(jdbcTemplate, database, name);
    }

    @Override
    protected boolean doExists() throws SQLException {
        return name.equals("");
    }

    @Override
    protected boolean doEmpty() throws SQLException {
        // is tables enough? what about indexes?
        return !jdbcTemplate.queryForBoolean("SELECT EXISTS (" +
                "  SELECT 1" +
                "  FROM information_schema.tables" +
                "  WHERE" +
                "  table_schema=''" +
                ")");
    }

    @Override
    protected void doCreate() throws SQLException {
        throw new SQLFeatureNotSupportedException("CREATE DATABASE not supported");
    }

    @Override
    protected void doDrop() throws SQLException {
        throw new SQLFeatureNotSupportedException("DROP DATABASE not supported");
    }

    @Override
    protected void doClean() throws SQLException {
        throw new SQLFeatureNotSupportedException("CLEAN DATABASE not supported");

    }

    @Override
    protected CloudSpannerTable[] doAllTables() throws SQLException {
        String query =
                "SELECT table_name FROM information_schema.tables WHERE table_schema=''";
        List<String> tableNames = jdbcTemplate.queryForStringList(query, name);
        CloudSpannerTable[] tables = new CloudSpannerTable[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            tables[i] = new CloudSpannerTable(jdbcTemplate, database, this, tableNames.get(i));
        }
        return tables;
    }

    @Override
    public Table getTable(String tableName) {
        return new CloudSpannerTable(jdbcTemplate, database, this, tableName);
    }
}
