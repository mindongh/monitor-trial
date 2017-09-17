/**
 * 
 */
package com.glodon.bim5d.monitor.nginx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hemd
 *
 */
@Controller
@RequestMapping("/monitor")
public class NginxMonitorController {

   @RequestMapping(value = "/nginx")
   @ResponseBody
   public Object monitor()throws Exception{
      return NginxMonitorCollector.gatherNginxMonitorInfo();
   }
   @RequestMapping(value = "/nginx.info")
   @ResponseBody
   public Object info()throws Exception{
      return "这是nginx监控系统";
   }
}
