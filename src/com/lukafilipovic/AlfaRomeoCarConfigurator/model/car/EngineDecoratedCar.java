package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

public class EngineDecoratedCar extends CarDecoratorAbs {

    public EngineDecoratedCar(CarAbs carAbs, String engine, double price) {
        this.carAbs = carAbs;
        this.price = carAbs.price + price;
        description = carAbs.description + "motor: " + engine + "\n";
    }

}
