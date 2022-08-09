package com.lukafilipovic.AlfaRomeoCarConfigurator.model;

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
                        ("INSERT INTO users (first_name, last_name, username, email, password) VALUES (?, ?, ?, ?, ?)");
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getUsername());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getPassword());
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        disconnect();
    }

    /**
     * Checks if user with username already exists in database.
     *
     * @param user
     * @return true if exists and false if does not.
     * @throws SQLException
     */

    public boolean ifUserWithUsernameExists(User user) throws SQLException {
        connect();
        PreparedStatement statement = con.prepareStatement
                ("SELECT * FROM users WHERE username = ?");
        statement.setString(1, user.getUsername());
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
     * Checks if user with email already exists in database.
     *
     * @param user
     * @return true if exists and false if does not.
     * @throws SQLException
     */

    public boolean ifUserWithEmailExists(User user) throws SQLException {
        connect();
        PreparedStatement statement = con.prepareStatement
                ("SELECT * FROM users WHERE email = ?");
        statement.setString(1, user.getEmail());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            disconnect();
            return true;
        } else {
            disconnect();
            return false;
        }
    }

}
