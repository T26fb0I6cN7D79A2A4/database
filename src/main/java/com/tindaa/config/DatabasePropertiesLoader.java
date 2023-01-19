package com.tindaa.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabasePropertiesLoader {
  private DatabasePropertiesLoader() {
    // default
  }

  public static DatabaseProperties loadProps(String path) {
    Properties props = new Properties();
    InputStream fileStream;

    try {
      fileStream = new FileInputStream(path);
      props.load(fileStream);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new DatabaseProperties(props);
  }
}
