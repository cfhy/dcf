package com.yyb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("stock")
public class Stock {
    @TableId(type = IdType.AUTO)
    private Integer stock_id;
    private String stock_name;
    private String stock_code;
    private String industry;
    private BigDecimal netfit;
    private BigDecimal total_revenue;
    private BigDecimal total_assets;
    private BigDecimal total_shares;
    private String operate_range;
    private String operate_desc;

    @TableField(exist = false)
    private Integer type;
}
