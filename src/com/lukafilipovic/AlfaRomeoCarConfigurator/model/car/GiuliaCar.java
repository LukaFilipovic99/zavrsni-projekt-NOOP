package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

/**
 * Concrete car.
 */
public class GiuliaCar extends CarAbs {
    private final double PRICE = 255550.00;

    public GiuliaCar() {
        price = PRICE;
        description = "model: GIULIA\n";
    }
}
