package com.yyb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

//机构预测的净利润
@Data
@TableName("org_net_fit")
public class OrgNetFit {
    @TableId(type = IdType.AUTO)
    private Integer net_fit_id;
    private String stock_name;
    private String stock_code;
    private Integer year;
    private String netfit;
}
