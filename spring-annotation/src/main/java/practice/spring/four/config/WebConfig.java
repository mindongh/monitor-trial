/**
 * 
 */
package practice.spring.four.config;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author hemd
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan({"practice.spring.four.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

   @Override
   public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
      configurer.defaultContentType(MediaType.APPLICATION_JSON);
   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
   }

   @Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
   }

   InternalResourceViewResolver internalResourceViewResolver() {
      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      viewResolver.setViewClass(JstlView.class);
      viewResolver.setPrefix("/WEB-INF/views/");
      viewResolver.setSuffix(".jsp");

      return viewResolver;
   }

   @Bean
   ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager contentNegotiationManager) {
      ArrayList<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
      viewResolvers.add(this.internalResourceViewResolver());

      ArrayList<View> defaultViews = new ArrayList<View>();
      defaultViews.add(new MappingJackson2JsonView());

      ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
      viewResolver.setContentNegotiationManager(contentNegotiationManager);
      viewResolver.setViewResolvers(viewResolvers);
      viewResolver.setDefaultViews(defaultViews);

      return viewResolver;
   }

   @Bean
   CommonsMultipartResolver multipartResolver() {
      return new CommonsMultipartResolver();
   }

   @Bean
   HandlerExceptionResolver exceptionResolver() {
      return new HandlerExceptionResolver(){

         public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
               Exception arg3) {
            return null;
         }
         
      };
   }

   
}
