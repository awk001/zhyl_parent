package cn.code.awk.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author anwenkang
 * @version 1.0.0
 * @ClassName HospServiceApplication.java
 * @Description  启动类
 * @createTime 2021年09月11日 15:32:00
 */
@SpringBootApplication()
@ComponentScan(basePackages = "cn.code.awk")
public class HospServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospServiceApplication.class, args);
    }
}