package com.intecsec.java.springboot.simple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/press")
public class PressController {

    @GetMapping("/10")
    public Object t10() throws InterruptedException {
        Thread.sleep(10);
        return "200";
    }

    @GetMapping("/20")
    public Object t20() throws InterruptedException {
        Thread.sleep(20);
        return "200";
    }

    @GetMapping("/50")
    public Object t50() throws InterruptedException {
        Thread.sleep(50);
        return "200";
    }

    @GetMapping("/100")
    public Object t100() throws InterruptedException {
        Thread.sleep(100);
        return "200";
    }

    @GetMapping("/150")
    public Object t150() throws InterruptedException {
        Thread.sleep(150);
        return "200";
    }

    @GetMapping("/200")
    public Object t200() throws InterruptedException {
        Thread.sleep(200);
        return "200";
    }

    @GetMapping("/500")
    public Object t500() throws InterruptedException {
        Thread.sleep(500);
        return "200";
    }

    @GetMapping("/1000")
    public Object t1000() throws InterruptedException {
        Thread.sleep(1000);
        return "200";
    }

    @GetMapping("/2000")
    public Object t2000() throws InterruptedException {
        Thread.sleep(2000);
        return "200";
    }
}