/**
 * 
 */
package practice.spring.four.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author hemd
 *
 */
@Configuration
@ComponentScan( basePackages={"practice.spring.four"}, 
excludeFilters = { @Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)}
)
public class APPConfig implements ApplicationContextAware {

   static ApplicationContext context;

   public static <T> T getBean(Class<T> requiredType) {
       return context.getBean(requiredType);
   }

   public void setApplicationContext(ApplicationContext ctx) throws BeansException {
      context = ctx;
   }
   
}
