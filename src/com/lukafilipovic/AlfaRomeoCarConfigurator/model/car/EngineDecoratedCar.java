package com.lukafilipovic.AlfaRomeoCarConfigurator.model.car;

public class EngineDecoratedCar extends CarDecoratorAbs{
    private String engine;
    private Double price;

    public EngineDecoratedCar(CarAbs carAbs, String engine, Double price){
        this.carAbs=carAbs;
        this.engine=engine;
        this.cost=cost+price;
        description="engine: "+engine+"\n";
    }

}
