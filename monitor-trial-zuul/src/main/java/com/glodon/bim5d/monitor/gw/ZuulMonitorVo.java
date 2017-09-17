/**
 * 
 */
package com.glodon.bim5d.monitor.gw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hemd
 *
 */
public class ZuulMonitorVo implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 5965583606457252047L;
   private Date startTime;
   private Date endTime;
   private String gatewayIp;
   private String requestUrl;
   private String routUrl;
   private String resultType;
   private String clientIp;
   private String sourcePlatform;
   private String sourceVersion;
   private String userId;
   private String projectId;
   private String organizationId;
   public Date getStartTime() {
      return startTime;
   }
   public void setStartTime(Date startTime) {
      this.startTime = startTime;
   }
   public Date getEndTime() {
      return endTime;
   }
   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }
   public String getGatewayIp() {
      return gatewayIp;
   }
   public void setGatewayIp(String gatewayIp) {
      this.gatewayIp = gatewayIp;
   }
   public String getRequestUrl() {
      return requestUrl;
   }
   public void setRequestUrl(String requestUrl) {
      this.requestUrl = requestUrl;
   }
   public String getRoutUrl() {
      return routUrl;
   }
   public void setRoutUrl(String routUrl) {
      this.routUrl = routUrl;
   }
   public String getResultType() {
      return resultType;
   }
   public void setResultType(String resultType) {
      this.resultType = resultType;
   }
   public String getClientIp() {
      return clientIp;
   }
   public void setClientIp(String clientIp) {
      this.clientIp = clientIp;
   }
   public String getSourcePlatform() {
      return sourcePlatform;
   }
   public void setSourcePlatform(String sourcePlatform) {
      this.sourcePlatform = sourcePlatform;
   }
   public String getSourceVersion() {
      return sourceVersion;
   }
   public void setSourceVersion(String sourceVersion) {
      this.sourceVersion = sourceVersion;
   }
   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }
   public String getProjectId() {
      return projectId;
   }
   public void setProjectId(String projectId) {
      this.projectId = projectId;
   }
   public String getOrganizationId() {
      return organizationId;
   }
   public void setOrganizationId(String organizationId) {
      this.organizationId = organizationId;
   }
   @Override
   public String toString() {
      return "ZuulMonitorVo [startTime=" + startTime + ", endTime=" + endTime + ", gatewayIp=" + gatewayIp
            + ", requestUrl=" + requestUrl + ", routUrl=" + routUrl + ", resultType=" + resultType + ", clientIp="
            + clientIp + ", sourcePlatform=" + sourcePlatform + ", sourceVersion=" + sourceVersion + ", userId="
            + userId + ", projectId=" + projectId + ", organizationId=" + organizationId + "]";
   }
}
