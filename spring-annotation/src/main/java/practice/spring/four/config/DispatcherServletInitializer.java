package practice.spring.four.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author hemd
 *
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

   @Override
   protected Class<?>[] getRootConfigClasses() {
      // TODO Auto-generated method stub
      return new Class<?>[]{APPConfig.class};
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      // TODO Auto-generated method stub
      return new Class<?>[]{WebConfig.class};
   }

   @Override
   protected String[] getServletMappings() {
      // TODO Auto-generated method stub
      return new String[]{"/"};
   }
   
}
