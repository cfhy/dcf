package com.yyb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("access_log")
public class AccessLog {
    @TableId(type = IdType.AUTO)
    private Integer access_log_id;
    private String access_ip;
    private String user_agent;
    private Date access_time;
    private String access_stock_code;
    private String access_stock_name;
}