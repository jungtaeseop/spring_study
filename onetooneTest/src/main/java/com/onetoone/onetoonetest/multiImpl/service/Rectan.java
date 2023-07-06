package com.onetoone.onetoonetest.multiImpl.service;

import org.springframework.stereotype.Service;

@Service
public class Rectan implements DrawingService{
    @Override
    public void draw() {
        System.out.println("Rectan test 1");
    }
}
