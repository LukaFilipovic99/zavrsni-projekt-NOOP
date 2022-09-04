package com.lukafilipovic.AlfaRomeoCarConfigurator.controller;

import com.lukafilipovic.AlfaRomeoCarConfigurator.model.database.Database;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.user.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.car.*;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car.EquipmentFrame;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.common.NavPanel;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car.FinishConfigurationFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Controller {
    private final Database database;
    private User user;
    private CarAbs car;

    public Controller() {
        database = new Database();
    }

    /**
     * Checks if user with email already exists in database. If does return status code 0 and shows message,
     * if does not return status code 1, saves user to the database and shows message about successful registration.
     *
     * @param user
     * @return status code.
     * @throws SQLException
     */
    public int saveUser(User user) throws SQLException {
        if (database.ifUserWithEmailExists(user.getEmail())) {
            JOptionPane.showMessageDialog(
                    new Frame(), "Korisnik s tom e-mail adresom već postoji! Molimo unesite drugu e-mail adresu.", "Registracija", JOptionPane.PLAIN_MESSAGE);
            return 0;
        } else {
            database.saveUserToDB(user);
            JOptionPane.showMessageDialog(
                    new Frame(), "Registracija uspješna.", "Registracija", JOptionPane.PLAIN_MESSAGE);
            return 1;
        }
    }

    /**
     * Checks if user with email exists in database. If does not return status code 0 and message. If does checks if email and
     * password match data in the database and if does loads user data from the database and return status code 1.
     * If password does not match with email return status code 2 and shows message.
     *
     * @param email
     * @param password
     * @return status code.
     * @throws SQLException
     */
    public int logIn(String email, String password) throws SQLException {
        if (!database.ifUserWithEmailExists(email)) {
            JOptionPane.showMessageDialog(
                    new Frame(), "Korisnik s tom e-mail adresom ne postoji.", "Prijava", JOptionPane.PLAIN_MESSAGE);
            return 0;
        } else if (database.areEmailAndPasswordMatching(email, password)) {
            this.user = database.loadUserFromDB(email, password);
            return 1;
        } else {
            JOptionPane.showMessageDialog(
                    new Frame(), "Netočna lozinka!", "Prijava", JOptionPane.PLAIN_MESSAGE);
            return 2;
        }
    }

    public void setUserNameOnNavPanel(NavPanel panel, User user) {
        panel.getUserName().setText(user.getFirstName() + " " + user.getLastName());
    }

    public void setPriceToEquipmentFramePricePanel(EquipmentFrame frame, double price) {
        frame.getPricePanel().getPriceLbl().setText(price + " kn");
    }

    /**
     * Wrap car with engine decorator (EngineDecoratedCar) using decorator pattern.
     * @param car
     * @param engine
     * @param price
     * @return EngineDecoratedCar
     */
    public EngineDecoratedCar addEngineToCar(CarAbs car, String engine, double price) {
        return new EngineDecoratedCar(car, engine, price);
    }

    /**
     * Wrap car with color decorator (ColorDecoratedCar) using decorator pattern.
     * @param car
     * @param color
     * @param price
     * @return ColorDecoratedCar
     */
    public ColorDecoratedCar addColorToCar(CarAbs car, String color, double price) {
        return new ColorDecoratedCar(car, color, price);
    }

    /**
     * Wrap car with wheels decorator (WheelsDecoratedCar) using decorator pattern.
     * @param car
     * @param wheels
     * @param price
     * @return WheelsDecoratedCar
     */
    public WheelsDecoratedCar addWheelsToCar(CarAbs car, String wheels, double price) {
        return new WheelsDecoratedCar(car, wheels, price);
    }

    /**
     * Wrap car with brakes decorator (BrakesDecoratedCar) using decorator pattern.
     * @param car
     * @param brakes
     * @param price
     * @return BrakesDecoratedCar
     */
    public BrakesDecoratedCar addBrakesToCar(CarAbs car, String brakes, double price) {
        return new BrakesDecoratedCar(car, brakes, price);
    }

    /**
     * Wrap car with seats decorator (SeatsDecoratedCar) using decorator pattern.
     * @param car
     * @param seats
     * @param price
     * @return SeatsDecoratedCar
     */
    public SeatsDecoratedCar addSeatsToCar(CarAbs car, String seats, double price) {
        return new SeatsDecoratedCar(car, seats, price);
    }

    /**
     * Wrap car with additional equipment decorator (AdditionalEquipmentDecoratedCar) using decorator pattern.
     * @param car
     * @param equipmentList
     * @param price
     * @return AdditionalEquipmentDecoratedCar
     */
    public AdditionalEquipmentDecoratedCar addEquipmentToCar(CarAbs car, List<String> equipmentList, double price) {
        return new AdditionalEquipmentDecoratedCar(car, equipmentList, price);
    }

    /**
     * Saves configured car to the database.
     *
     * @param user user who configured car
     * @param car  configured car
     * @return carId
     * @throws SQLException
     */
    public String saveCar(User user, CarAbs car) throws SQLException {
        String carId;
        do {
            carId = generateCarId();
        } while (database.ifCarIdExists(carId));
        database.saveCarToDB(carId, car, user.getId());
        return carId;
    }

    public void setAlfaCodeToFinishConfigurationFrame(FinishConfigurationFrame frame, String alfaCode) {
        frame.getAlfaCode().setText(alfaCode);
    }

    /**
     * Get data from database of all cars configured by user and shows it on the carsTxtArea.
     *
     * @param user
     * @param carsTxtArea
     * @throws SQLException
     */
    public void showAllCarsOnTxtArea(User user, JTextArea carsTxtArea) throws SQLException {
        String cars = database.getAllCarsByUserId(user.getId());
        carsTxtArea.setText(cars);
    }


    /**
     * Get data about car with selected id and shows it on the carsTxtArea.
     *
     * @param user
     * @param carId
     * @param carsTxtArea
     * @throws SQLException
     */
    public void showCarWithIdOnTxtArea(User user, String carId, JTextArea carsTxtArea) throws SQLException {
        if (database.ifCarIdExists(carId)) {
            String car = database.getCarByUserIdAndCarId(user.getId(), carId);
            carsTxtArea.setText(car);
        } else {
            JOptionPane.showMessageDialog(new Frame(), "Auto s tim alfa kodom ne postoji!", "Neispravan alfa kod", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Generates random alfa-numeric string of 8 characters. Source: "https://www.baeldung.com/java-random-string"
     *
     * @return generatedString
     */
    public String generateCarId() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .toUpperCase();
        return generatedString;
    }

}
