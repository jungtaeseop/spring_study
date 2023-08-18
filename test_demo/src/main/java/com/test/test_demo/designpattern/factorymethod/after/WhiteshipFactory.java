package com.test.test_demo.designpattern.factorymethod.after;

public class WhiteshipFactory extends DefaultShipFactory{
    @Override
    public Ship createShip() {
        return new Whiteship();
    }

}
