/**
 * 
 */
package com.glodon.bim5d.monitor.util;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author hemd
 * 监控工具类
 */
public class MonitorUtil {
   /**服务器名称*/
   public static String serverName=System.getenv().get("COMPUTERNAME");
   /**服务器ip*/
   public static String ip=null;
   static{
      InetAddress addr=null;
      try {
         addr = InetAddress.getLocalHost();
      } catch (UnknownHostException e) {
      }
      ip = addr.getHostAddress();
   }
   /**
    * 加载sigar jni lib
    * @throws Exception
    */
   public static void loadJniLib()throws Exception{
      try {
         ResourceBundle resourceBundle = ResourceBundle.getBundle("jni-lib-settings");
         String lib=resourceBundle.getString("lib.jni.sigar");
         lib=getPureString(lib);
         if(lib!=null&&new File(lib).isDirectory()){
            String path=System.getProperty("java.library.path");
            if(!path.contains(lib)){
               path+=":"+lib;
               System.setProperty("java.library.path", path);
            }
         }
     } catch (Exception e) {
     }
   }
   /**
    * 从MySQL jdbcURL中解析出ip和dbname
    * @param url
    * @return
    */
   public static String[] parseDbUrl(String url){
      String arr[]=new String[2];
      if(url!=null){
         url=url.trim();
         if(!"".equals(url)){
            int i=url.indexOf('?');
            if(i!=-1)
               url=url.substring(0,i);
            url=url.substring(url.indexOf("//")+2);
            arr[0]=url.substring(0, url.indexOf(':'));
            arr[1]=url.substring(url.lastIndexOf('/')+1);
         }
      }
      return arr;
   }
   /**
    * 减法
    * @param l1
    * @param l2
    * @return
    */
   public static Long subtract(Long l1,Long l2){
      if(l1==null||l2==null)
         return null;
      return l1-l2;
   }
   /**
    * 获取纯净String
    * @param arg0
    * @return
    */
   public static String getPureString(Object arg0){
      String pure=null;
      if(arg0!=null){
         pure=arg0.toString().trim();
         if(pure.equals(""))
            pure=null;
      }
      return pure;
   }
   public static void main(String a[]) {
      String s="jdbc:mysql://mysql-keystone-host:3306/keystone?characterEncoding=UTF-8";
      System.out.println(Arrays.toString(parseDbUrl(s)));
   }
}
