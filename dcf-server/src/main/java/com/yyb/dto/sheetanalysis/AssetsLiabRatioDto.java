package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 资产负债率
 */
@Data
public class AssetsLiabRatioDto {
    //年份
    private String year;
    //总负债
    private BigDecimal totalLiab;
    /**
     * 资产负债率
     * 资产负债率 = 总负债 / 总资产
     *
     * 1、比率越小，公司未来发生债务危机的可能较小；
     * 2、比率越大，公司未来发生债务危机的可能较大；
     */
    private BigDecimal assetLiabRatio;
    /**
     * 资产负债率增长率
     * 3、一般同比大幅增加的公司，遇到问题的概率比较大，偿债风险较大。（同比指的是本年度与上一年度的值对比。）
     * 4、资产负债率并不是越低越好，要知道像应付票据、应付账款、预收款项之类的负债高恰恰是公司竞争力强的表现。
     */
    private BigDecimal growthRatio;
}
