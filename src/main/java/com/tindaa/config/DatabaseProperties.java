package com.tindaa.config;

import java.util.Properties;

public class DatabaseProperties {
  Properties properties;

  public DatabaseProperties() {
    // default properties
  }

  public DatabaseProperties(Properties properties) {
    this.properties = properties;
  }

  public String getUrl() {
    return properties.getProperty("mysql.url");
  }

  public String getUsername() {
    return properties.getProperty("mysql.username");
  }

  public String getPassword() {
    return properties.getProperty("mysql.password");
  }

}
