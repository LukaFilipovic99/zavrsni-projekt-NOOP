package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

/**
 * Concrete car.
 */
public class StelvioCar extends CarAbs {
    private final double PRICE = 318250.00;

    public StelvioCar() {
        price = PRICE;
        description = "model: STELVIO\n";
    }
}
