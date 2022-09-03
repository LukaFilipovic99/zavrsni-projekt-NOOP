package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

import java.util.List;

/**
 * Class used to decorate car with list of additional equipment.
 */
public class AdditionalEquipmentDecoratedCar extends CarDecoratorAbs {

    public AdditionalEquipmentDecoratedCar(CarAbs carAbs, List<String> additionalEquipment, double price) {
        this.carAbs = carAbs;
        this.price = carAbs.price + price;
        this.description = carAbs.description + "dodatna oprema: " + additionalEquipment + "\n";
    }
}
