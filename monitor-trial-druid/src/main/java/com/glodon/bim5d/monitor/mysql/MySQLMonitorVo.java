/**
 * 
 */
package com.glodon.bim5d.monitor.mysql;

import java.io.Serializable;

/**
 * @author hemd
 * MySQL数据库监控指标Vo
 */
public class MySQLMonitorVo implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = -833476031703381694L;
   private String ip;
   private String dbName;
   private Integer connectionCount;
   private Integer activeConnectionCount;
   private Integer dbCount;
   public MySQLMonitorVo() {
      super();
   }
   public MySQLMonitorVo(String ip, String dbName, Integer connectionCount, Integer activeConnectionCount,
         Integer dbCount) {
      super();
      this.ip = ip;
      this.dbName = dbName;
      this.connectionCount = connectionCount;
      this.activeConnectionCount = activeConnectionCount;
      this.dbCount = dbCount;
   }
   public String getIp() {
      return ip;
   }
   public void setIp(String ip) {
      this.ip = ip;
   }
   public String getDbName() {
      return dbName;
   }
   public void setDbName(String dbName) {
      this.dbName = dbName;
   }
   public Integer getConnectionCount() {
      return connectionCount;
   }
   public void setConnectionCount(Integer connectionCount) {
      this.connectionCount = connectionCount;
   }
   public Integer getActiveConnectionCount() {
      return activeConnectionCount;
   }
   public void setActiveConnectionCount(Integer activeConnectionCount) {
      this.activeConnectionCount = activeConnectionCount;
   }
   public Integer getDbCount() {
      return dbCount;
   }
   public void setDbCount(Integer dbCount) {
      this.dbCount = dbCount;
   }
   @Override
   public String toString() {
      return "MySQLMonitorVo [ip=" + ip + ", dbName=" + dbName + ", connectionCount=" + connectionCount
            + ", activeConnectionCount=" + activeConnectionCount + ", dbCount=" + dbCount + "]";
   }
   
}
