/**
 * 
 */
package com.glodon.bim5d.monitor.redis;

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
public class RedisMonitorController {
   @Autowired
   RedisMonitorCollector collector;

   @RequestMapping(value = "/redis")
   @ResponseBody
   public Object monitor()throws Exception{
      return collector.gatherRedisMonitorInfo();
   }
   @RequestMapping(value = "/redis.info")
   @ResponseBody
   public Object info()throws Exception{
      return "这是redis监控系统";
   }
}
