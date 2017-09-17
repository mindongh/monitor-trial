/**
 * 
 */
package com.glodon.bim5d.monitor.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.glodon.bim5d.monitor.enume.MonitorTypeEnum;
import com.glodon.bim5d.monitor.util.IPreviousCacheUtil;
import com.glodon.bim5d.monitor.util.MonitorUtil;
import com.glodon.bim5d.monitor.util.SpringContextHolder;

/**
 * @author hemd
 *  收集redis 监控信息
 */
@Component
public class RedisMonitorCollector {
   @Autowired
   @Qualifier("previousLocalCacheUtil")
   IPreviousCacheUtil previousUtil;
   /**
    * 收集redis 监控信息
    * @return
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
   public List<RedisMonitorVo> gatherRedisMonitorInfo() throws Exception{
      Map<String,RedisMonitorVo>info=getRedisMonitorInfo();
      List<RedisMonitorVo>infos=null;
      if(previousUtil.isEmpty(MonitorTypeEnum.redis)){
         previousUtil.addCurrentInfo(MonitorTypeEnum.redis,info);
      }
      Map<String,RedisMonitorVo> previous=(Map<String, RedisMonitorVo>) previousUtil.getPreviousInfo(MonitorTypeEnum.redis);
      Map<String,RedisMonitorVo>current=(Map<String, RedisMonitorVo>) BeanUtils.cloneBean(info);
      previousUtil.removePreviousInfo(MonitorTypeEnum.redis);
      previousUtil.addCurrentInfo(MonitorTypeEnum.redis,current);
      RedisMonitorVo pt=null;
      for(Map.Entry<String,RedisMonitorVo>en:info.entrySet()){
         pt=previous.get(en.getKey());
         en.getValue().setCmdCount(MonitorUtil.subtract(en.getValue().getCmdCount(),pt.getCmdCount()));
      }
      infos=new ArrayList<RedisMonitorVo>(info.values());
      return infos;
   }
   @SuppressWarnings("rawtypes")
   public Map<String,RedisMonitorVo> getRedisMonitorInfo() throws Exception{
      Map<String,RedisMonitorVo>infos=new HashMap<String,RedisMonitorVo>();
      Map<String,RedisTemplate> map=SpringContextHolder.getBeansOfType(RedisTemplate.class);
      RedisTemplate<?, ?> rt=null;
      for(Map.Entry<String,RedisTemplate>en:map.entrySet()){
         final RedisMonitorVo info=new RedisMonitorVo();
         rt=en.getValue();
         infos.put(en.getKey(),info);
         rt.execute(new RedisCallback<Object>(){
            @Override
            public Object doInRedis(RedisConnection con) throws DataAccessException {
               Long ddbSize=con.dbSize();
               Properties prop=con.info();
               String connectionCountStr=MonitorUtil.getPureString(prop.getProperty("connected_clients"));
               String cmdCountStr=MonitorUtil.getPureString(prop.getProperty("total_commands_processed"));
               String memoryUsageStr=MonitorUtil.getPureString(prop.getProperty("used_memory"));
               info.setKeyCount(ddbSize);
               info.setConnectionCount(connectionCountStr==null?null:Integer.valueOf(connectionCountStr));
               info.setCmdCount(cmdCountStr==null?null:Long.valueOf(cmdCountStr));
               info.setMemoryUsage(memoryUsageStr==null?null:Double.valueOf(memoryUsageStr));
               return null;
            }});
         
      }
   
      return infos;
   }
}
