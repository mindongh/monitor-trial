/**
 * 
 */
package com.glodon.bim5d.monitor.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hemd
 *
 */
@Controller
@RequestMapping("/monitor")
public class MySQLMonitorController {
   @Autowired
   MySQLMonitorCollector collector;
   @RequestMapping(value = "/mysql")
   @ResponseBody
   public Object monitor()throws Exception{
      return collector.gatherMySQLMonitorInfo();
   }
   @RequestMapping(value = "/mysql.info")
   @ResponseBody
   public Object info()throws Exception{
      return "这是mysql监控系统";
   }
}
