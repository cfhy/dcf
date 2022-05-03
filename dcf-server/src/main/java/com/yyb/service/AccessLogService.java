package com.yyb.service;


import com.yyb.entity.AccessLog;
import com.yyb.mapper.AccessLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
@Slf4j
public class AccessLogService {
    @Autowired
    private AccessLogMapper accessLogMapper;

    public void writeLog(String stockCode, String stockName, HttpServletRequest request){
        AccessLog log =new AccessLog();
        log.setAccess_time(new Date());
        log.setAccess_stock_code(stockCode);
        log.setAccess_stock_name(stockName);
        log.setAccess_ip(getIpAddr(request));
        log.setUser_agent(request.getHeader("User-Agent"));
        accessLogMapper.insert(log);
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        log.info("x-forwarded-for={}",request.getHeader("x-forwarded-for"));
        log.info("Proxy-Client-IP={}",request.getHeader("Proxy-Client-IP"));
        log.info("WL-Proxy-Client-IP={}",request.getHeader("WL-Proxy-Client-IP"));
        log.info("request.getRemoteAddr={}",request.getRemoteAddr());
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

}
