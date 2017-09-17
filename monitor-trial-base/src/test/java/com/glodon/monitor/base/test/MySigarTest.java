/**
 * 
 */
package com.glodon.monitor.base.test;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.Sigar;

import com.glodon.bim5d.monitor.util.MonitorUtil;

/**
 * @author hemd
 *
 */
public class MySigarTest {

   static{
      try {
         MonitorUtil.loadJniLib();  //加载sigar库
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /**
    * @param args
    */
   public static void main(String[] args) throws Exception {
      //系统负载 
      Sigar sigar = new Sigar();
      try{
         double load[]=sigar.getLoadAverage();
         System.out.println(load);
      }catch(Exception e){
      }
      System.out.println( sigar.getProcList().length);
      System.out.println("=================================");
   }
   
}
