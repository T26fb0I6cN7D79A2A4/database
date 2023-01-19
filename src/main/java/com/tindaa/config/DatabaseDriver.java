package com.tindaa.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {
  private DatabaseProperties props;

  public DatabaseDriver() {
    // default properties
  }

  public DatabaseDriver(DatabaseProperties props) {
    this.props = props;
  }

  public Connection getConnection() throws SQLException {
    // assign db parameters
    String url = props.getUrl();
    String user = props.getUsername();
    String password = props.getPassword();

    System.out.println("Using url: " + url);

    // create a connection to the database
    return DriverManager.getConnection(url, user, password);
  }
}
