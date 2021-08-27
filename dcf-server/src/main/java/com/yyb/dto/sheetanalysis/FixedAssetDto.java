package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 固定资产
 */
@Data
public class FixedAssetDto {
    //年份
    private String year;
    //固定资产
    private BigDecimal fixedAsset;
    //在建工程
    private BigDecimal constructionInProcess;
    //工程物资
    private BigDecimal projectGoodsAndMaterial;
    //总的固定资产
    private BigDecimal totalFixedAsset;
    //固定资产工程占总资产的比率，比率 = （固定资产 + 在建工程 + 工程物资） / 总资产
    //大于40%，重资产型公司。维持竞争力的成本比较高，风险相对较大。 小于40%，轻资产型公司。保持持续的竞争力成本相对要低一些。
    //40%只是经验值，不是说重资产型公司就不能投，重资产型公司不代表盈利能力不好，比如一家持续专注于主业的公司，其获得持续成功的概率就会大一些。
    private BigDecimal fixedAssetRatio;
}
