/**
 * 
 */
package com.glodon.bim5d.monitor.nginx.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hemd
 *
 */
@XmlRootElement(name = "monitor-nginx-list")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonitorNginxList extends ArrayList<MonitorNginx> {

   @XmlAttribute(name = "size")
   private Integer size;
   /**
    * 
    */
   private static final long serialVersionUID = 3731199512921817402L;
   @XmlElement(name = "monitor-nginx")
   public List<MonitorNginx>getMonitorNginxList(){
      return this;
   }
   public Integer getSize() {
      return size;
   }
   public void setSize(Integer size) {
      this.size = size;
   }
}
