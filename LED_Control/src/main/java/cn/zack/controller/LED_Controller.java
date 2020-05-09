package cn.zack.controller;

import cn.zack.service.LED_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cn.zack
 * GPIO控制入口
 */
@RestController
@RequestMapping(path = "led")
public class LED_Controller {

    @Autowired
    private LED_Service LED_service;

    @GetMapping(path = "on")
    public String turnOn() {
        String message = LED_service.turnFun(1);
        return message;
    }

    @GetMapping(path = "off")
    public String turnOff() {
        String message = LED_service.turnFun(2);
        return message;
    }
}
