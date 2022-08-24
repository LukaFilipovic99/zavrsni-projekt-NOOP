package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

public class WheelsDecoratedCar extends CarDecoratorAbs {

    public WheelsDecoratedCar(CarAbs carAbs, String wheels, double price) {
        this.carAbs = carAbs;
        this.price = carAbs.price + price;
        description = carAbs.description + "kotači: " + wheels + "\n";
    }
}
