package com.test.test_demo.designpattern.factorymethod.after;

import java.util.Calendar;

public class BlackshipFactory implements ShipFactory {


    @Override
    public Ship createShip() {
        return new Blackship();
    }


}
