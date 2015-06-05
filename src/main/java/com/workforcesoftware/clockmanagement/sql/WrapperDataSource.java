package com.workforcesoftware.clockmanagement.sql;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class WrapperDataSource implements DataSource {

  public void setFlyway(Flyway flyway) {
    flyway.setInitOnMigrate(true);
    flyway.setInitVersion("0");
    this.flyway = flyway;
  }

  public void setWrappedDataSource(DataSource wrappedDataSource) {
    this.wrappedDataSource = wrappedDataSource;
  }

  private synchronized void ensureFlywayUpgradeComplete() {
    try {
      if(!flywayUpgradeCompleted) {
        flyway.migrate();
        flywayUpgradeCompleted = true;
      }
    } catch(Exception e) {
      throw new RuntimeException(
              "Flyway upgrade failed.", e);
    }
  }

  @Override
  public Connection getConnection() throws SQLException {
    ensureFlywayUpgradeComplete();
    return wrappedDataSource.getConnection();
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    ensureFlywayUpgradeComplete();
    return wrappedDataSource.getConnection(username, password);
  }

  @Override
  public PrintWriter getLogWriter() throws SQLException {
    return wrappedDataSource.getLogWriter();
  }

  @Override
  public void setLogWriter(PrintWriter out) throws SQLException {
    wrappedDataSource.setLogWriter(out);
  }

  @Override
  public void setLoginTimeout(int seconds) throws SQLException {
    wrappedDataSource.setLoginTimeout(seconds);
  }

  @Override
  public int getLoginTimeout() throws SQLException {
    return wrappedDataSource.getLoginTimeout();
  }

  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return wrappedDataSource.getParentLogger();
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return wrappedDataSource.unwrap(iface);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return wrappedDataSource.isWrapperFor(iface);
  }

  /** The java.sql.DataSource wrapped by this object. */
  private DataSource wrappedDataSource;
  private Flyway flyway;
  private boolean flywayUpgradeCompleted = false;
}
