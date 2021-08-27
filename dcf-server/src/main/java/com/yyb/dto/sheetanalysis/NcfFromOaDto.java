package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 经营活动产生的现金流量净额
 */
@Data
public class NcfFromOaDto {
    //年份
    private Integer year;
    //经营活动产生的现金流量净额
    private BigDecimal ncfFromOa;
    //固定资产折旧,可直接搜索年报“现金流量表补充资料”得到“固定资产折旧、油气资产折耗、生产性生物资产折旧”的数值。
    private BigDecimal assetDepreciation;
    //无形资产摊销,可直接搜索年报“现金流量表补充资料”得到“无形资产摊销”的数值。
    private BigDecimal intangibleAssetsAmortize ;
    //分配股利、利润或偿付利息支付的现金,（借款利息 + 现金股利 ）对应合并现金流量表中筹资活动下面“分配股利、利润或偿付利息支付的现金”
    private BigDecimal cashPaidOfDistribution;
    //“经营现金净额”减去“固定折旧+无形摊销+借款利息+股利”之差,"大于0，公司才有多余的资金扩大再生产。持续小于0，淘汰。"
    private BigDecimal ncfFromOaDiff;
}
