package com.onetoone.onetoonetest.multiImpl.service;

import org.springframework.stereotype.Service;

@Service
public class Circle implements DrawingService{
    @Override
    public void draw() {
        System.out.println("Circle test 1");

    }
}
