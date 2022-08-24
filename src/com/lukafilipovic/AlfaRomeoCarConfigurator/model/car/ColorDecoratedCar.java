package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

public class ColorDecoratedCar extends CarDecoratorAbs {


    public ColorDecoratedCar(CarAbs carAbs, String color, double price) {
        this.carAbs = carAbs;
        this.price = carAbs.price + price;
        description = carAbs.description + "boja: " + color + "\n";
    }
}
