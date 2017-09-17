/**
 * 
 */
package com.glodon.bim5d.monitor.util;

import com.glodon.bim5d.monitor.enume.MonitorTypeEnum;

/**
 * @author hemd
 * 缓存前一个监控直
 */
public interface IPreviousCacheUtil {
   public  Boolean isEmpty(MonitorTypeEnum enume);
   public  Object getPreviousInfo(MonitorTypeEnum enume);
   public Boolean addCurrentInfo(MonitorTypeEnum enume,Object current);
   public  Object removePreviousInfo(MonitorTypeEnum enume);
}
