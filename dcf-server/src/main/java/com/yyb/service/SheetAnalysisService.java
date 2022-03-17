package com.yyb.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyb.config.Column;
import com.yyb.entity.*;
import com.yyb.entity.dfcf.*;
import com.yyb.mapper.*;
import com.yyb.utils.DecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * 财报分析Service
 */
@Service
@Slf4j
public class SheetAnalysisService {
    @Autowired
    private BalanceMapper balanceMapper;
    @Autowired
    private CashFlowMapper cashFlowMapper;
    @Autowired
    private IncomeMapper incomeMapper;
    @Autowired
    private AnalysisSubjectMapper analysisSubjectMapper;
    @Autowired
    private DfcfCrawler dfcfCrawler;

    public static final BigDecimal handred = new BigDecimal(100);

    /**
     * 判断数据库中是否存在，存在直接返回结果，不存在进入以下操作：
     * 1、拉取存数据库
     * 2、分析，结果存数据库
     * 3、返回结果
     *
     * @param stockCode
     * @return
     */
    @Transactional
    public List<AnalysisSubject> analysis(String stockCode) {
        //判断数据库中是否存在，存在直接返回结果
        int year = 5;
        List<AnalysisSubject> analysisList = getAnalysisList(stockCode, year,true);
        if (CollUtil.isEmpty(analysisList)) {
            synchronized (SheetAnalysisService.class) {
                analysisList = getAnalysisList(stockCode, year,false);
                if (CollUtil.isEmpty(analysisList)) {
                    //1、拉取存数据库
                    pullSave(stockCode, year);
                    //2、分析，结果存数据库
                    List<BalanceEntity> balanceList = balanceMapper.selectList(getQueryWrapper(stockCode, year));
                    List<IncomeEntity> incomeList = incomeMapper.selectList(getQueryWrapper(stockCode, year));
                    List<CashFlowEntity> cashFlowList = cashFlowMapper.selectList(getQueryWrapper(stockCode, year));
                    List<LnfhrzItemEntity> bonusList = dfcfCrawler.getBonusFinancing(stockCode);
                    List<ZyzbItemEntity> zyzbList = dfcfCrawler.getZYZB(stockCode);
                    this.analysis(balanceList, incomeList, cashFlowList, bonusList, zyzbList, stockCode);
                    analysisList = analysisSubjectMapper.selectList(getQueryWrapper(stockCode, year));
                }
            }
        }
        return analysisList;
    }

    private List<AnalysisSubject> getAnalysisList(String stockCode, int year, boolean isFirst) {
        List<AnalysisSubject> analysisSubjectList = analysisSubjectMapper.selectList(getQueryWrapper(stockCode, year));
        if (CollUtil.isEmpty(analysisSubjectList)) {
            return null;
        }
        //如果不为空，判断数据库是否有当前年的年报
        Integer lastYear = analysisSubjectList.get(analysisSubjectList.size()-1).getYear();
        int currYear = DateUtil.year(new Date())-1;
        if (lastYear.equals(currYear)) {
            return analysisSubjectList;
        }
        //如果是第1次来访问，不去判断是否有年报出来了
        if (isFirst) {
            return null;
        }
        //查是否有年报出来
        List<DateItemEntity> dateList = dfcfCrawler.getDateList(stockCode);
        if (CollUtil.isEmpty(dateList)) {
            //没年报，不查了
            return analysisSubjectList;
        }
        String reportDateName = dateList.get(0).getREPORT_DATE_NAME();
        if (!reportDateName.contains(String.valueOf(currYear))) {
            //没年报，不查了
            return analysisSubjectList;
        }
        return null;
    }

    private void pullSave(String stockCode, int year) {
        //1.拉取资产负债表
        List<BalanceSheetEntity> balanceSheetList = dfcfCrawler.downloadBalanceSheet(stockCode, year);

        balanceMapper.delete(deleteSheetWrapper(stockCode));
        for (BalanceSheetEntity dfcfBalance : balanceSheetList) {
            BalanceEntity balance = new BalanceEntity();
            setValue(dfcfBalance, balance, BalanceEntity.class);
            balanceMapper.insert(balance);
        }
        //2.拉取利润表
        List<IncomeSheetEntity> incomeSheetList = dfcfCrawler.downloadIncomeSheet(stockCode, year);

        incomeMapper.delete(deleteSheetWrapper(stockCode));
        for (IncomeSheetEntity dfcfIncome : incomeSheetList) {
            IncomeEntity income = new IncomeEntity();
            setValue(dfcfIncome, income, IncomeEntity.class);
            incomeMapper.insert(income);
        }
        //3.拉取现金流量表
        List<CashFlowSheetEntity> cashflowList = dfcfCrawler.downloadCashSheet(stockCode, year);

        cashFlowMapper.delete(deleteSheetWrapper(stockCode));
        for (CashFlowSheetEntity dfcfCashflow : cashflowList) {
            CashFlowEntity cashFlow = new CashFlowEntity();
            setValue(dfcfCashflow, cashFlow, CashFlowEntity.class);
            cashFlowMapper.insert(cashFlow);
        }
    }

    private <T> QueryWrapper<T> getQueryWrapper(String stockCode, int year) {
        QueryWrapper<T> asQuery = new QueryWrapper<>();
        asQuery.eq("stock_code", stockCode);
        asQuery.orderByAsc("year");
        asQuery.last(String.format(" limit %d ", year));
        return asQuery;
    }

    private <T> QueryWrapper<T> deleteSheetWrapper(String stockCode) {
        QueryWrapper<T> asQuery = new QueryWrapper<>();
        asQuery.eq("stock_code", stockCode);
        return asQuery;
    }

    private void analysis(List<BalanceEntity> balanceList, List<IncomeEntity> incomeList, List<CashFlowEntity> cashFlowList
            , List<LnfhrzItemEntity> bonusList, List<ZyzbItemEntity> zyzbList, String stockCode) {
        List<AnalysisSubject> list = new ArrayList<>();
        for (BalanceEntity balance : balanceList) {
            AnalysisSubject as = new AnalysisSubject();
            as.setYear(balance.getYear());
            as.setStock_code(balance.getStock_code());
            as.setStock_name(balance.getStock_name());

            Optional<IncomeEntity> incomeOpt = incomeList.stream().filter(m -> m.getYear().equals(balance.getYear())).findFirst();
            IncomeEntity income = incomeOpt.orElse(new IncomeEntity());

            Optional<CashFlowEntity> cashFlowOptional = cashFlowList.stream().filter(m -> m.getYear().equals(balance.getYear())).findFirst();
            CashFlowEntity cashFlow = cashFlowOptional.orElse(new CashFlowEntity());

            Optional<LnfhrzItemEntity> bonusOptional = bonusList.stream().filter(m -> m.getSTATISTICS_YEAR().equals(String.valueOf(balance.getYear()))).findFirst();
            LnfhrzItemEntity bonus = bonusOptional.orElse(new LnfhrzItemEntity());

            Optional<ZyzbItemEntity> zyzbOptional = zyzbList.stream().filter(m -> m.getREPORT_DATE().contains(String.valueOf(balance.getYear()))).findFirst();
            ZyzbItemEntity zyzb = zyzbOptional.orElse(new ZyzbItemEntity());

            //第1步：看总资产，判断公司实力及扩张能力。
            as.setTotal_assets(balance.getTotal_assets());
            as.setTotal_assets_yoy(balance.getTotal_assets_yoy());

            //第2步：看资产负债率，判断公司的债务风险。
            as.setTotal_liab(balance.getTotal_liab());
            as.setAsset_liab_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getTotal_liab(), as.getTotal_assets(), 4), handred));

            //第3步：看有息负债和准货币资金，排除偿债风险。
            as.setCurrency_funds(balance.getCurrency_funds());
            as.setTradable_fnncl_assets(balance.getTradable_fnncl_assets());
            //as.setOthr_current_assets_lccp();
            //as.setTrade_financial_jgxck();
            //准货币资金=货币资金+交易性金融资产+其他流动资产里的理财产品+其他流动资产里的结构性存款；',
            as.setCurrency_funds_plus(DecimalUtil.add(as.getCurrency_funds(), as.getTradable_fnncl_assets()));
            as.setSt_loan(balance.getSt_loan());
            as.setNoncurrent_liab_due_in1y(balance.getNoncurrent_liab_due_in1y());
            as.setLt_loan(balance.getLt_loan());
            as.setBond_payable(balance.getBond_payable());
            as.setLt_payable(balance.getLt_payable());
            //有息负债=短期借款＋一年内到期的非流动负债＋长期借款＋应付债券＋长期应付款；
            as.setTotal_liab_with_interest(DecimalUtil.add(as.getSt_loan(), as.getNoncurrent_liab_due_in1y(), as.getLt_loan(), as.getBond_payable(), as.getLt_payable()));
            as.setLiab_zhbzj_with_yxfz_diff(DecimalUtil.subtract(as.getCurrency_funds_plus(), as.getTotal_liab_with_interest()));
            //货币资金减短期有息负债的差额 = 货币资金 — 短期借款 — 一年内到期的非流动负债；
            as.setTotal_st_liab(DecimalUtil.add(as.getSt_loan(), as.getNoncurrent_liab_due_in1y()));
            as.setCurrency_funds_with_dqfz_diff(DecimalUtil.subtract(as.getCurrency_funds(), as.getTotal_st_liab()));

            //第4步：看应收应付和预付预收，判断公司的行业地位。
            as.setBill_payable(balance.getBill_payable());
            as.setAccounts_payable(balance.getAccounts_payable());
            as.setPre_receivable(balance.getPre_receivable());
            as.setContract_liabilities(balance.getContract_liabilities());
            //应付预收合计=应付票据+应付账款+预收款项+合同负债；
            as.setTotal_ap_bp_pr(DecimalUtil.add(as.getBill_payable(), as.getAccounts_payable(), as.getPre_receivable(), as.getContract_liabilities()));

            as.setBills_receivable(balance.getBills_receivable());
            as.setAccount_receivable(balance.getAccount_receivable());
            as.setFinance_rece(balance.getFinance_rece());
            as.setPre_payment(balance.getPre_payment());
            as.setContractual_assets(balance.getContractual_assets());
            //应收预付合计=应收票据+应收账款+应收款项融资+预付款项+合同资产；
            as.setTotal_ar_br(DecimalUtil.add(as.getBills_receivable(), as.getAccount_receivable(), as.getPre_payment(), as.getContractual_assets()));
            as.setAp_bp_pr_with_ar_br_diff(DecimalUtil.subtract(as.getTotal_ap_bp_pr(), as.getTotal_ar_br()));
            as.setAccount_receivable_ratio(DecimalUtil.multiply(DecimalUtil.divide(DecimalUtil.add(as.getAccount_receivable(), as.getContractual_assets()), as.getTotal_assets(), 4), handred));
            as.setOnly_account_receivable_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getAccount_receivable(), as.getTotal_assets(), 4), handred));

            //第5步：看固定资产，判断公司的轻重。
            as.setFixed_asset(balance.getFixed_asset());
            as.setConstruction_in_process(balance.getConstruction_in_process());
            as.setProject_goods_and_material(balance.getProject_goods_and_material());
            BigDecimal totalFixAssets = DecimalUtil.add(as.getFixed_asset(), as.getConstruction_in_process(), as.getProject_goods_and_material());
            as.setTotal_fixed_asset(totalFixAssets);
            as.setFixed_asset_ratio(DecimalUtil.multiply(DecimalUtil.divide(totalFixAssets, as.getTotal_assets(), 4), handred));

            //第6步：看投资类资产，判断公司的专注程度。
            as.setCreditor_invest(balance.getCreditor_invest());
            as.setOther_creditor_invest(balance.getOther_creditor_invest());
            as.setFvtpl_finassets(balance.getFvtpl_finassets());
            as.setSaleable_finacial_assets(balance.getSaleable_finacial_assets());
            as.setHeld_to_maturity_invest(balance.getHeld_to_maturity_invest());
            as.setLt_equity_invest(balance.getLt_equity_invest());
            as.setInvest_property(balance.getInvest_property());
            as.setOthr_equity_instruments(balance.getOthr_equity_instruments());
            as.setOther_illiquid_fnncl_assets(balance.getOther_illiquid_fnncl_assets());

            BigDecimal totalFnncl = DecimalUtil.add(as.getCreditor_invest(), as.getOther_creditor_invest(),
                    as.getFvtpl_finassets(), as.getSaleable_finacial_assets(),
                    as.getHeld_to_maturity_invest(), as.getLt_equity_invest(), as.getInvest_property(),
                    as.getOthr_equity_instruments(), as.getOther_illiquid_fnncl_assets()
            );
            as.setTotal_fnncl_assets(totalFnncl);
            as.setTotal_fnncl_assets_ratio(DecimalUtil.multiply(DecimalUtil.divide(totalFnncl, as.getTotal_assets(), 4), handred));

            as.setInventory(balance.getInventory());
            as.setInventory_with_assets_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getInventory(), as.getTotal_assets(), 4), handred));
            as.setGoodwill(balance.getGoodwill());
            as.setGoodwill_with_assets_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getGoodwill(), as.getTotal_assets(), 4), handred));

            //第7步：看营业收入，判断公司的行业地位及成长能力。
            as.setOperate_income(income.getOperate_income());
            as.setOperate_income_yoy(income.getOperate_income_yoy());
            as.setCash_received_of_sales_service(cashFlow.getSales_services());
            as.setCash_received_of_sales_service_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getCash_received_of_sales_service(), as.getOperate_income(), 4), handred));

            //第8步：看毛利率，判断公司产品的竞争力。
            as.setOperating_costs(income.getOperate_cost());
            as.setGross_profit(DecimalUtil.subtract(as.getOperate_income(), as.getOperating_costs()));
            as.setGross_profit_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getGross_profit(), as.getOperate_income(), 4), handred));

            //第9步：看费用率，判断公司成本管控能力。
            as.setSales_fee(income.getSales_fee());
            as.setManage_fee(income.getManage_fee());
            as.setRad_cost(income.getResearch_expense());
            as.setFinancing_expenses(income.getFinance_expense());
            if (as.getFinancing_expenses().compareTo(BigDecimal.ZERO) < 0) {
                as.setTotal_fee(DecimalUtil.add(as.getSales_fee(), as.getManage_fee(), as.getRad_cost()));
            } else {
                as.setTotal_fee(DecimalUtil.add(as.getSales_fee(), as.getManage_fee(), as.getRad_cost(), as.getFinancing_expenses()));
            }
            as.setTotal_fee_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getTotal_fee(), as.getOperate_income(), 4), handred));
            as.setTotal_fee_with_mll_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getTotal_fee_ratio(), as.getGross_profit_ratio(), 4), handred));

            //销售费用/营业收入
            as.setSales_fee_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getSales_fee(), as.getOperate_income(), 4), handred));

            //第10步：看主营利润，判断公司的盈利能力及利润质量。
            ////主营利润 = 营业收入 - 营业成本 - 税金及附加 - 四费合计
            as.setOperating_taxes_and_surcharge(income.getOperate_tax_add());
            BigDecimal fee = DecimalUtil.add(as.getOperating_costs(), as.getOperating_taxes_and_surcharge(), as.getTotal_fee());
            as.setMain_profit(DecimalUtil.subtract(as.getOperate_income(), fee));
            //主营利润率(主营占营业收入比率) "大于15%，说明主营盈利还可以。小于15%，公司不具备持续的竞争力，淘汰。"
            as.setMain_profit_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getMain_profit(), as.getOperate_income(), 4), handred));
            as.setOperate_profit(income.getTotal_profit());
            //'主营利润占利润总额的比率'
            as.setMain_profit_with_profit_total_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getMain_profit(), as.getOperate_profit(), 4), handred));

            //第11步：看净利润，判断公司的经营成果及含金量。
            as.setNet_profit(income.getNetprofit());
            as.setNcf_from_oa(cashFlow.getNetcash_operate());
            as.setNetcash_operate_yoy(cashFlow.getNetcash_operate_yoy());
            //净利润现金比率 = 经营活动产生的现金流量净额 / 净利润
            as.setNet_profit_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getNcf_from_oa(), as.getNet_profit(), 4), handred));

            //第12步：看归母净利润，判断公司自有资本的获利能力。
            as.setNet_profit_atsopc(income.getParent_netprofit());
            as.setParent_netprofit_yoy(income.getParent_netprofit_yoy());
            //ROE=归属于母公司股东的净利润/归属于母公司所有者权益合计
            //as.setRoe(DecimalUtil.multiply(DecimalUtil.divide(as.getNet_profit_atsopc(), balance.getTotal_quity_atsopc(), 4), handred));
            as.setRoe(zyzb.getROEJQ());
            //第13步：看购建固定资产、无形资产和其他长期资产支付的现金，判断公司未来的成长能力。
            as.setCash_paid_for_assets(cashFlow.getConstruct_long_asset());
            as.setBuy_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getCash_paid_for_assets(), as.getNcf_from_oa(), 4), handred));

            //第14步：看分红，判断公司的品质。
            if (bonus.getTOTAL_DIVIDEND() != null) {
                as.setBonus_fee(bonus.getTOTAL_DIVIDEND());
                //年度股利支付率=年度累计分红总额/归属于母公司股东的净利润
                as.setBonus_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getBonus_fee(), as.getNet_profit_atsopc(), 4), handred));
            }
            //第15步：看三大活动现金流量净额的组合类型，选出最佳类型的公司。
            as.setNcf_from_ia(cashFlow.getNetcash_invest());
            as.setNcf_from_fa(cashFlow.getNetcash_finance());
            String type = "";
            if (DecimalUtil.greaterOrEquals(as.getNcf_from_oa(), BigDecimal.ZERO)) {
                type += "正";
            } else {
                type += "负";
            }
            if (DecimalUtil.greaterOrEquals(as.getNcf_from_ia(), BigDecimal.ZERO)) {
                type += "正";
            } else {
                type += "负";
            }
            if (DecimalUtil.greaterOrEquals(as.getNcf_from_fa(), BigDecimal.ZERO)) {
                type += "正";
            } else {
                type += "负";
            }
            as.setType(type);
            as.setOpinion_type(balance.getOpinion_type());
            list.add(as);
        }
        if (CollUtil.isNotEmpty(list) && list.size() > 1) {
            //计算波动
            for (int i = 1; i < list.size(); i++) {
                //毛利率波动幅度=（本年毛利率-上年毛利率）/上年毛利率
                BigDecimal currYearGross = list.get(i).getGross_profit_ratio();
                BigDecimal preYearGross = list.get(i - 1).getGross_profit_ratio();
                list.get(i).setGross_profit_ratio_wave(DecimalUtil.multiply(DecimalUtil.divide(DecimalUtil.subtract(currYearGross, preYearGross), preYearGross, 4), handred));
            }
            analysisSubjectMapper.delete(deleteSheetWrapper(stockCode));
            list.forEach(m -> {
                analysisSubjectMapper.insert(m);
            });
        }
    }


    private <T> Map<String, Field> getClassFields(Class<T> tClass) {
        Map<String, Field> mapField = new HashMap<>();
        Field[] declaredFields = tClass.getDeclaredFields();
        for (Field field : declaredFields) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation != null) {
                field.setAccessible(true);
                mapField.put(annotation.name(), field);
            }
        }
        return mapField;
    }

    @SuppressWarnings("unchecked")
    private <F, T> void setValue(F from, T output, Class<T> tClass) {
        Map<String, Field> fromMapField = getClassFields(from.getClass());

        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                Column annotation = field.getAnnotation(Column.class);
                if (annotation == null) {
                    continue;
                }
                field.setAccessible(true);
                //如果字段名称是年份或者代码需要特殊处理
                String fieldName = field.getName();
                String fieldTypeName = field.getGenericType().getTypeName();
                if (fieldName.equals("year")) {
                    Field fromField = fromMapField.get(annotation.name());
                    String reportDate = fromField.get(from).toString();
                    Integer o1 = Integer.parseInt(reportDate.substring(0, 4));
                    field.set(output, o1);
                    continue;
                }
                if (fieldName.equals("stock_code")) {
                    Field fromField = fromMapField.get(annotation.name());
                    String stockCode = (String) fromField.get(from);
                    int index = stockCode.indexOf(".");
                    stockCode = stockCode.substring(index + 1) + stockCode.substring(0, index);
                    field.set(output, stockCode);
                    continue;
                }
                if (fieldTypeName.equals("java.lang.String")) {
                    Field fromField = fromMapField.get(annotation.name());
                    String o1 = (String) fromField.get(from);
                    field.set(output, o1);
                    continue;
                }
                if (fieldTypeName.equals("java.math.BigDecimal")) {
                    Field fromField = fromMapField.get(annotation.name());
                    if (fromField != null) {
                        BigDecimal o1 = (BigDecimal) fromField.get(from);
                        field.set(output, o1);
                    }
                }
            } catch (IllegalAccessException e) {
                log.info("获取字段的值出错", e);
            }
        }
    }
}