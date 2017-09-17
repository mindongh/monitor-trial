/**
 * 
 */
package com.glodon.bim5d.monitor.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hemd
 *
 */
@Controller
@RequestMapping(value = "/monitor")
public class BaseMonitorController {
   @Autowired
   BaseMonitorCollector collector;
   @RequestMapping(value = "/base")
   @ResponseBody
   public Object monitor()throws Exception{
      return collector.gatherBaseMonitorInfo();
   }
   @RequestMapping(value = "/base.info")
   @ResponseBody
   public Object info()throws Exception{
      return "这是服务器基础性能监控系统";
   }
   
}
