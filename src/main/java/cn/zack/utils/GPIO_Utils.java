package cn.zack.utils;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

/**
 * @author cn.zack
 * GPIO控制器
 */
public class GPIO_Utils {
    /**
     * 创建GPIO控制器
     */
    private static final GpioController gpioController = GpioFactory.getInstance();

    public static GpioController getGpioController() {
        return gpioController;
    }
}
