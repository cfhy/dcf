package com.yyb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yyb.config.Column;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 利润表
 */
@Data
@TableName("basic_cash_flow")
public class CashFlowEntity {
    public CashFlowEntity() {
    }

    public CashFlowEntity(Integer year, String stock_name, String stock_code) {
        this.year = year;
        this.stock_code = stock_code;
        this.stock_name = stock_name;
    }

    @TableId(type = IdType.AUTO)
    private Integer cash_flow_id;

    private String stock_code;
    private String stock_name;

    //年份
    @Column(name = "科目\\时间")
    private Integer year;

    //一、经营活动产生的现金流量：
    @Column(name = "销售商品、提供劳务收到的现金")
    private BigDecimal sales_services;

    @Column(name = "客户存款和同业存放款项净增加额")
    private BigDecimal deposit_interbank_add;

    @Column(name = "向中央银行借款净增加额")
    private BigDecimal loan_pbc_add;

//    @Column(name = "向其他金融机构拆入资金净增加额")
//    private BigDecimal sales_services;

    @Column(name = "收到原保险合同保费取得的现金")
    private BigDecimal receive_origic_premium;

//    @Column(name = "收到再保业务现金净额")
//    private BigDecimal sales_services;

    @Column(name = "保户储金及投资款净增加额")
    private BigDecimal insured_invest_add;

    @Column(name = "收取利息、手续费及佣金的现金")
    private BigDecimal receive_interest_commission;

    @Column(name = "拆入资金净增加额")
    private BigDecimal borrow_fund_add;

    @Column(name = "回购业务资金净增加额")
    private BigDecimal repo_business_add;

    @Column(name = "代理买卖证券收到的现金净额")
    private BigDecimal receive_agent_trade;

//    @Column(name = "收到的税费返还")
//    private BigDecimal sales_services;

    @Column(name = "收到其他与经营活动有关的现金")
    private BigDecimal receive_other_operate;

    @Column(name = "经营活动现金流入小计")
    private BigDecimal total_operate_inflow;

    @Column(name = "购买商品、接受劳务支付的现金")
    private BigDecimal buy_services;

    @Column(name = "客户贷款及垫款净增加额")
    private BigDecimal loan_advance_add;

    @Column(name = "存放中央银行和同业款项净增加额")
    private BigDecimal pbc_interbank_add;

    @Column(name = "支付原保险合同赔付款项的现金")
    private BigDecimal pay_origic_compensate;

//    @Column(name = "拆出资金净增加额")
//    private BigDecimal pay_origic_compensate;

    @Column(name = "支付利息、手续费及佣金的现金")
    private BigDecimal pay_interest_commission;

    @Column(name = "支付给职工及为职工支付的现金")
    private BigDecimal pay_staff_cash;

    @Column(name = "支付的各项税费")
    private BigDecimal pay_all_tax;

    @Column(name = "支付其他与经营活动有关的现金")
    private BigDecimal pay_other_operate;

    @Column(name = "经营活动现金流出小计")
    private BigDecimal total_operate_outflow;

    @Column(name = "经营活动产生的现金流量净额")
    private BigDecimal netcash_operate;

    //二、投资活动产生的现金流量：
    @Column(name = "收回投资收到的现金")
    private BigDecimal withdraw_invest;

    @Column(name = "取得投资收益收到的现金")
    private BigDecimal receive_invest_income;

    @Column(name = "处置固定资产、无形资产和其他长期资产收回的现金净额")
    private BigDecimal disposal_long_asset;

    @Column(name = "处置子公司及其他营业单位收到的现金净额")
    private BigDecimal disposal_subsidiary_other;

    @Column(name = "收到其他与投资活动有关的现金")
    private BigDecimal receive_other_invest;

    @Column(name = "投资活动现金流入小计")
    private BigDecimal total_invest_inflow;

    @Column(name = "购建固定资产、无形资产和其他长期资产支付的现金")
    private BigDecimal construct_long_asset;

    @Column(name = "投资支付的现金")
    private BigDecimal invest_pay_cash;

//    @Column(name = "质押贷款净增加额")
//    private BigDecimal invest_pay_cash;

    @Column(name = "取得子公司及其他营业单位支付的现金净额")
    private BigDecimal obtain_subsidiary_other;

    @Column(name = "支付其他与投资活动有关的现金")
    private BigDecimal pay_other_invest;

    @Column(name = "投资活动现金流出小计")
    private BigDecimal total_invest_outflow;

    @Column(name = "投资活动产生的现金流量净额")
    private BigDecimal netcash_invest;

    //三、筹资活动产生的现金流量：
    @Column(name = "吸收投资收到的现金")
    private BigDecimal accept_invest_cash;

    @Column(name = "其中:子公司吸收少数股东投资收到的现金")
    private BigDecimal subsidiary_accept_invest;

    @Column(name = "取得借款收到的现金")
    private BigDecimal receive_loan_cash;

    @Column(name = "收到其他与筹资活动有关的现金")
    private BigDecimal receive_other_finance;

    @Column(name = "筹资活动现金流入小计")
    private BigDecimal total_finance_inflow;

    @Column(name = "偿还债务支付的现金")
    private BigDecimal pay_debt_cash;

    @Column(name = "分配股利、利润或偿付利息支付的现金")
    private BigDecimal assign_dividend_porfit;

    @Column(name = "其中：子公司支付给少数股东的股利、利润支付其他与筹资活动有关的现金")
    private BigDecimal subsidiary_pay_dividend;

    @Column(name = "筹资活动现金流出小计")
    private BigDecimal total_finance_outflow;

    @Column(name = "筹资活动产生的现金流量净额")
    private BigDecimal netcash_finance;

    @Column(name = "四、汇率变动对现金及现金等价物的影响")
    private BigDecimal rate_change_effect;

    @Column(name = "五、现金及现金等价物净增加额")
    private BigDecimal cce_add;

    @Column(name = "加:期初现金及现金等价物余额")
    private BigDecimal begin_cce;

    @Column(name = "六、期末现金及现金等价物余额")
    private BigDecimal end_cce;

    //补充资料
    @Column(name = "资产减值准备")
    private BigDecimal asset_impairment;
    @Column(name = "固定资产和投资性房地产折旧")
    private BigDecimal fa_ir_depr;
    @Column(name = "其中:固定资产折旧、油气资产折耗、生产性生物资产折旧")
    private BigDecimal oilgas_biology_depr;
    @Column(name = "投资性房地产折旧")
    private BigDecimal ir_depr;
    @Column(name = "无形资产摊销")
    private BigDecimal ia_amortize;
    @Column(name = "长期待摊费用摊销")
    private BigDecimal lpe_amortize;

}
