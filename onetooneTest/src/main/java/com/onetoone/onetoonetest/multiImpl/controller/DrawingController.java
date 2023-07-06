package com.onetoone.onetoonetest.multiImpl.controller;

import com.onetoone.onetoonetest.multiImpl.service.Circle;
import com.onetoone.onetoonetest.multiImpl.service.DrawingService;
import com.onetoone.onetoonetest.multiImpl.service.Rectan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PropertySource("classpath:url.properties")
@RestController
@RequestMapping("/controller")
public class DrawingController {

    private final DrawingService circle;

    private final DrawingService rectan;

    @Value("${localtion}")
    private String type;

    private DrawingService getType(String type){
        return type.equals("center") ? this.circle : this.rectan;
    }

    public DrawingController(Circle circle, Rectan rectan){
        this.circle = circle;
        this.rectan = rectan;
    }

    @GetMapping("/shapes")
    public void drawShapes() {
        getType(type).draw();
    }
}
