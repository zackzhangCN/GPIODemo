package cn.zack.schedule;

import cn.zack.utils.GPIO_Utils;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zack
 */
@Service
public class CPU_Thermal_Schedule {

    // 控制台输出信息
    private static final Logger logger = LoggerFactory.getLogger(CPU_Thermal_Schedule.class);

    // 获取gpio控制器
    GpioController gpioController = GPIO_Utils.getGpioController();
    // 初始化散热扇为开启状态
    GpioPinDigitalOutput gpio00 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00, "GPIOControl", PinState.LOW);

    static {
        logger.info("初始化CPU散热扇");
    }

    @Value("${cpu.thermal.path}")
    private String cpuThermalPath;

    /**
     * 定时监控CPU温度信息, 每分钟一次, 如果温度高于55°则开启散热扇, 低于50°则关闭散热扇
     */
    @Scheduled(fixedRate = 1000 * 60)
    public void autoControlCPUThermal() {
        logger.info("开始调整CPU散热扇");
        // 读取cpu温度
        Integer cpuThermal = getCpuThermal();

        // 高于55°, 开启散热扇
        if (cpuThermal >= 55) {
            gpioControl(0);
        }

        // 低于50°, 关闭散热扇
        if (cpuThermal <= 50) {
            gpioControl(1);
        }
    }

    /**
     * 获取cpu温度信息
     *
     * @return cpu温度
     */
    public Integer getCpuThermal() {
        Integer thermal = 0;
        try {
            // 读取cpu温度文件
            Path path = Paths.get(cpuThermalPath);
            List<String> list = Files.readAllLines(path);
            String line1 = list.get(0);
            // 取温度数值, 除1000, 得摄氏度值
            thermal = Integer.parseInt(line1) / 1000;
            logger.info("读取CPU温度为: {}, 摄氏度取整: {}°", line1, thermal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thermal;
    }

    /**
     * cpu散热扇开启或关闭
     * 散热扇正极接引脚2(5V)
     * 散热扇负极接引脚11(GPIO_01)
     * 通过控制GPIO_01的高低电平来控制散热扇
     *
     * @param status
     */
    public void gpioControl(Integer status) {
        // 开启或者关闭散热扇
        if (status == 0) {
            gpio00.low();
            logger.info("开启散热扇");
        } else {
            gpio00.blink(1, TimeUnit.MINUTES);
            logger.info("关闭散热扇");
        }
    }
}
