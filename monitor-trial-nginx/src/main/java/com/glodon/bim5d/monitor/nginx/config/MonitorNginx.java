/**
 * 
 */
package com.glodon.bim5d.monitor.nginx.config;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author hemd
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MonitorNginx implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = -8317591351503236817L;
   @XmlElement(name = "server-ip")
   private String serverIp;
   @XmlElement(name = "server-name")
   private String serverName;
   @XmlElement(name = "server-domain")
   private String serverDomain;
   @XmlElement(name = "app-name")
   private String appName;
   @XmlElement(name = "server-port")
   private Integer serverPort;
   @XmlElement(name = "protocol")
   private String protocol;
   @XmlElement(name = "remark")
   private String remark;
   public String getServerIp() {
      return serverIp;
   }
   public void setServerIp(String serverIp) {
      this.serverIp = serverIp;
   }
   public String getServerName() {
      return serverName;
   }
   public void setServerName(String serverName) {
      this.serverName = serverName;
   }
   public String getServerDomain() {
      return serverDomain;
   }
   public void setServerDomain(String serverDomain) {
      this.serverDomain = serverDomain;
   }
   public String getAppName() {
      return appName;
   }
   public void setAppName(String appName) {
      this.appName = appName;
   }
   public Integer getServerPort() {
      return serverPort;
   }
   public void setServerPort(Integer serverPort) {
      this.serverPort = serverPort;
   }
   public String getProtocol() {
      return protocol;
   }
   public void setProtocol(String protocol) {
      this.protocol = protocol;
   }
   public String getRemark() {
      return remark;
   }
   public void setRemark(String remark) {
      this.remark = remark;
   }
   @Override
   public String toString() {
      return "MonitorNginx [serverIp=" + serverIp + ", serverName=" + serverName + ", serverDomain=" + serverDomain
            + ", appName=" + appName + ", serverPort=" + serverPort + ", protocol=" + protocol + ", remark=" + remark
            + "]";
   }
   
}
