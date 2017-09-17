/**
 * 
 */
package com.glodon.bim5d.monitor.nginx;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.glodon.bim5d.monitor.nginx.config.MonitorNginx;
import com.glodon.bim5d.monitor.nginx.config.MonitorNginxList;
import com.glodon.bim5d.monitor.util.JaxbReadXml;
import com.glodon.bim5d.monitor.util.MonitorUtil;

/**
 * @author hemd
 * 用于收集Nginx监控信息
 */
public class NginxMonitorCollector {
   private static List<MonitorNginx>nginxList;
   private static String parseUrl(MonitorNginx nginx){
      StringBuilder url=new StringBuilder(nginx.getProtocol());
      String domain=MonitorUtil.getPureString(nginx.getServerDomain());
      url.append("://").append(domain==null?nginx.getServerIp():domain);
      url.append("/").append(nginx.getAppName());
      return url.toString();
   }
   public static List<NginxMonitorVo>gatherNginxMonitorInfo()throws Exception{
      if(nginxList==null)
      {
         MonitorNginxList nginxs = JaxbReadXml.readString(MonitorNginxList.class, "monitor.nginx.list.xml");
         nginxList=nginxs.getMonitorNginxList();
      }
      List<NginxMonitorVo>infos=new ArrayList<NginxMonitorVo>(nginxList.size());
      NginxMonitorVo info=null;
      Document doc=null;
      String activeConnections=null;
      String accepts=null;                  
      String handled=null;                  
      String requests=null;                
      String reading=null;                  
      String writing=null;                   
      String waiting=null;                   
      for(MonitorNginx nginx:nginxList){
         doc =  Jsoup.connect(parseUrl(nginx)).get();
         String arr[]=doc.getElementsByTag("body").text().split(" ");
         info=new NginxMonitorVo();
         infos.add(info);
         info.setIp(nginx.getServerIp());
         activeConnections=MonitorUtil.getPureString(arr[2]);
         accepts=MonitorUtil.getPureString(arr[7]);
         handled=MonitorUtil.getPureString(arr[8]);
         requests=MonitorUtil.getPureString(arr[9]);
         reading=MonitorUtil.getPureString(arr[11]);
         writing=MonitorUtil.getPureString(arr[13]);
         waiting=MonitorUtil.getPureString(arr[15]);
         info.setActiveConnections(activeConnections==null?info.getActiveConnections():Long.valueOf(activeConnections));
         info.setAccepts(accepts==null?info.getAccepts():Long.valueOf(accepts));
         info.setHandled(handled==null?info.getHandled():Long.valueOf(handled));
         info.setRequest(requests==null?info.getRequest():Long.valueOf(requests));
         info.setReading(reading==null?info.getReading():Long.valueOf(reading));
         info.setWriting(writing==null?info.getWriting():Long.valueOf(writing));
         info.setWaiting(waiting==null?info.getWaiting():Long.valueOf(waiting));
         info.setClosedConnections(MonitorUtil.subtract(info.getAccepts(),info.getHandled()));
      }
      return infos;
   }
}
