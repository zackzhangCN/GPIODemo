package cn.zack.service;

import cn.zack.utils.GPIO_Utils;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.stereotype.Service;

/**
 * @author GPIO控制逻辑
 */
@Service
public class GPIO_Service {
    /**
     * 获取一个gpio控制器
     */
    final GpioController gpioController = GPIO_Utils.getGpioController();

    /**
     * 获取7,8引脚, 并设置初始状态为低电平
     */
    final GpioPinDigitalOutput gpio_07 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_07, "fun", PinState.LOW);
    final GpioPinDigitalOutput gpio_08 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_08, "fun", PinState.LOW);

    /**
     * 设置gpio_07引脚为高电平或低电平
     */
    public String turnFun(Integer status) {
        // led点亮, 7引脚改为高电平状态
        if (status == 1) {
            gpio_07.high();
            return "turn on ok !!!";
        } else if (status == 2) {
            // led熄灭, 7引脚改为低电平状态
            gpio_07.low();
            return "turn off ok !!!";
        } else {
            return "status error !!!";
        }
    }

}
