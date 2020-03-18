package com.order.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/***
 * @Description: 分布式商城系统-订单系统： 启动类
 * @author: pikav
 * @date: 2020-3-15
 */

@SpringBootApplication
@ImportResource(value = {"classpath:spring/spring-jdbc.xml", "classpath:spring/spring-dubbo-dev.xml"})
@MapperScan(basePackages = "com.order.model.mapper")
public class BootAppliction extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootAppliction.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootAppliction.class, args);
    }

}
