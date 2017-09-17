/**
 * 
 */
package com.glodon.bim5d.monitor.nginx;

import java.io.Serializable;

/**
 * @author hemd
 * Nginx监控指标Vo
 */
public class NginxMonitorVo implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = -6390978963684773822L;
   private String ip;
   private Long accepts;
   private Long activeConnections;
   private Long closedConnections;
   private Long handled;
   private Long request;
   private Long reading;
   private Long writing;
   private Long waiting;
   public NginxMonitorVo() {
      super();
   }
   public NginxMonitorVo(String ip,Long accepts, Long activeConnections, Long closedConnections, Long handled, Long request,
         Long reading, Long writing, Long waiting) {
      super();
      this.ip = ip;
      this.accepts = accepts;
      this.activeConnections = activeConnections;
      this.closedConnections = closedConnections;
      this.handled = handled;
      this.request = request;
      this.reading = reading;
      this.writing = writing;
      this.waiting = waiting;
   }
   public String getIp() {
      return ip;
   }
   public void setIp(String ip) {
      this.ip = ip;
   }
   public Long getAccepts() {
      return accepts;
   }
   public void setAccepts(Long accepts) {
      this.accepts = accepts;
   }
   public Long getActiveConnections() {
      return activeConnections;
   }
   public void setActiveConnections(Long activeConnections) {
      this.activeConnections = activeConnections;
   }
   public Long getClosedConnections() {
      return closedConnections;
   }
   public void setClosedConnections(Long closedConnections) {
      this.closedConnections = closedConnections;
   }
   public Long getHandled() {
      return handled;
   }
   public void setHandled(Long handled) {
      this.handled = handled;
   }
   public Long getRequest() {
      return request;
   }
   public void setRequest(Long request) {
      this.request = request;
   }
   public Long getReading() {
      return reading;
   }
   public void setReading(Long reading) {
      this.reading = reading;
   }
   public Long getWriting() {
      return writing;
   }
   public void setWriting(Long writing) {
      this.writing = writing;
   }
   public Long getWaiting() {
      return waiting;
   }
   public void setWaiting(Long waiting) {
      this.waiting = waiting;
   }
   @Override
   public String toString() {
      return "NginxMonitorVo [ip=" + ip + ", accepts=" + accepts + ", activeConnections=" + activeConnections + ", closedConnections="
            + closedConnections + ", handled=" + handled + ", request=" + request + ", reading=" + reading
            + ", writing=" + writing + ", waiting=" + waiting + "]";
   }
}
