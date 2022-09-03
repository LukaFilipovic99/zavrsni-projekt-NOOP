package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

/**
 * Class used to decorate car with seats.
 */
public class SeatsDecoratedCar extends CarDecoratorAbs {

    public SeatsDecoratedCar(CarAbs carAbs, String seats, double price) {
        this.carAbs = carAbs;
        this.price = carAbs.price + price;
        description = carAbs.description + "sjedala: " + seats + "\n";
    }
}
