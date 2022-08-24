package com.lukafilipovic.AlfaRomeoCarConfigurator.model.Database;

import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.car.CarAbs;

import java.sql.*;

/**
 * Database class which contains methods for connecting and disconnecting to the MySQL database. Also contains methods for executing SQL queries on the database.
 */
public class Database {
    private Connection con;

    /**
     * Method used for connecting to the database.
     * Source: Materials from the collage class: Napredno objektno programiranje.
     *
     * @throws SQLException
     */
    private void connect() throws SQLException {
        System.out.println("Connecting to database...");

        try {
            // load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // obtain connection
            String url = "jdbc:mysql://localhost:3306/noop_project"; // your database
            String user = "root"; // your user name
            String password = "database99"; // your password
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to -> " + con.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load driver!!!");
        }
    }

    /**
     * Method used for disconnecting to the database.
     * Source: Materials from the collage class: Napredno objektno programiranje.
     *
     * @throws SQLException
     */
    private void disconnect() throws SQLException {
        con.close();
        System.out.println("Disconnected from DB...");
    }

    /**
     * Saves user to the database.
     *
     * @param user
     * @throws SQLException
     */
    public void saveUserToDB(User user) throws SQLException {
        connect();
        if (con != null) {
            try {
                PreparedStatement statement = con.prepareStatement
                        ("INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)");
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPassword());
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        disconnect();
    }


    /**
     * Checks if user with email already exists in database.
     *
     * @param user
     * @return true if exists and false if does not.
     * @throws SQLException
     */

    public boolean ifUserWithEmailExists(String email) throws SQLException {
        connect();
        PreparedStatement statement = con.prepareStatement
                ("SELECT * FROM users WHERE email = ?");
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            disconnect();
            return true;
        } else {
            disconnect();
            return false;
        }
    }

    public boolean areEmailAndPasswordMatching(String email, String password) throws SQLException {
        connect();
        PreparedStatement statement = con.prepareStatement
                ("SELECT * FROM users WHERE email=? AND password=?");
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            disconnect();
            return true;
        } else {
            disconnect();
            return false;
        }

    }

    public User loadUserFromDB(String email, String password) throws SQLException {
        User user = new User();
        connect();
        PreparedStatement statement = con.prepareStatement
                ("SELECT * FROM users WHERE email=? AND password=?");
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            user.setId(resultSet.getLong(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
        }
        disconnect();
        return user;
    }

    /**
     * Checks if id already exists in cars table.
     *
     * @param carId
     * @return true if exists, false if does not.
     * @throws SQLException
     */

    public boolean ifCarIdExists(String carId) throws SQLException {
        connect();
        PreparedStatement statement = con.prepareStatement
                ("SELECT * FROM cars WHERE id = ?");
        statement.setString(1, carId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            disconnect();
            return true;
        } else {
            disconnect();
            return false;
        }
    }

    /**
     * Saves car to the database.
     *
     * @param carId  generated car id.
     * @param car    configured car.
     * @param userId references user which configured car.
     * @throws SQLException
     */
    public void saveCarToDB(String carId, CarAbs car, Long userId) throws SQLException {
        connect();
        if (con != null) {
            try {
                PreparedStatement statement = con.prepareStatement
                        ("INSERT INTO cars (id, description, price, user_id) VALUES (?, ?, ?, ?)");
                statement.setString(1, carId);
                statement.setString(2, car.getDescription());
                statement.setDouble(3, car.getPrice());
                statement.setLong(4, userId);
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        disconnect();
    }
}