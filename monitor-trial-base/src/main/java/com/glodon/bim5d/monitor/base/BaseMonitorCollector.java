/**
 * 
 */
package com.glodon.bim5d.monitor.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.glodon.bim5d.monitor.enume.MonitorTypeEnum;
import com.glodon.bim5d.monitor.util.IPreviousCacheUtil;
import com.glodon.bim5d.monitor.util.MonitorUtil;

/**
 * @author hemd
 * 收集服务器基础性能信息
 */
@Component
public class BaseMonitorCollector {
   public static void main(String a[])throws Exception{
      BaseMonitorVo info=getBaseMonitorInfo();
      System.out.println(info);
   }
   static{
      try {
         MonitorUtil.loadJniLib();  //加载sigar库
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   @Autowired
   @Qualifier("previousLocalCacheUtil")
   IPreviousCacheUtil previousUtil;
   /**
    * 收集服务器基础性能信息
    * @return
    * @throws Exception
    */
   public  BaseMonitorVo gatherBaseMonitorInfo() throws Exception{
      BaseMonitorVo info=getBaseMonitorInfo();
      if(previousUtil.isEmpty(MonitorTypeEnum.base)){
         previousUtil.addCurrentInfo(MonitorTypeEnum.base, info);
      }
      BaseMonitorVo previous=(BaseMonitorVo) previousUtil.getPreviousInfo(MonitorTypeEnum.base);
      BaseMonitorVo current=(BaseMonitorVo) BeanUtils.cloneBean(info);
      previousUtil.removePreviousInfo(MonitorTypeEnum.base);
      previousUtil.addCurrentInfo(MonitorTypeEnum.base, current);
      info.setDiskReads(MonitorUtil.subtract(info.getDiskReads(),previous.getDiskReads()));
      info.setDiskWrites(MonitorUtil.subtract(info.getDiskWrites(),previous.getDiskWrites()));
      info.setReceivedBytes(MonitorUtil.subtract(info.getReceivedBytes(),previous.getReceivedBytes()));
      info.setSendedBytes(MonitorUtil.subtract(info.getSendedBytes(),previous.getSendedBytes()));
      return info;
   }
   private static BaseMonitorVo getBaseMonitorInfo() throws Exception{
      BaseMonitorVo info=new BaseMonitorVo();
      info.setServerName(MonitorUtil.serverName);
      info.setIp(MonitorUtil.ip);
      Sigar sigar = new Sigar();
      info.setCpuRatio(sigar.getCpuPerc().getCombined());
      getLoad(sigar,info);
      info.setMemoryRatio(sigar.getMem().getUsedPercent());
      getDisk(sigar,info);
      getNet(sigar,info);
      getProc(sigar,info);
      info.setCreateTime(new Date());
      return info;
   }
   private static void getLoad(Sigar sigar,BaseMonitorVo info){
      try{
         double[] load=sigar.getLoadAverage();
         info.setLaodAverage(calcAvg(load));
      }catch(Exception e){}
   }
   private static void getDisk(Sigar sigar,BaseMonitorVo info)throws Exception{
      FileSystem fslist[] = sigar.getFileSystemList();
      if(!(fslist==null||fslist.length==0)){
         FileSystemUsage usage = null;
         List<Long>total=new ArrayList<Long>();
         List<Long>used=new ArrayList<Long>();
         List<Long>reads=new ArrayList<Long>();
         List<Long>writes=new ArrayList<Long>();
         for(FileSystem fs:fslist){
            if(fs.getType()==2){
               usage = sigar.getFileSystemUsage(fs.getDirName());
               total.add(usage.getTotal());
               used.add(usage.getUsed());
               reads.add(usage.getDiskReads());
               writes.add(usage.getDiskWrites());
            }
         }
         Long st=sum(total.toArray(new Long[]{}));
         Long su=sum(used.toArray(new Long[]{}));
         Long sr=sum(reads.toArray(new Long[]{}));
         Long sw=sum(writes.toArray(new Long[]{}));
         Double bl=st==null||su==null?null:(double)su/st;
         info.setDiskRatio(bl);
         info.setDiskReads(sr);
         info.setDiskWrites(sw);
      }
   }
   private static void getNet(Sigar sigar,BaseMonitorVo info)throws Exception{
      String ifNames[] = sigar.getNetInterfaceList();
      List<Long>receives=new ArrayList<Long>();
      List<Long>sends=new ArrayList<Long>();
      if(ifNames!=null&&ifNames.length>0){
         for (int i = 0; ifNames!=null&&i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
            receives.add(ifstat.getRxBytes());
            sends.add(ifstat.getTxBytes());
         }
         Long sr=sum(receives.toArray(new Long[]{}));
         Long ss=sum(sends.toArray(new Long[]{}));
         info.setReceivedBytes(sr);
         info.setSendedBytes(ss);
      }
   }
   private static void getProc(Sigar sigar,BaseMonitorVo info)throws Exception{
      long pl[]=sigar.getProcList();
      if(pl!=null)
         info.setProcessCount(new Long(pl.length));
   }
   private static Double calcAvg(double...source){
      Double avg=null;
      if(source.length>0){
         avg=0d;
         for(double d:source){
            avg+=d;
         }
         avg=avg/source.length;
      }
      return avg;
   }
   private static Long sum(Long...longs){
      if(longs.length>0){
         if(longs.length>1){
            for(int i=1;i<longs.length;++i){
               longs[0]+=longs[i];
            }
         }
         return longs[0];
      }
      return null;
   }
}
