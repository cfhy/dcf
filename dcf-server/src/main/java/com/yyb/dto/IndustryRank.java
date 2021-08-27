package com.yyb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IndustryRank {
    private String industry;
    private String stockName;
    private String stockCode;
    private BigDecimal netfit;
    private BigDecimal total_revenue;
    private BigDecimal total_assets;
}
