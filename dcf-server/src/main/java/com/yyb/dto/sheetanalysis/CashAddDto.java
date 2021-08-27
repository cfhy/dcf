package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 现金净增加额
 */
@Data
public class CashAddDto {
    //年份
    private String year;
    /**
     * 现金及现金等价物净增加额
     */
    private BigDecimal netIncreaseInCce;
    /**
     * 每年实际发放现金分红的总额
     * 可以通过 同花顺财经的“个股频道”查询“分红融资”下的分红情况。查询网址为 http://stockpage.10jqka.com.cn/
     * 以“股权登记日”的年度为准，查到的单位是以“亿”计算的，换算后面加0进去补足。
     */
    private BigDecimal totalBonus;
    /**
     * 加回“现金分红”后的现金及现金等价物净增加额= 现金及现金等价物净增加额 + 现金分红的总额
     * 优秀的公司现金净增加额一般都是大于0的，公司所属的“正负负”或“正正负”类型才能持续的保持。小于0的公司淘汰掉。
     */
    private BigDecimal totalCash;
}
