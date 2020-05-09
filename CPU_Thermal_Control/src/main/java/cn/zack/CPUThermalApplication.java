package cn.zack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zack
 */
@EnableScheduling
@SpringBootApplication
public class CPUThermalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CPUThermalApplication.class, args);
        System.out.println("CPU温度调节系统启动完成...");
    }
}
