package com.yyb.service;


import cn.hutool.core.util.StrUtil;
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

    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (StrUtil.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if (StrUtil.isBlank(ipAddress) || ipAddress.contains(":")
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = "127.0.0.1";
        }
        String [] ips= ipAddress.split(",");
        return ips[0];
    }
}
