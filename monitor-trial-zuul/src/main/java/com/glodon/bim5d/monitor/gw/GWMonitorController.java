/**
 * 
 */
package com.glodon.bim5d.monitor.gw;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hemd
 *
 */
@Controller
@RequestMapping(value = "/monitor")
public class GWMonitorController {

   @RequestMapping(value = "/gw.info")
   @ResponseBody
   public Object info()throws Exception{
      return "这是网关监控系统";
   }
}
