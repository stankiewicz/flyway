package org.flywaydb.core.internal.database.cloudspanner;

import java.util.List;
import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.api.logging.Log;
import org.flywaydb.core.api.logging.LogFactory;
import org.flywaydb.core.internal.parser.Parser;
import org.flywaydb.core.internal.parser.ParsingContext;
import org.flywaydb.core.internal.parser.Token;

public class CloudSpannerParser  extends Parser {
    private static final Log LOG = LogFactory.getLog(CloudSpannerParser.class);
    public CloudSpannerParser(Configuration configuration, ParsingContext parsingContext) {
        super(configuration,parsingContext, 3);
    }

    @Override
    protected char getIdentifierQuote() {
        return '`';
    }

    protected char getAlternativeIdentifierQuote() {
        return '\"';
    }

    @Override
    protected Boolean detectCanExecuteInTransaction(String simplifiedStatement,
        List<Token> keywords) {
        LOG.debug("checking if [" + simplifiedStatement + "] can run in transaction");
        // Flyway tries to do hold transaction in which migration will happen
        return false;
    }
}
