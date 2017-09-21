/**
 * 
 */
package com.glodon.bim5d.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hemd
 *
 */
@SpringBootApplication
//@EnableEurekaClient //启用服务注册
public class NginxRootApplication {
   
   /**
    * @param args
    */
   public static void main(String[] args) {
      SpringApplication.run(NginxRootApplication.class, args);
   }
   
}
