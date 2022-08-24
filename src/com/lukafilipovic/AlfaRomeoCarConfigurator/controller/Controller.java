package com.lukafilipovic.AlfaRomeoCarConfigurator.controller;

import com.lukafilipovic.AlfaRomeoCarConfigurator.model.Database.Database;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.car.*;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car.EquipmentFrame;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.common.NavPanel;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car.FinishConfigurationFrame;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.sql.SQLException;

import java.util.List;
import java.util.Random;

/**
 * User controller.
 */

@Getter
@Setter
public class Controller {
    private final Database database;
    private NavPanel navPanel;
    private User user;
    private CarAbs car;

    public Controller() {
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

    public void setPriceToEquipmentFramePricePanel(EquipmentFrame frame, double price) {
        frame.getPricePanel().getPriceLbl().setText(String.valueOf(price) + " kn");
    }

    public EngineDecoratedCar addEngineToCar(CarAbs car, String engine, double price) {
        return new EngineDecoratedCar(car, engine, price);
    }

    public ColorDecoratedCar addColorToCar(CarAbs car, String color, double price) {
        return new ColorDecoratedCar(car, color, price);
    }

    public WheelsDecoratedCar addWheelsToCar(CarAbs car, String wheels, double price) {
        return new WheelsDecoratedCar(car, wheels, price);
    }

    public BrakesDecoratedCar addBrakesToCar(CarAbs car, String brakes, double price) {
        return new BrakesDecoratedCar(car, brakes, price);
    }

    public SeatsDecoratedCar addSeatsToCar(CarAbs car, String seats, double price) {
        return new SeatsDecoratedCar(car, seats, price);
    }

    public AdditionalEquipmentDecoratedCar addEquipmentToCar(CarAbs car, List<String> equipmentList, double price) {
        return new AdditionalEquipmentDecoratedCar(car, equipmentList, price);
    }

    /**
     * Saves configured car to the database
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

    public void setAlfaCodeToFinishConfigurationFrame(FinishConfigurationFrame frame, String alfaCode){
        frame.getAlfaCode().setText(alfaCode);
    }

    /**
     * Generates random alfa-numeric string of 8 characters. Source: "https://www.baeldung.com/java-random-string"
     *
     * @return generatedString
     */
    public String generateCarId() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .toUpperCase();
        System.out.println(generatedString);
        return generatedString;
    }

}
