package com.lukafilipovic.AlfaRomeoCarConfigurator.controller;

import com.lukafilipovic.AlfaRomeoCarConfigurator.model.Database;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.NavPanel;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

/**
 * User controller.
 */

@Getter
@Setter
public class UserController {
    private final Database database;
    private NavPanel navPanel;
    private User user;

    public UserController() {
        database = new Database();
    }

    /**
     * Checks if user with email already exists in database. If does return message to select another email and if does not saves user to the database.
     *
     * @param user
     * @return message.
     * @throws SQLException
     */
    public String saveUser(User user) throws SQLException {
        if (database.ifUserWithEmailExists(user.getEmail()))
            return "Korisnik s tom e-mail adresom već postoji! Molimo unesite drugu e-mail adresu.";
        else {
            database.saveUserToDB(user);
            return "Registracija uspješna!";
        }
    }

    public String logIn(String email, String password) throws SQLException {
        if (!database.ifUserWithEmailExists(email)) return "Korisnik s tom e-mail adresom ne postoji.";
        else if (database.areEmailAndPasswordMatching(email, password)) {
            this.user = database.loadUserFromDB(email, password);
            return "Prijava uspješna.";
        } else return "Netočna lozinka!";
    }

    public void setUserNameOnNavPanel(NavPanel panel, User user) {
        panel.getUserName().setText(user.getFirstName() + " " + user.getLastName());
    }

}
