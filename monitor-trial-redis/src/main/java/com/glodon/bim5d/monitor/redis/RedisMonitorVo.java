/**
 * 
 */
package com.glodon.bim5d.monitor.redis;

import java.io.Serializable;

/**
 * @author hemd
 * Redis监控指标Vo
 */
public class RedisMonitorVo implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -162122484150615476L;
   private Integer connectionCount;
   private Long keyCount;
   private Long cmdCount;
   private Double memoryUsage;
   public RedisMonitorVo() {
      super();
   }
   public RedisMonitorVo(Integer connectionCount, Long keyCount, Long cmdCount, Double memoryUsage) {
      super();
      this.connectionCount = connectionCount;
      this.keyCount = keyCount;
      this.cmdCount = cmdCount;
      this.memoryUsage = memoryUsage;
   }
   public Integer getConnectionCount() {
      return connectionCount;
   }
   public void setConnectionCount(Integer connectionCount) {
      this.connectionCount = connectionCount;
   }
   public Long getKeyCount() {
      return keyCount;
   }
   public void setKeyCount(Long keyCount) {
      this.keyCount = keyCount;
   }
   public Long getCmdCount() {
      return cmdCount;
   }
   public void setCmdCount(Long cmdCount) {
      this.cmdCount = cmdCount;
   }
   public Double getMemoryUsage() {
      return memoryUsage;
   }
   public void setMemoryUsage(Double memoryUsage) {
      this.memoryUsage = memoryUsage;
   }
   @Override
   public String toString() {
      return "RedisMonitorVo [connectionCount=" + connectionCount + ", keyCount=" + keyCount + ", cmdCount=" + cmdCount
            + ", memoryUsage=" + memoryUsage + "]";
   }
}
