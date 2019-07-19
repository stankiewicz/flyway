package org.flywaydb.core.internal.database.cloudspanner;

import org.flywaydb.core.internal.database.base.Connection;
import org.flywaydb.core.internal.database.base.Schema;
import org.flywaydb.core.internal.database.base.Table;

import java.sql.SQLException;
import java.util.concurrent.Callable;

public class CloudSpannerConnection extends Connection<CloudSpannerDatabase> {


    protected CloudSpannerConnection(CloudSpannerDatabase database, java.sql.Connection connection) {
        super(database, connection);
    }

    @Override
    protected String getCurrentSchemaNameOrSearchPath() throws SQLException {
        return "";
    }

    @Override
    public Schema getSchema(String name) {
        return new CloudSpannerSchema(jdbcTemplate, database, name);
    }

//    @Override
//    public <T> T lock(Table table, Callable<T> callable) {
//        return new ClousSpannerLockTemplate(jdbcTemplate, table.toString().hashCode()).execute(callable);
//    }
}
