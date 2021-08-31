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
@TableName("basic_income")
public class IncomeEntity {
    public IncomeEntity(){}

    public IncomeEntity(Integer year,String stock_name,String stock_code){
        this.year=year;
        this.stock_code=stock_code;
        this.stock_name=stock_name;
    }

    @TableId(type = IdType.AUTO)
    private Integer income_id;

    private String stock_code;
    private String stock_name;

    //年份
    @Column(name = "科目\\时间")
    private Integer year;

    @Column(name = "一、营业总收入")
    private BigDecimal total_operate_income;

    @Column(name = "其中：营业收入")
    private BigDecimal operate_income;

    @Column(name = "利息收入")
    private BigDecimal interest_income;

    @Column(name = "已赚保费")
    private BigDecimal earned_premium;

    @Column(name = "手续费及佣金收入")
    private BigDecimal fee_commission_income;

    @Column(name = "二、营业总成本")
    private BigDecimal total_operate_cost;

    @Column(name = "其中：营业成本")
    private BigDecimal operate_cost;

    @Column(name = "利息支出")
    private BigDecimal interest_expense;

    @Column(name = "手续费及佣金支出")
    private BigDecimal fee_commission_expense;

    @Column(name = "退保金")
    private BigDecimal surrender_value;

    @Column(name = "赔付支出净额")
    private BigDecimal compensate_expense;

    @Column(name = "提取保险责任合同准备金净额")
    private BigDecimal extract_insurance_reserve;

    @Column(name = "保单红利支出")
    private BigDecimal policy_bonus_expense;

    @Column(name = "分保费用")
    private BigDecimal reinsure_expense;

    @Column(name = "税金及附加")
    private BigDecimal operate_tax_add;

    @Column(name = "销售费用")
    private BigDecimal sales_fee;

    @Column(name = "管理费用")
    private BigDecimal manage_fee;

    @Column(name = "研发费用")
    private BigDecimal research_expense;

    @Column(name = "财务费用")
    private BigDecimal finance_expense;

    @Column(name = "其中：利息费用")
    private BigDecimal fe_interest_expense;

    @Column(name = "利息收入")
    private BigDecimal fe_interest_income;

    @Column(name = "加：其他收益")
    private BigDecimal other_income;

    @Column(name = "投资收益")
    private BigDecimal invest_income;

    @Column(name = "其中：对联营企业和合营企业的投资收益")
    private BigDecimal invest_joint_income;

//    @Column(name = "以摊余成本计量的金融资产终止确认收益")
//    private BigDecimal invest_joint_income;

    @Column(name = "汇兑收益")
    private BigDecimal exchange_income;//汇兑收益

//    @Column(name = "净敞口套期收益")
//    private BigDecimal invest_joint_income;

    @Column(name = "公允价值变动收益")
    private BigDecimal fairvalue_change_income;

    @Column(name = "信用减值损失")
    private BigDecimal credit_impairment_income;

    @Column(name = "资产减值损失")
    private BigDecimal asset_impairment_income;

    @Column(name = "资产处置收益")
    private BigDecimal asset_disposal_income;

    @Column(name = "三、营业利润")
    private BigDecimal operate_profit;

    @Column(name = "加：营业外收入")
    private BigDecimal nonbusiness_income;

    @Column(name = "减：营业外支出")
    private BigDecimal nonbusiness_expense;

    @Column(name = "四、利润总额")
    private BigDecimal total_profit;

    @Column(name = "减：所得税费用")
    private BigDecimal income_tax;

    @Column(name = "五、净利润")
    private BigDecimal netprofit;

    @Column(name = "持续经营净利润")
    private BigDecimal continued_netprofit;

    @Column(name = "终止经营净利润")
    private BigDecimal discontinued_netprofit;

    @Column(name = "归属于母公司股东的净利润")
    private BigDecimal parent_netprofit;

    @Column(name = "归属于母公司股东的净利润增长率")
    private BigDecimal parent_netprofit_yoy;

    @Column(name = "少数股东损益")
    private BigDecimal minority_interest;

//    @Column(name = "六、其他综合收益的税后净额")
//    private BigDecimal minority_interest;

//    @Column(name = "（一）归属母公司所有者的其他综合收益的税后净额")
//    private BigDecimal minority_interest;

//    @Column(name = "1.不能重分类进损益的其他综合收益")
//    private BigDecimal minority_interest;

//    @Column(name = "（1）重新计量设定受益计划变动额")
//    private BigDecimal minority_interest;

//    @Column(name = "（2）权益法下不能转损益的其他综合收益")
//    private BigDecimal minority_interest;

//    @Column(name = "（3）其他权益工具投资公允价值变动")
//    private BigDecimal minority_interest;

//    @Column(name = "（4）企业自身信用风险公允价值变动")
//    private BigDecimal minority_interest;

//    @Column(name = "2．将重分类进损益的其他综合收益")
//    private BigDecimal minority_interest;

//    @Column(name = "（1）权益法下可转损益的其他综合收益")
//    private BigDecimal minority_interest;

//    @Column(name = "（2）其他债权投资公允价值变动")
//    private BigDecimal minority_interest;

//    @Column(name = "（3）可供出售金融资产公允价值变动损益")
//    private BigDecimal minority_interest;

//    @Column(name = "（4）金融资产重分类计入其他综合收益的金额")
//    private BigDecimal minority_interest;

//    @Column(name = "（5）持有至到期投资重分类为可供出售金融资产损益")
//    private BigDecimal minority_interest;

//    @Column(name = "（6）其他债权投资信用减值准备")
//    private BigDecimal minority_interest;

//    @Column(name = "（7）现金流量套期储备（现金流量套期损益的有效部分）")
//    private BigDecimal minority_interest;

//    @Column(name = "（8）外币财务报表折算差额")
//    private BigDecimal minority_interest;

//    @Column(name = "（9）其他")
//    private BigDecimal minority_interest;

//    @Column(name = "（二）归属于少数股东的其他综合收益的税后净额")
//    private BigDecimal minority_interest;


    @Column(name = "七、综合收益总额")
    private BigDecimal total_compre_income;

    @Column(name = "（一）归属于母公司所有者的综合收益总额")
    private BigDecimal parent_tci;

    @Column(name = "（二）归属于少数股东的综合收益总额")
    private BigDecimal minority_tci;

    @Column(name = "其中：联营企业和合营企业的投资收益(元)")
    private BigDecimal invest_incomes_from_rr;

    @Column(name = "（一）基本每股收益(元/股)")
    private BigDecimal basic_eps;

    @Column(name = "（二）稀释每股收益(元/股)")
    private BigDecimal diluted_eps;
}
