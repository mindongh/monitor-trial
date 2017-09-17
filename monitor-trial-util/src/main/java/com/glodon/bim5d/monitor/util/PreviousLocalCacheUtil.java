/**
 * 
 */
package com.glodon.bim5d.monitor.util;

import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Component;

import com.glodon.bim5d.monitor.enume.MonitorTypeEnum;

/**
 * @author hemd
 *
 */
@Component("previousLocalCacheUtil")
public class PreviousLocalCacheUtil implements IPreviousCacheUtil {
   private static List<Object>previousBaseInfo=new Vector<Object>(1);
   private static List<Object>previousRedisInfo=new Vector<Object>(1);
   @Override
   public Boolean isEmpty(MonitorTypeEnum enume){
      if(enume.equals(MonitorTypeEnum.base)){
         return previousBaseInfo.isEmpty();
      }
      if(enume.equals(MonitorTypeEnum.redis)){
         return previousRedisInfo.isEmpty();
      }
      return Boolean.FALSE;
   }
   @Override
   public Object getPreviousInfo(MonitorTypeEnum enume){
      if(enume.equals(MonitorTypeEnum.base)){
         return previousBaseInfo.iterator().next();
      }
      if(enume.equals(MonitorTypeEnum.redis)){
         return previousRedisInfo.iterator().next();
      }
      return null;
   }
   @Override
   public Boolean addCurrentInfo(MonitorTypeEnum enume,Object current){
      if(enume.equals(MonitorTypeEnum.base)){
         return previousBaseInfo.add(current);
      }
      if(enume.equals(MonitorTypeEnum.redis)){
         return previousRedisInfo.add(current);
      }
      return Boolean.FALSE;
   }
   @Override
   public Object removePreviousInfo(MonitorTypeEnum enume){
      if(enume.equals(MonitorTypeEnum.base)){
         return previousBaseInfo.remove(0);
      }
      if(enume.equals(MonitorTypeEnum.redis)){
         return previousRedisInfo.remove(0);
      }
      return null;
   }
}
