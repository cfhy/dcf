package com.yyb.entity.xueqiu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockBasicInfoQuoteEntity {
    //股票名称
    private String name;
    //股票代码
    private String symbol;
    //类型：11-股票 14-转债 13-ETF
    private Integer type;
    //股价
    private BigDecimal current;
    //流通股
    private Long float_shares;
    //总股本
    private Long total_shares;
    //流通市值
    private Long float_market_capital;
    //总市值
    private Long market_capital;
    //TTM
    private String pe_ttm;
    //股息率
    private String dividend_yield;
}
