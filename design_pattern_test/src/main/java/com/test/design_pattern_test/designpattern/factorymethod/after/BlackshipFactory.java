package com.test.design_pattern_test.designpattern.factorymethod.after;


public class BlackshipFactory extends DefaultShipFactory{
    @Override
    public Ship createShip() {
        return new Blackship();
    }
}
