package com.test.test_demo.designpattern.factorymethod.after;

public class Client {

    public static void main(String[] args) {
        Ship whiteship = new WhiteshipFactory().orderShip("Whiteship", "keesun@mail.com");
        System.out.println(whiteship);


        Ship blackship = new BlackshipFactory().orderShip("Blacksip","abc@gmail.com");
        System.out.println(blackship);
    }
}
