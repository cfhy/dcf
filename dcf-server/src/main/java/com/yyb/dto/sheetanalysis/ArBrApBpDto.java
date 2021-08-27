package com.yyb.dto.sheetanalysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 应收应付和预付预收
 * 应付预收总额 > 应收预付总额   说明行业地位强，差额是公司无偿占用别人的资金！关于行业地位的判断，注意要看5年的数据，不能只看1年的数据来进行判断。
 */
@Data
public class ArBrApBpDto {
    //年份
    private String year;
    //应付票据
    private BigDecimal billPayable;
    //应付账款
    private BigDecimal accountsPayable;
    //预收款项
    private BigDecimal preReceivable;
    //应付预收合计 = 应付票据 + 应付账款 + 预收款项
    private BigDecimal totalApBpPr;

    //应收票据
    private BigDecimal billsReceivable;
    //应收款项融资里的应收票据
    private BigDecimal billsReceivablePlus;
    //应收账款
    private BigDecimal accountReceivable;
    //应收款项融资里的应收账款保理
    private BigDecimal accountReceivablePlus;
    //预付款项
    private BigDecimal prePayment;
    //应收预付合计 = 应收票据 + 应收账款 + 预付款项
    private BigDecimal totalArBr;
    /**
     * 应付预收与应收预付之差
     * 大于0，差额为公司无偿占用上游供应商和下游经销商的资金金额。相当于上下游企业为公司提供了大量的无息贷款。金额越大，公司的竞争力越强。
     * 如果是行业龙头，则公司具有“两头吃”的能力
     * 小于0，公司的自有资金被其他公司无偿占用，这样的公司竞争力相对较弱。
     */
    private BigDecimal apBpPrWithArBrDiff;
    //应收账款占总资产的比率 = 应收账款 / 总资产
    //应收账款的规模较大，经营风险自然也较大。比率大于20%的公司淘汰。
    private BigDecimal accountReceivableRatio;

}
