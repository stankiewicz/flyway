package org.flywaydb.core.internal.database.cloudspanner;

import java.sql.DatabaseMetaData;
import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.internal.database.base.Database;
import org.flywaydb.core.internal.database.base.Table;
import org.flywaydb.core.internal.jdbc.JdbcConnectionFactory;

import java.sql.Connection;

public class CloudSpannerDatabase extends Database<CloudSpannerConnection> {
    public CloudSpannerDatabase(Configuration configuration, JdbcConnectionFactory jdbcConnectionFactory) {
        super(configuration, jdbcConnectionFactory);
    }

    @Override
    protected CloudSpannerConnection doGetConnection(Connection connection) {
        return new CloudSpannerConnection(this, connection);
    }

    @Override
    public void ensureSupported() {

    }

    @Override
    public boolean supportsDdlTransactions() {
        return false;
    }

    Connection getNewRawConnection() {
        return jdbcConnectionFactory.openConnection();
    }

    @Override
    public boolean supportsChangingCurrentSchema() {
        return false;
    }

    @Override
    public String getBooleanTrue() {
        return "true";
    }

    @Override
    public String getBooleanFalse() {
        return "false";
    }

    @Override
    protected String doQuote(String identifier) {
        return "`" + identifier + "`";
    }

    @Override
    public boolean catalogIsSchema() {
        return false;
    }

    @Override
    public String getRawCreateScript(Table table, boolean baseline) {
        return "" +
                "CREATE TABLE " + table.getName() + " (\n" +
                "    installed_rank INT64 NOT NULL,\n" +
                "    version STRING(50),\n" +
                "    description STRING(200) NOT NULL,\n" +
                "    type STRING(20) NOT NULL,\n" +
                "    script STRING(1000) NOT NULL,\n" +
                "    checksum INT64,\n" +
                "    installed_by STRING(100) NOT NULL,\n" +
                "    installed_on TIMESTAMP OPTIONS (allow_commit_timestamp=true),\n" +
                "    execution_time INT64 NOT NULL,\n" +
                "    success BOOL NOT NULL\n" +
                ") PRIMARY KEY (installed_rank DESC);\n" +
                (baseline ? getBaselineStatement(table) + ";\n" : "") +
                "CREATE INDEX " + table.getName() + "_s_idx ON " + table.getName() + " (\"success\");";
    }
}
