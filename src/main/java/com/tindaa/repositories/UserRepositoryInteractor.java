package com.tindaa.repositories;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tindaa.config.DatabaseDriver;
import com.tindaa.core.repository.UserRepository;
import com.tindaa.core.user.User;

public class UserRepositoryInteractor implements UserRepository {

  private DatabaseDriver db;

  public UserRepositoryInteractor() {
    // default constructor
  }

  public UserRepositoryInteractor(DatabaseDriver db) {
    this.db = db;
  }

  @Override
  public boolean checkIfUserExistByEmail(String email) throws IOException {
    String call = "CALL check_user_exists(?)";

    boolean userExists = false;

    try {
      Connection conn = db.getConnection();

      CallableStatement callableStatement = conn.prepareCall(call);
      callableStatement.setString(1, email);

      ResultSet rs = callableStatement.executeQuery();

      if (rs.next()) {
        userExists = rs.getBoolean("user_exists");
      }

      conn.close();

      return userExists;
    } catch (SQLException e) {
      throw new IOException("Could not find user from database due to server error: " + e.getMessage());
    }
  }

  @Override
  public User getUserByUid(String uid) throws IOException {
    String call = "CALL get_user_by_uid(?);";

    try {
      Connection conn = db.getConnection();

      CallableStatement callableStatement = conn.prepareCall(call);
      callableStatement.setString(1, uid);

      ResultSet rs = callableStatement.executeQuery();

      User user = new User();

      if (rs.next()) {
        user.setUid(rs.getString("uid"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFullName(rs.getString("fullname"));
        user.setAddress(rs.getString("address"));
        user.setPhone(rs.getLong("phone"));
      }

      conn.close();

      return user;
    } catch (SQLException e) {
      throw new IOException("Could not find user by uid due to server error: " + e.getMessage());
    }
  }

  @Override
  public User getUserByEmail(String email) throws IOException {
    String call = "CALL get_user_by_email(?);";

    try {
      Connection conn = db.getConnection();

      CallableStatement callableStatement = conn.prepareCall(call);
      callableStatement.setString(1, email);

      ResultSet rs = callableStatement.executeQuery();

      User user = new User();

      if (rs.next()) {
        user.setUid(rs.getString("uid"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFullName(rs.getString("fullname"));
        user.setAddress(rs.getString("address"));
        user.setPhone(rs.getLong("phone"));
      }

      conn.close();

      return user;
    } catch (SQLException e) {
      throw new IOException("Could not find user by email due to server error: " + e.getMessage());
    }
  }

  @Override
  public void addUser(User user) throws IOException {
    String call = "CALL add_user(?, ?, ?, ?, ?);";

    String email = user.getEmail();
    String password = user.getEmail();
    String fullname = user.getEmail();
    String address = user.getEmail();
    Long phone = user.getPhone();

    try {
      Connection conn = db.getConnection();

      CallableStatement callableStatement = conn.prepareCall(call);

      callableStatement.setString(1, email);
      callableStatement.setString(2, password);
      callableStatement.setString(3, fullname);
      callableStatement.setString(4, address);
      callableStatement.setLong(5, phone);

      callableStatement.executeQuery();
      conn.close();
    } catch (SQLException e) {
      throw new IOException("Could not add user due to server error: " + e.getMessage());
    }
  }

  @Override
  public void updateUser(String uid, User user) throws IOException {
    String call = "CALL update_user(?, ?, ?, ?, ?, ?);";

    String email = user.getEmail();
    String password = user.getEmail();
    String fullname = user.getEmail();
    String address = user.getEmail();
    Long phone = user.getPhone();

    try {
      Connection conn = db.getConnection();

      CallableStatement callableStatement = conn.prepareCall(call);

      callableStatement.setString(1, uid);
      callableStatement.setString(2, email);
      callableStatement.setString(3, password);
      callableStatement.setString(4, fullname);
      callableStatement.setString(5, address);
      callableStatement.setLong(6, phone);

      callableStatement.executeQuery();
      conn.close();
    } catch (SQLException e) {
      throw new IOException("Could not update user due to server error: " + e.getMessage());
    }
  }

  @Override
  public void deleteUser(String uid) throws IOException {
    String call = "CALL delete_user(?);";

    try {
      Connection conn = db.getConnection();

      CallableStatement callableStatement = conn.prepareCall(call);
      callableStatement.setString(1, uid);
      callableStatement.executeQuery();

      conn.close();
    } catch (SQLException e) {
      throw new IOException("Could not delete user due to server error: " + e.getMessage());
    }
  }

}