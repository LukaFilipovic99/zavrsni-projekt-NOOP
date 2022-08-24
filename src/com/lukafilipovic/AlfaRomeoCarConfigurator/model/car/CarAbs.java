package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CarAbs {
    protected int id;
    protected double price;
    protected String description;

}
