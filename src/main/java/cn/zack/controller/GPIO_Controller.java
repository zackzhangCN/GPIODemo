package cn.zack.controller;

import cn.zack.service.GPIO_Service;
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
public class GPIO_Controller {

    @Autowired
    private GPIO_Service gpio_service;

    @GetMapping(path = "on")
    public String turnOn() {
        String message = gpio_service.turnFun(1);
        return message;
    }

    @GetMapping(path = "off")
    public String turnOff() {
        String message = gpio_service.turnFun(2);
        return message;
    }
}
