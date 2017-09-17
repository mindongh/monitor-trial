/**
 * 
 */
package com.glodon.bim5d.monitor.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.glodon.bim5d.monitor.util.MonitorUtil;
import com.glodon.bim5d.monitor.util.SpringContextHolder;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author hemd
 * 用于收集MySQL监控信息
 */
@Component
public class MySQLMonitorCollector {
   
   /**
    * 收集MySQL监控信息
    * @return  返回一个集合，配置多少数据源就返回多少监控对象
    * @throws Exception
    */
   public List<MySQLMonitorVo> gatherMySQLMonitorInfo() throws Exception{
      List<MySQLMonitorVo>infos=new ArrayList<MySQLMonitorVo>();
      MySQLMonitorVo info=null;
      Map<String,JdbcTemplate> map=SpringContextHolder.getBeansOfType(JdbcTemplate.class);
      JdbcTemplate jt=null;
      DataSource ds=null;
      List<Map<String, Object>>datas=null;
      Map<String, Object>data=null;
      DruidDataSource dds=null;
      ComboPooledDataSource cds=null;
      String jdbcUrl=null;
      String arr[]=null;
      for(Map.Entry<String,JdbcTemplate>en:map.entrySet()){
         info=new MySQLMonitorVo();
         infos.add(info);
         jt=en.getValue();
         ds=jt.getDataSource();
         if(ds instanceof DruidDataSource){
            dds=(DruidDataSource)ds;
            jdbcUrl=dds.getUrl();
         }
         if(ds instanceof ComboPooledDataSource){
            cds=(ComboPooledDataSource)ds;
            jdbcUrl=cds.getJdbcUrl();
         }
         arr=MonitorUtil.parseDbUrl(jdbcUrl);
         info.setIp(arr[0]);
         info.setDbName(arr[1]);
         datas=jt.queryForList("SHOW DATABASES");
         if(datas!=null)
            info.setDbCount(datas.size());
         data=jt.queryForMap("SHOW STATUS LIKE ?","Max_used_connections");
         if(data!=null){
            jdbcUrl=MonitorUtil.getPureString(data.get("Value"));
            if(jdbcUrl!=null)
               info.setConnectionCount(Integer.valueOf(jdbcUrl));
         }
         data=jt.queryForMap("SHOW STATUS LIKE ?","Threads_connected");
         if(data!=null){
            jdbcUrl=MonitorUtil.getPureString(data.get("Value"));
            if(jdbcUrl!=null)
               info.setActiveConnectionCount(Integer.valueOf(jdbcUrl));
         }
      }
      return infos;
   }
}
