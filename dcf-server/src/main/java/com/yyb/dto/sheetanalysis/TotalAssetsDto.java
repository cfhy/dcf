package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 总资产
 */
@Data
public class TotalAssetsDto {
    //年份
    private String year;
    /**
     * A股市场总资产大致规模：
     * 截止2020年5月在A股3800多家上市公司中， 总资产大于500亿的大概300多家；总资产大于100亿的大概1000多家；总资产大于50亿的大概1600多家。
     */
    private BigDecimal totalAssets;
    /**
     *  总资产增长率 = （本年总资产 - 上年总资产）/ 上年总资产
     *
     *  1、总资产同比增长较快，有两种情况：
     *  （1）公司一般处于快速扩张之中，这样的公司成长性较强。
     *  （2）公司的扩张可能来自债务，而不是公司净利润的增长，公司可能处于债务危机之中。
     *
     *  2、总资产同比负增长，公司很可能处于收缩或者衰退之中。
     */
    private BigDecimal totalAssetsGroupRatio;

}
