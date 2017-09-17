package com.glodon.bim5d.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.glodon.bim5d.monitor.gw.MonitorLogFilter;

@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient //启用服务注册
public class GateWayApplication {
   @Bean
   public MonitorLogFilter monitorLogFilter() { 
    return new MonitorLogFilter(); 
   }
	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}
}
