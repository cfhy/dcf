package com.yyb.entity.tonghuashun;

import com.yyb.config.Column;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 利润表
 */
@Data
public class CashFlowSheetEntity {
    //年份
    @Column(name = "科目\\时间")
    private Integer year;

    @Column(name = "销售商品、提供劳务收到的现金(元)")
    private BigDecimal cash_received_of_sales_service;

    @Column(name = "收到的税费与返还(元)")
    private BigDecimal refund_of_tax_and_levies;

    @Column(name = "收到其他与经营活动有关的现金(元)")
    private BigDecimal cash_received_of_othr_oa;

    @Column(name = "经营活动现金流入小计(元)")
    private BigDecimal sub_total_of_ci_from_oa;

    @Column(name = "购买商品、接受劳务支付的现金(元)")
    private BigDecimal goods_buy_and_service_cash_pay;

    @Column(name = "支付给职工以及为职工支付的现金(元)")
    private BigDecimal cash_paid_to_employee_etc;

    @Column(name = "支付的各项税费(元)")
    private BigDecimal payments_of_all_taxes;

    @Column(name = "支付其他与经营活动有关的现金(元)")
    private BigDecimal othrcash_paid_relating_to_oa;

    @Column(name = "经营活动现金流出小计(元)")
    private BigDecimal sub_total_of_cos_from_oa;

    @Column(name = "经营活动产生的现金流量净额(元)")
    private BigDecimal ncf_from_oa;

    @Column(name = "收回投资收到的现金(元)")
    private BigDecimal cash_received_of_dspsl_invest;

    @Column(name = "取得投资收益收到的现金(元)")
    private BigDecimal invest_income_cash_received;

    @Column(name = "处置固定资产、无形资产和其他长期资产收回的现金净额(元)")
    private BigDecimal net_cash_of_disposal_assets;

    @Column(name = "处置子公司及其他营业单位收到的现金净额(元)")
    private BigDecimal net_cash_of_disposal_branch;

    @Column(name = "收到其他与投资活动有关的现金(元)")
    private BigDecimal cash_received_of_othr_ia;

    @Column(name = "投资活动现金流入小计(元)")
    private BigDecimal sub_total_of_ci_from_ia;

    @Column(name = "购建固定资产、无形资产和其他长期资产支付的现金(元)")
    private BigDecimal cash_paid_for_assets;

    @Column(name = "投资支付的现金(元)")
    private BigDecimal invest_paid_cash;

    @Column(name = "取得子公司及其他营业单位支付的现金净额(元)")
    private BigDecimal net_cash_amt_from_branch;

    @Column(name = "支付其他与投资活动有关的现金(元)")
    private BigDecimal othrcash_paid_relating_to_ia;

    @Column(name = "投资活动现金流出小计(元)")
    private BigDecimal sub_total_of_cos_from_ia;

    @Column(name = "投资活动产生的现金流量净额(元)")
    private BigDecimal ncf_from_ia;

    @Column(name = "吸收投资收到的现金(元)")
    private BigDecimal cash_received_of_absorb_invest;

    @Column(name = "其中：子公司吸收少数股东投资收到的现金(元)")
    private BigDecimal cash_received_from_investor;

    @Column(name = "取得借款收到的现金(元)")
    private BigDecimal cash_received_of_borrowing;

    @Column(name = "收到其他与筹资活动有关的现金(元)")
    private BigDecimal cash_received_of_othr_fa;

    @Column(name = "筹资活动现金流入小计(元)")
    private BigDecimal sub_total_of_ci_from_fa;

    @Column(name = "偿还债务支付的现金(元)")
    private BigDecimal cash_pay_for_debt;

    @Column(name = "分配股利、利润或偿付利息支付的现金(元)")
    private BigDecimal cash_paid_of_distribution;

    @Column(name = "其中：子公司支付给少数股东的股利、利润(元)")
    private BigDecimal branch_paid_to_minority_holder;

    @Column(name = "支付其他与筹资活动有关的现金(元)")
    private BigDecimal othrcash_paid_relating_to_fa;

    @Column(name = "筹资活动现金流出小计(元)")
    private BigDecimal sub_total_of_cos_from_fa;

    @Column(name = "筹资活动产生的现金流量净额(元)")
    private BigDecimal ncf_from_fa;

    @Column(name = "四、汇率变动对现金及现金等价物的影响(元)")
    private BigDecimal effect_of_exchange_chg_on_cce;

    @Column(name = "五、现金及现金等价物净增加额(元)")
    private BigDecimal net_increase_in_cce;

    @Column(name = "加：期初现金及现金等价物余额(元)")
    private BigDecimal initial_balance_of_cce;

    @Column(name = "六、期末现金及现金等价物余额(元)")
    private BigDecimal final_balance_of_cce;
}
