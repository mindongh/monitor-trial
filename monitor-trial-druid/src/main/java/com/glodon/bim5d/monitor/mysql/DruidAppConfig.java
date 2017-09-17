/**
 * 
 */
package com.glodon.bim5d.monitor.mysql;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;

import com.glodon.bim5d.monitor.util.SpringContextHolder;

/**
 * @author hemd
 *
 */
@Configuration
@ImportResource({"classpath:applicationContext.xml"})
@ComponentScan({"com.glodon"})
public class DruidAppConfig implements ApplicationContextAware {
   static ApplicationContext context;

   public static <T> T getBean(Class<T> requiredType) {
       return context.getBean(requiredType);
   }

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       context = applicationContext;
   }
   @Bean
   @Lazy(false)
   public SpringContextHolder SpringContextHolder(){
      return new SpringContextHolder();
   }
}
