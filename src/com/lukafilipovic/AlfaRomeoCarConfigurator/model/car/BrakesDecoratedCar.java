package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

public class BrakesDecoratedCar extends CarDecoratorAbs {

    public BrakesDecoratedCar(CarAbs carAbs, String brakes, double price) {
        this.carAbs = carAbs;
        this.price = carAbs.price + price;
        description = carAbs.description + "kočiona kliješta: " + brakes + "\n";
    }
}
