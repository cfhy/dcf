package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 归母净利润
 */
@Data
public class NetProfitAtsopcDto {
    //年份
    private Integer year;
    //归属于母公司所有者的净利润
    private BigDecimal netProfitAtsopc;
    /**
     * 归母净利润增长率 = （本年归属于母公司股东的净利润 - 上年归属于母公司股东的净利润）/ 上年归属于母公司股东的净利润 * 100%
     *  大于10%，说明公司在以较快的速度成长。 大于10%且规模比较大，说明公司不但盈利能力强而且还在快速成长。
     *  小于0，很可能意味公司已经处于衰落之中。
     *  经验： 一般会把归母净利润增长率小于10%的公司淘汰掉。
     */
    private BigDecimal growthRatio;
    //加权平均净资产收益率ROE
    private BigDecimal roe;
}
