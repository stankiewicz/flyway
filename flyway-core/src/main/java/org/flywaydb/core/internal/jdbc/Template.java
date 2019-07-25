package org.flywaydb.core.internal.jdbc;

import java.util.concurrent.Callable;

public interface Template {

  /**
   * Executes this callback within a transaction.
   *
   * @param callback The callback to execute.
   * @return The result of the transaction code.
   */
  <T> T execute(Callable<T> callback);

}
