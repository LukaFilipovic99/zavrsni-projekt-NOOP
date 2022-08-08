package com.lukafilipovic.AlfaRomeoCarConfigurator.controller;

import com.lukafilipovic.AlfaRomeoCarConfigurator.model.Database;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

/**
 * User controller.
 */
public class UserController {
    private final Database database;

    public UserController(){
        database=new Database();
    }

    public String saveUser(User user) throws SQLException {
        if (database.ifUserWithUsernameExists(user)) return "Korisničko ime je zauzeto! Molimo odaberite drugo korisničko ime.";
        else if (database.ifUserWithEmailExists(user)) return "Korisnik s tom e-mail adresom već postoji! Molimo unesite drugu e-mail adresu.";
        else {
            database.saveUserToDB(user);
            return "Registracija uspješna!";
        }
    }
}
