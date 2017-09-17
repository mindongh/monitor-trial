/**
 * 
 */
package com.glodon.monitor.base.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;

import com.glodon.bim5d.monitor.util.MonitorUtil;

public class SigarTest {
   static{
      try {
         MonitorUtil.loadJniLib();  //加载sigar库
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /**
    * 按照目录加載jni类库
    * 
    * @param folders
    * @throws Exception
    */
   public synchronized static void loadJniLibByDir(String... folders) throws Exception {
      if (folders.length == 0)
         folders = new String[] { "lib-jni" };
      File folder = null;
      int i = 0;
      do {
         folder = new File(folders[i]);
         if (!folder.exists()) {
            folder = new File(Thread.currentThread().getContextClassLoader().getResource(folders[i]).getFile());
         }
         if (!folder.exists()) {
            break;
         }
         if (!folder.isDirectory())
            if (folder.isFile())
               loadJniLibByFile(folder);
         if (folder.exists() && folder.isDirectory()) {
            Object files[] = folder.listFiles(new FileFilter() {
               @Override
               public boolean accept(File file) {
                  String sysType = System.getProperty("os.name");
                  sysType = sysType.toLowerCase();
                  sysType = sysType.contains("win") ? ".dll"
                        : sysType.contains("hpux") ? ".sl" : sysType.contains("mac") ? ".dylib" : ".so";
                  boolean acc = file.getName().toLowerCase().endsWith(sysType);
                  return acc;
               }
            });
            loadJniLibByFile(files);
         }
      } while (++i < folders.length);
   }
   
   /**
    * 按照文件加载jni类库
    * 
    * @param files
    * @throws Exception
    */
   public static void loadJniLibByFile(Object... files) throws Exception {
      if (files == null || files.length == 0)
         return;
      String nativeTempDir = System.getProperty("java.io.tmpdir");
      InputStream in = null;
      BufferedInputStream reader = null;
      FileOutputStream writer = null;
      File extractedLibFile = null;
      String extractedLibFileName = null;
      String tmpStr = null;
      File sourceFile = null;
      boolean isSysPath = false;
      for (Object file : files) {
         extractedLibFileName = nativeTempDir + File.separator;
         if (file instanceof File) {
            extractedLibFileName += ((File) file).getName();
         }
         if (file instanceof String) {
            tmpStr = (String) file;
            if (tmpStr.indexOf(File.separator) == -1) {
               extractedLibFileName += file.toString();
            } else {
               isSysPath = true;
               extractedLibFileName += tmpStr.substring(tmpStr.lastIndexOf(File.separator) + 1);
            }
         }
         extractedLibFile = new File(extractedLibFileName);
         if (!extractedLibFile.exists()) {
            if (file instanceof File) {
               sourceFile = (File) file;
            }
            if (file instanceof String) {
               if (isSysPath) {
                  sourceFile = new File(tmpStr);
               } else {
                  sourceFile = new File(Thread.currentThread().getContextClassLoader().getResource(tmpStr).getFile());
               }
            }
            if (!(sourceFile.exists() && sourceFile.isFile()))
               break;
            in = new FileInputStream(sourceFile);
            try {
               reader = new BufferedInputStream(in);
               writer = new FileOutputStream(extractedLibFile);
               byte[] buffer = new byte[1024];
               while (reader.read(buffer) > 0) {
                  writer.write(buffer);
                  buffer = new byte[1024];
               }
            } finally {
               if (in != null)
                  in.close();
               if (writer != null)
                  writer.close();
            }
            System.load(extractedLibFile.toString());
         }
      }
   }
   
   /**
    * main(这里用一句话描述这个方法的作用) TODO(这里描述这个方法适用条件 – 可选) TODO(这里描述这个方法的执行流程 – 可选)
    * TODO(这里描述这个方法的使用方法 – 可选) TODO(这里描述这个方法的注意事项 – 可选)
    *
    * @Title: main @Description: TODO @param @param args 设定文件 @return void
    *         返回类型 @throws
    */
   public static void sigarTest() {
      
      Sigar sigar = new Sigar();
      try {
         Mem mem = sigar.getMem();
         CpuPerc cpuCerc = sigar.getCpuPerc();
         System.out.println("*****当前CPU使用情况 ：");
         System.out.println("#总使用率: " + cpuCerc.getCombined() * 100 + "%");
         System.out.println("#用户使用率(user): " + cpuCerc.getUser() * 100 + "%");
         System.out.println("#系统使用率(sys): " + cpuCerc.getSys() * 100 + "%");
         System.out.println("#当前空闲率(idel): " + cpuCerc.getIdle() * 100 + "%");
         System.out.println("\n*****当前内存使用情况 ：");
         System.out.println("#内存总量：" + mem.getTotal() / 1024 / 1024 + "M");
         System.out.println("#已使用内存：" + mem.getUsed() / 1024 / 1024 + "M");
         System.out.println("#剩余内存：" + mem.getFree() / 1024 / 1024 + "M");
      } catch (Exception e) {
         e.printStackTrace();
      }
      
   }
   
   private static void property() throws UnknownHostException {
      Runtime r = Runtime.getRuntime();
      Properties props = System.getProperties();
      InetAddress addr;
      addr = InetAddress.getLocalHost();
      String ip = addr.getHostAddress();
      Map<String, String> map = System.getenv();
      String userName = map.get("USERNAME");// 获取用户名
      String computerName = map.get("COMPUTERNAME");// 获取计算机名
      String userDomain = map.get("USERDOMAIN");// 获取计算机域名
      System.out.println("用户名:   " + userName);
      System.out.println("计算机名:  " + computerName);
      System.out.println("计算机域名: " + userDomain);
      System.out.println("本地ip地址:   " + ip);
      System.out.println("本地主机名: " + addr.getHostName());
      System.out.println("JVM可以使用的总内存: " + r.totalMemory());
      System.out.println("JVM可以使用的剩余内存:   " + r.freeMemory());
      System.out.println("JVM可以使用的处理器个数:  " + r.availableProcessors());
      System.out.println("Java的运行环境版本： " + props.getProperty("java.version"));
      System.out.println("Java的运行环境供应商：   " + props.getProperty("java.vendor"));
      System.out.println("Java供应商的URL： " + props.getProperty("java.vendor.url"));
      System.out.println("Java的安装路径：   " + props.getProperty("java.home"));
      System.out.println("Java的虚拟机规范版本：   " + props.getProperty("java.vm.specification.version"));
      System.out.println("Java的虚拟机规范供应商：  " + props.getProperty("java.vm.specification.vendor"));
      System.out.println("Java的虚拟机规范名称：   " + props.getProperty("java.vm.specification.name"));
      System.out.println("Java的虚拟机实现版本：   " + props.getProperty("java.vm.version"));
      System.out.println("Java的虚拟机实现供应商：  " + props.getProperty("java.vm.vendor"));
      System.out.println("Java的虚拟机实现名称：   " + props.getProperty("java.vm.name"));
      System.out.println("Java运行时环境规范版本：  " + props.getProperty("java.specification.version"));
      System.out.println("Java运行时环境规范供应商： " + props.getProperty("java.specification.vender"));
      System.out.println("Java运行时环境规范名称：  " + props.getProperty("java.specification.name"));
      System.out.println("Java的类格式版本号： " + props.getProperty("java.class.version"));
      System.out.println("Java的类路径： " + props.getProperty("java.class.path"));
      System.out.println("加载库时搜索的路径列表： " + props.getProperty("java.library.path"));
      System.out.println("默认的临时文件路径：   " + props.getProperty("java.io.tmpdir"));
      System.out.println("一个或多个扩展目录的路径：   " + props.getProperty("java.ext.dirs"));
      System.out.println("操作系统的名称：  " + props.getProperty("os.name"));
      System.out.println("操作系统的构架：  " + props.getProperty("os.arch"));
      System.out.println("操作系统的版本：  " + props.getProperty("os.version"));
      System.out.println("文件分隔符： " + props.getProperty("file.separator"));
      System.out.println("路径分隔符： " + props.getProperty("path.separator"));
      System.out.println("行分隔符：  " + props.getProperty("line.separator"));
      System.out.println("用户的账户名称：  " + props.getProperty("user.name"));
      System.out.println("用户的主目录：   " + props.getProperty("user.home"));
      System.out.println("用户的当前工作目录：   " + props.getProperty("user.dir"));
   }
   
   private static void memory() throws SigarException {
      Sigar sigar = new Sigar();
      Mem mem = sigar.getMem();
      // 内存总量
      System.out.println("内存总量:  " + mem.getTotal() / 1024L + "K av");
      // 当前内存使用量
      System.out.println("当前内存使用量:  " + mem.getUsed() / 1024L + "K used");
      // 当前内存剩余量
      System.out.println("当前内存剩余量:  " + mem.getFree() / 1024L + "K free");
      Swap swap = sigar.getSwap();
      // 交换区总量
      System.out.println("交换区总量: " + swap.getTotal() / 1024L + "K av");
      // 当前交换区使用量
      System.out.println("当前交换区使用量: " + swap.getUsed() / 1024L + "K used");
      // 当前交换区剩余量
      System.out.println("当前交换区剩余量: " + swap.getFree() / 1024L + "K free");
   }
   
   private static void cpu() throws SigarException {
      Sigar sigar = new Sigar();
      CpuInfo infos[] = sigar.getCpuInfoList();
      CpuPerc cpuList[] = null;
      cpuList = sigar.getCpuPercList();
      for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
         CpuInfo info = infos[i];
         System.out.println("第" + (i + 1) + "块CPU信息");
         System.out.println("CPU的总量MHz:   " + info.getMhz());// CPU的总量MHz
         System.out.println("CPU生产商:   " + info.getVendor());// 获得CPU的卖主，如：Intel
         System.out.println("CPU类别: " + info.getModel());// 获得CPU的类别，如：Celeron
         System.out.println("CPU缓存数量:  " + info.getCacheSize());// 缓冲存储器数量
         printCpuPerc(cpuList[i]);
      }
   }
   
   private static void printCpuPerc(CpuPerc cpu) {
      System.out.println("CPU用户使用率: " + CpuPerc.format(cpu.getUser()));// 用户使用率
      System.out.println("CPU系统使用率: " + CpuPerc.format(cpu.getSys()));// 系统使用率
      System.out.println("CPU当前等待率: " + CpuPerc.format(cpu.getWait()));// 当前等待率
      System.out.println("CPU当前错误率: " + CpuPerc.format(cpu.getNice()));//
      System.out.println("CPU当前空闲率: " + CpuPerc.format(cpu.getIdle()));// 当前空闲率
      System.out.println("CPU总的使用率: " + CpuPerc.format(cpu.getCombined()));// 总的使用率
   }
   
   private static void os() {
      OperatingSystem OS = OperatingSystem.getInstance();
      // 操作系统内核类型如： 386、486、586等x86
      System.out.println("操作系统:  " + OS.getArch());
      System.out.println("操作系统CpuEndian():   " + OS.getCpuEndian());//
      System.out.println("操作系统DataModel():   " + OS.getDataModel());//
      // 系统描述
      System.out.println("操作系统的描述:  " + OS.getDescription());
      // 操作系统类型
      // System.out.println("OS.getName(): " + OS.getName());
      // System.out.println("OS.getPatchLevel(): " + OS.getPatchLevel());//
      // 操作系统的卖主
      System.out.println("操作系统的卖主:  " + OS.getVendor());
      // 卖主名称
      System.out.println("操作系统的卖主名: " + OS.getVendorCodeName());
      // 操作系统名称
      System.out.println("操作系统名称:   " + OS.getVendorName());
      // 操作系统卖主类型
      System.out.println("操作系统卖主类型: " + OS.getVendorVersion());
      // 操作系统的版本号
      System.out.println("操作系统的版本号: " + OS.getVersion());
   }
   
   private static void who() throws SigarException {
      Sigar sigar = new Sigar();
      Who who[] = sigar.getWhoList();
      if (who != null && who.length > 0) {
         for (int i = 0; i < who.length; i++) {
            // System.out.println("当前系统进程表中的用户名" + String.valueOf(i));
            Who _who = who[i];
            System.out.println("用户控制台: " + _who.getDevice());
            System.out.println("用户host:   " + _who.getHost());
            // System.out.println("getTime(): " + _who.getTime());
            // 当前系统进程表中的用户名
            System.out.println("当前系统进程表中的用户名:   " + _who.getUser());
         }
      }
   }
   
   private static void file() throws Exception {
      Sigar sigar = new Sigar();
      FileSystem fslist[] = sigar.getFileSystemList();
      for (int i = 0; i < fslist.length; i++) {
         System.out.println("分区的盘符名称" + i);
         FileSystem fs = fslist[i];
         // 分区的盘符名称
         System.out.println("盘符名称:  " + fs.getDevName());
         // 分区的盘符名称
         System.out.println("盘符路径:  " + fs.getDirName());
         System.out.println("盘符标志:  " + fs.getFlags());//
         // 文件系统类型，比如 FAT32、NTFS
         System.out.println("盘符类型:  " + fs.getSysTypeName());
         // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
         System.out.println("盘符类型名: " + fs.getTypeName());
         // 文件系统类型
         System.out.println("盘符文件系统类型: " + fs.getType());
         FileSystemUsage usage = null;
         try {
            usage = sigar.getFileSystemUsage(fs.getDirName());
         } catch (Exception e) {
            break;
         }
         switch (fs.getType()) {
            case 0: // TYPE_UNKNOWN ：未知
               break;
            case 1: // TYPE_NONE
               break;
            case 2: // TYPE_LOCAL_DISK : 本地硬盘
               // 文件系统总大小
               System.out.println(fs.getDevName() + "总大小:   " + usage.getTotal() + "KB");
               // 文件系统剩余大小
               System.out.println(fs.getDevName() + "剩余大小:  " + usage.getFree() + "KB");
               // 文件系统可用大小
               System.out.println(fs.getDevName() + "可用大小:  " + usage.getAvail() + "KB");
               // 文件系统已经使用量
               System.out.println(fs.getDevName() + "已经使用量: " + usage.getUsed() + "KB");
               double usePercent = usage.getUsePercent() * 100D;
               // 文件系统资源的利用率
               System.out.println(fs.getDevName() + "资源的利用率:   " + usePercent + "%");
               break;
            case 3:// TYPE_NETWORK ：网络
               break;
            case 4:// TYPE_RAM_DISK ：闪存
               break;
            case 5:// TYPE_CDROM ：光驱
               break;
            case 6:// TYPE_SWAP ：页面交换
               break;
         }
         System.out.println(fs.getDevName() + "读出： " + usage.getDiskReads());
         System.out.println(fs.getDevName() + "写入： " + usage.getDiskWrites());
      }
      return;
   }
   
   private static void net() throws Exception {
      Sigar sigar = new Sigar();
      String ifNames[] = sigar.getNetInterfaceList();
      for (int i = 0; i < ifNames.length; i++) {
         String name = ifNames[i];
         NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
         System.out.println("网络设备名: " + name);// 网络设备名
         System.out.println("IP地址:  " + ifconfig.getAddress());// IP地址
         System.out.println("子网掩码:  " + ifconfig.getNetmask());// 子网掩码
         if ((ifconfig.getFlags() & 1L) <= 0L) {
            System.out.println("!IFF_UP...skipping getNetInterfaceStat");
            continue;
         }
         NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
         System.out.println(name + "接收的总包裹数:" + ifstat.getRxPackets());// 接收的总包裹数
         System.out.println(name + "发送的总包裹数:" + ifstat.getTxPackets());// 发送的总包裹数
         System.out.println(name + "接收到的总字节数:" + ifstat.getRxBytes());// 接收到的总字节数
         System.out.println(name + "发送的总字节数:" + ifstat.getTxBytes());// 发送的总字节数
         System.out.println(name + "接收到的错误包数:" + ifstat.getRxErrors());// 接收到的错误包数
         System.out.println(name + "发送数据包时的错误数:" + ifstat.getTxErrors());// 发送数据包时的错误数
         System.out.println(name + "接收时丢弃的包数:" + ifstat.getRxDropped());// 接收时丢弃的包数
         System.out.println(name + "发送时丢弃的包数:" + ifstat.getTxDropped());// 发送时丢弃的包数
      }
   }
   
   private static void ethernet() throws SigarException {
      Sigar sigar = null;
      sigar = new Sigar();
      String[] ifaces = sigar.getNetInterfaceList();
      for (int i = 0; i < ifaces.length; i++) {
         NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
         if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
               || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
            continue;
         }
         System.out.println(cfg.getName() + "IP地址:" + cfg.getAddress());// IP地址
         System.out.println(cfg.getName() + "网关广播地址:" + cfg.getBroadcast());// 网关广播地址
         System.out.println(cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
         System.out.println(cfg.getName() + "子网掩码:" + cfg.getNetmask());// 子网掩码
         System.out.println(cfg.getName() + "网卡描述信息:" + cfg.getDescription());// 网卡描述信息
         System.out.println(cfg.getName() + "网卡类型" + cfg.getType());//
      }
   }
   
   public static void main(String[] args) {
      try {
//         loadJniLibByDir();
         MonitorUtil.loadJniLib();
         sigarTest();
         System.out.println("----------------------------------");
         // System信息，从jvm获取
         property();
         System.out.println("----------------------------------");
         // cpu信息
         cpu();
         System.out.println("----------------------------------");
         // 内存信息
         memory();
         System.out.println("----------------------------------");
         // 操作系统信息
         os();
         System.out.println("----------------------------------");
         // 用户信息
         who();
         System.out.println("----------------------------------");
         // 文件系统信息
         file();
         System.out.println("----------------------------------");
         // 网络信息
         net();
         System.out.println("----------------------------------");
         // 以太网信息
         ethernet();
         System.out.println("----------------------------------");
         
      } catch (Exception e1) {
         e1.printStackTrace();
      }
   }
   
}
