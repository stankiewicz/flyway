package org.flywaydb.core.internal.database.cloudspanner;

import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.internal.parser.Parser;

public class CloudSpannerParser  extends Parser {
    public CloudSpannerParser(Configuration configuration) {
        super(configuration,3);
    }

    @Override
    protected char getIdentifierQuote() {
        return '`';
    }

    protected char getAlternativeIdentifierQuote() {
        return '\"';
    }
}
