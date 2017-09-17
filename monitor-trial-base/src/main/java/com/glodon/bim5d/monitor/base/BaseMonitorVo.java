/**
 * 
 */
package com.glodon.bim5d.monitor.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hemd
 * 服务器基础性能指标Vo
 */
public class BaseMonitorVo implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 3204884184023467458L;
   
   private String serverName;
   private String ip;
   private Double cpuRatio;
   private Double laodAverage;
   private Double memoryRatio;
   private Double diskRatio;
   private Long diskReads;
   private Long diskWrites;
   private Long receivedBytes;
   private Long sendedBytes;
   private Long processCount;
   private Date createTime;
   public BaseMonitorVo() {
      super();
   }
   public BaseMonitorVo(String serverName, String ip, Double cpuRatio, Double laodAverage, Double memoryRatio,
         Double diskRatio, Long diskReads, Long diskWrites, Long receivedBytes, Long sendedBytes, Long processCount,
         Date createTime) {
      super();
      this.serverName = serverName;
      this.ip = ip;
      this.cpuRatio = cpuRatio;
      this.laodAverage = laodAverage;
      this.memoryRatio = memoryRatio;
      this.diskRatio = diskRatio;
      this.diskReads = diskReads;
      this.diskWrites = diskWrites;
      this.receivedBytes = receivedBytes;
      this.sendedBytes = sendedBytes;
      this.processCount = processCount;
      this.createTime = createTime;
   }
   public String getServerName() {
      return serverName;
   }
   public void setServerName(String serverName) {
      this.serverName = serverName;
   }
   public String getIp() {
      return ip;
   }
   public void setIp(String ip) {
      this.ip = ip;
   }
   public Double getCpuRatio() {
      return cpuRatio;
   }
   public void setCpuRatio(Double cpuRatio) {
      this.cpuRatio = cpuRatio;
   }
   public Double getLaodAverage() {
      return laodAverage;
   }
   public void setLaodAverage(Double laodAverage) {
      this.laodAverage = laodAverage;
   }
   public Double getMemoryRatio() {
      return memoryRatio;
   }
   public void setMemoryRatio(Double memoryRatio) {
      this.memoryRatio = memoryRatio;
   }
   public Double getDiskRatio() {
      return diskRatio;
   }
   public void setDiskRatio(Double diskRatio) {
      this.diskRatio = diskRatio;
   }
   public Long getDiskReads() {
      return diskReads;
   }
   public void setDiskReads(Long diskReads) {
      this.diskReads = diskReads;
   }
   public Long getDiskWrites() {
      return diskWrites;
   }
   public void setDiskWrites(Long diskWrites) {
      this.diskWrites = diskWrites;
   }
   public Long getReceivedBytes() {
      return receivedBytes;
   }
   public void setReceivedBytes(Long receivedBytes) {
      this.receivedBytes = receivedBytes;
   }
   public Long getSendedBytes() {
      return sendedBytes;
   }
   public void setSendedBytes(Long sendedBytes) {
      this.sendedBytes = sendedBytes;
   }
   public Long getProcessCount() {
      return processCount;
   }
   public void setProcessCount(Long processCount) {
      this.processCount = processCount;
   }
   public Date getCreateTime() {
      return createTime;
   }
   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }
   @Override
   public String toString() {
      return "BaseMonitorVo [serverName=" + serverName + ", ip=" + ip + ", cpuRatio=" + cpuRatio + ", laodAverage="
            + laodAverage + ", memoryRatio=" + memoryRatio + ", diskRatio=" + diskRatio + ", diskReads=" + diskReads
            + ", diskWrites=" + diskWrites + ", receivedBytes=" + receivedBytes + ", sendedBytes=" + sendedBytes
            + ", processCount=" + processCount + ", createTime=" + createTime + "]";
   }
}
