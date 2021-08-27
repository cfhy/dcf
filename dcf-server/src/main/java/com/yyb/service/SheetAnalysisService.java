package com.yyb.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yyb.dto.TypeEnum;
import com.yyb.entity.*;
import com.yyb.entity.tonghuashun.BalanceSheetEntity;
import com.yyb.entity.tonghuashun.CashFlowSheetEntity;
import com.yyb.entity.tonghuashun.IncomeSheetEntity;
import com.yyb.entity.xueqiu.*;
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
    private XueQiuCrawler xueQiuCrawler;
    @Autowired
    private TongHuaSunCrawler tongHuaSunCrawler;

    public static final BigDecimal handred=new BigDecimal(100);

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
        QueryWrapper<AnalysisSubject> asQuery = new QueryWrapper<>();
        asQuery.eq("stock_code", stockCode);
        asQuery.orderByAsc("year");
        List<AnalysisSubject> analysisSubjectList = analysisSubjectMapper.selectList(asQuery);

        if (CollUtil.isEmpty(analysisSubjectList)) {
            synchronized (SheetAnalysisService.class) {
                if (CollUtil.isEmpty(analysisSubjectList)) {
                    //1、拉取存数据库
                    StockBasicInfoQuoteEntity stockBasicInfo = xueQiuCrawler.getStockBasicInfo(stockCode);
                    String stockName = stockBasicInfo.getName();
                    storeBalance(stockCode, stockName);
                    storeIncome(stockCode, stockName);
                    storeCashFlow(stockCode, stockName);
                    //2、分析，结果存数据库
                    List<BalanceEntity> balanceList = balanceMapper.selectList(getQueryWrapper(new QueryWrapper<>(), stockCode));
                    List<IncomeEntity> incomeList = incomeMapper.selectList(getQueryWrapper(new QueryWrapper<>(), stockCode));
                    List<CashFlowEntity> cashFlowList = cashFlowMapper.selectList(getQueryWrapper(new QueryWrapper<>(), stockCode));
                    List<MainIndicatorItemEntity> mainIndicatorList = xueQiuCrawler.getMainIndicator(stockCode);
                    this.analysis(balanceList, incomeList, cashFlowList, mainIndicatorList, stockCode);
                    analysisSubjectList = analysisSubjectMapper.selectList(asQuery);
                }
            }
        }
        return analysisSubjectList;
    }

    private <T> QueryWrapper<T> getQueryWrapper(QueryWrapper<T> wrapper, String stockCode) {
        wrapper.eq("stock_code", stockCode);
        wrapper.eq("type", 1);
        wrapper.orderByAsc("year");
        return wrapper;
    }

    private void analysis(List<BalanceEntity> balanceList, List<IncomeEntity> incomeList
            , List<CashFlowEntity> cashFlowList, List<MainIndicatorItemEntity> mainIndicatorList, String stockCode) {

        for (BalanceEntity balance : balanceList) {
            QueryWrapper<AnalysisSubject> wrapper = new QueryWrapper<>();
            wrapper.eq("stock_code", stockCode);
            wrapper.eq("year", balance.getYear());
            AnalysisSubject as = analysisSubjectMapper.selectList(wrapper).get(0);

            Optional<IncomeEntity> incomeOpt = incomeList.stream().filter(m -> m.getYear().equals(balance.getYear())).findFirst();
            IncomeEntity income = incomeOpt.orElse(new IncomeEntity());

            Optional<CashFlowEntity> cashFlowOptional = cashFlowList.stream().filter(m -> m.getYear().equals(balance.getYear())).findFirst();
            CashFlowEntity cashFlow = cashFlowOptional.orElse(new CashFlowEntity());

            MainIndicatorItemEntity indicator = null;
            for (MainIndicatorItemEntity indicatorItem : mainIndicatorList) {
                Integer year = DateUtil.year(new Date(indicatorItem.getReport_date()));
                if (balance.getYear().equals(year)) {
                    indicator = indicatorItem;
                    break;
                }
            }
            if (indicator == null) {
                indicator = new MainIndicatorItemEntity();
            }
            //第5步：看总资产，判断公司实力及扩张能力。
            as.setTotal_assets(balance.getTotal_assets());
            //第6步：看资产负债率，判断公司的债务风险。
            as.setTotal_liab(balance.getTotal_liab());
            as.setAsset_liab_ratio(indicator.getAsset_liab_ratio().get(0));
            //第7步：看有息负债和准货币资金，排除偿债风险。
            as.setCurrency_funds(balance.getCurrency_funds());
            as.setSt_loan(balance.getSt_loan());
            as.setNoncurrent_liab_due_in1y(balance.getNoncurrent_liab_due_in1y());
            as.setLt_loan(balance.getLt_loan());
            as.setBond_payable(balance.getBond_payable());
            as.setLt_payable(balance.getLt_payable());
            as.setTotal_liab_with_interest(DecimalUtil.add(as.getSt_loan(), as.getNoncurrent_liab_due_in1y(), as.getLt_loan(), as.getBond_payable(), as.getLt_payable()));
            as.setLiab_zhbzj_with_yxfz_diff(DecimalUtil.subtract(as.getCurrency_funds(), as.getTotal_liab_with_interest()));
            //第8步：看应收应付和预付预收，判断公司的行业地位。
            as.setBill_payable(balance.getBill_payable());
            as.setAccounts_payable(balance.getAccounts_payable());
            as.setPre_receivable(balance.getPre_receivable());
            as.setTotal_ap_bp_pr(DecimalUtil.add(as.getBill_payable(), as.getAccounts_payable(), as.getPre_receivable()));
            as.setBills_receivable(balance.getBills_receivable());
            as.setAccount_receivable(balance.getAccount_receivable());
            as.setPre_payment(balance.getPre_payment());
            as.setTotal_ar_br(DecimalUtil.add(as.getBills_receivable(), as.getAccount_receivable(), as.getPre_payment()));
            as.setAp_bp_pr_with_ar_br_diff(DecimalUtil.subtract(as.getTotal_ap_bp_pr(), as.getTotal_ar_br()));
            as.setAccount_receivable_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getAccount_receivable(), as.getTotal_assets(),4),handred));
            //第9步：看固定资产，判断公司的轻重。
            as.setFixed_asset(balance.getFixed_asset());
            as.setConstruction_in_process(balance.getConstruction_in_process());
            as.setProject_goods_and_material(balance.getProject_goods_and_material());
            BigDecimal totalFixAssets = DecimalUtil.add(as.getFixed_asset(), as.getConstruction_in_process(), as.getProject_goods_and_material());
            as.setTotal_fixed_asset(totalFixAssets);
            as.setFixed_asset_ratio(DecimalUtil.multiply(DecimalUtil.divide(totalFixAssets, as.getTotal_assets(),4),handred));
            //第10步：看投资类资产，判断公司的专注程度。
            as.setTradable_fnncl_assets(balance.getTradable_fnncl_assets());
            as.setSaleable_finacial_assets(balance.getSaleable_finacial_assets());
            as.setOthr_equity_instruments(balance.getOthr_equity_instruments());
            as.setOther_illiquid_fnncl_assets(balance.getOther_illiquid_fnncl_assets());
            as.setHeld_to_maturity_invest(balance.getHeld_to_maturity_invest());
            as.setLt_equity_invest(balance.getLt_equity_invest());
            as.setInvest_property(balance.getInvest_property());
            BigDecimal totalFnncl = DecimalUtil.add(as.getTradable_fnncl_assets(), as.getSaleable_finacial_assets()
                    , as.getOthr_equity_instruments(), as.getOther_illiquid_fnncl_assets(), as.getHeld_to_maturity_invest(),
                    as.getLt_equity_invest(), as.getInvest_property());
            as.setTotal_fnncl_assets(totalFnncl);
            as.setTotal_fnncl_assets_ratio(DecimalUtil.multiply(DecimalUtil.divide(totalFnncl, as.getTotal_assets(),4),handred));
            //第13步：看营业收入，判断公司的行业地位及成长能力。
            as.setRevenue(income.getRevenue());
            as.setCash_received_of_sales_service(cashFlow.getCash_received_of_sales_service());
            as.setCash_received_of_sales_service_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getCash_received_of_sales_service(), as.getRevenue(),4),handred));
            //第14步：看毛利率，判断公司产品的竞争力。
            as.setOperating_costs(income.getOperating_cost());
            as.setRevenue_with_operating_costs_diff(DecimalUtil.subtract(as.getRevenue(), as.getOperating_costs()));
            as.setRevenue_with_operating_costs_diff_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getRevenue_with_operating_costs_diff(), as.getRevenue(),4),handred));
            //第15步：看费用率，判断公司成本管控能力。
            as.setSales_fee(income.getSales_fee());
            as.setManage_fee(income.getManage_fee());
            as.setRad_cost(income.getRad_cost());
            as.setFinancing_expenses(income.getFinancing_expenses());
            if(as.getFinancing_expenses().compareTo(BigDecimal.ZERO)<0){
                as.setTotal_fee(DecimalUtil.add(as.getSales_fee(), as.getManage_fee(), as.getRad_cost()));
            }else{
                as.setTotal_fee(DecimalUtil.add(as.getSales_fee(), as.getManage_fee(), as.getRad_cost(), as.getFinancing_expenses()));
            }
            as.setTotal_fee_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getTotal_fee(), as.getRevenue(),4),handred));
            as.setTotal_fee_with_mll_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getTotal_fee_ratio(), as.getRevenue_with_operating_costs_diff_ratio(),4),handred));
            //第16步：看主营利润，判断公司的盈利能力及利润质量。
            ////主营利润 = 营业收入 - 营业成本 - 税金及附加 - 四费合计
            BigDecimal fee = DecimalUtil.add(income.getOperating_cost(), income.getOperating_taxes_and_surcharge(), as.getTotal_fee());
            as.setMain_profit(DecimalUtil.subtract(as.getRevenue(), fee));
            //主营利润率(主营占营业收入比率) "大于15%，说明主营盈利还可以。小于15%，公司不具备持续的竞争力，淘汰。"
            as.setMain_profit_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getMain_profit(), as.getRevenue(),4),handred));
            as.setProfit_total_amt(income.getProfit_total_amt());
            as.setMain_profit_with_profit_total_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getMain_profit(), as.getProfit_total_amt(),4),handred));
            //第17步：看净利润，判断公司的经营成果及含金量。
            as.setNet_profit(income.getNet_profit());
            as.setNcf_from_oa(cashFlow.getNcf_from_oa());
            //净利润现金比率 = 经营活动产生的现金流量净额 / 净利润
            as.setNet_profit_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getNcf_from_oa(), as.getNet_profit(),4),handred));
            //第18步：看归母净利润，判断公司自有资本的获利能力。
            as.setNet_profit_atsopc(income.getNet_profit_atsopc());
            as.setRoe(indicator.getAvg_roe().get(0));
            //第19步：看经营活动产生的现金流量净额，判断公司的造血能力。
            as.setCash_paid_of_distribution(cashFlow.getCash_paid_of_distribution());
            as.setNcf_from_oa_diff(DecimalUtil.subtract(as.getNcf_from_oa(),as.getCash_paid_of_distribution()));
            //第20步：看购建固定资产、无形资产和其他长期资产支付的现金，判断公司未来的成长能力。
            as.setCash_paid_for_assets(cashFlow.getCash_paid_for_assets());
            as.setBuy_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getCash_paid_for_assets(), as.getNcf_from_oa(),4),handred));
            as.setNet_cash_of_disposal_assets(cashFlow.getNet_cash_of_disposal_assets());
            as.setSale_ratio(DecimalUtil.multiply(DecimalUtil.divide(as.getNet_cash_of_disposal_assets(), as.getCash_paid_for_assets(),4),handred));
            //第21步：看分红，判断公司的品质。

            //第22步：看三大活动现金流量净额的组合类型，选出最佳类型的公司。
            as.setNcf_from_ia(cashFlow.getNcf_from_ia());
            as.setNcf_from_fa(cashFlow.getNcf_from_fa());
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
            //第23步：看现金及现金等价物的净增加额，判断公司的稳定性。
            as.setNet_increase_in_cce(cashFlow.getNet_increase_in_cce());
            UpdateWrapper<AnalysisSubject> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("stock_code", as.getStock_code());
            updateWrapper.eq("year", as.getYear());
            as.setTotal_assets_group_ratio(null);
            as.setNet_profit_atsopc_group_ratio(null);
            as.setRevenue_group_ratio(null);
            analysisSubjectMapper.update(as, updateWrapper);
        }
    }

    private void storeBalance(String stockCode,String stockName) {
        //1.拉取数据
        List<BalanceSheetEntity> thsBalanceSheetList = tongHuaSunCrawler.downloadBalanceSheet(stockCode);
        List<BalanceSheetItemEntity> xqBalanceSheetList = xueQiuCrawler.downloadBalanceSheet(stockCode);

        Map<String, Field> thsMapField = getClassFields(BalanceSheetEntity.class);
        Map<String, Field> xqMapField = getClassFields(BalanceSheetItemEntity.class);

        int size=thsBalanceSheetList.size()>=xqBalanceSheetList.size()?xqBalanceSheetList.size():thsBalanceSheetList.size();
        for (int i = 0; i < size; i++) {
            BalanceSheetEntity thsBalance = thsBalanceSheetList.get(i);
            BalanceSheetItemEntity xqBalanceDto = xqBalanceSheetList.get(i);

            BalanceEntity ths = new BalanceEntity(thsBalance.getYear(), TypeEnum.THS.getCode(),stockName, stockCode);
            BalanceEntity xq = new BalanceEntity(thsBalance.getYear(), TypeEnum.XQ.getCode(),stockName, stockCode);

            AnalysisSubject as = new AnalysisSubject(thsBalance.getYear(), stockName,stockCode);
            as.setTotal_assets_group_ratio(DecimalUtil.multiply(xqBalanceDto.getTotal_assets().get(1),handred));

            setValue(thsMapField, xqMapField, thsBalance, xqBalanceDto, ths, xq, BalanceEntity.class);
            //3.入库
            balanceMapper.insert(ths);
            balanceMapper.insert(xq);
            analysisSubjectMapper.insert(as);
        }
    }

    private void storeIncome(String stockCode,String stockName) {
        //1.拉取数据
        List<IncomeSheetEntity> thsIncomeList = tongHuaSunCrawler.downloadIncomeSheet(stockCode);
        List<IncomeSheetItemEntity> xqIncomeList = xueQiuCrawler.downloadIncomeSheet(stockCode);

        Map<String, Field> thsMapField = getClassFields(IncomeSheetEntity.class);
        Map<String, Field> xqMapField = getClassFields(IncomeSheetItemEntity.class);

        int size=thsIncomeList.size()>=xqIncomeList.size()?xqIncomeList.size():thsIncomeList.size();
        for (int i = 0; i <size; i++) {
            IncomeSheetEntity thsIncome = thsIncomeList.get(i);
            IncomeSheetItemEntity xqIncome = xqIncomeList.get(i);

            IncomeEntity ths = new IncomeEntity(thsIncome.getYear(), TypeEnum.THS.getCode(), stockName,stockCode);
            IncomeEntity xq = new IncomeEntity(thsIncome.getYear(), TypeEnum.XQ.getCode(), stockName,stockCode);
            AnalysisSubject as = new AnalysisSubject(thsIncome.getYear(), stockName,stockCode);
            as.setRevenue_group_ratio(DecimalUtil.multiply(xqIncome.getRevenue().get(1),handred));
            as.setNet_profit_atsopc_group_ratio(DecimalUtil.multiply(xqIncome.getNet_profit_atsopc().get(1),handred));

            setValue(thsMapField, xqMapField, thsIncome, xqIncome, ths, xq, IncomeEntity.class);
            //3.入库
            incomeMapper.insert(ths);
            incomeMapper.insert(xq);
            UpdateWrapper<AnalysisSubject> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("stock_code", as.getStock_code());
            updateWrapper.eq("year", as.getYear());
            analysisSubjectMapper.update(as, updateWrapper);
        }
    }

    private void storeCashFlow(String stockCode,String stockName) {
        //1.拉取数据
        List<CashFlowSheetEntity> thsCashList = tongHuaSunCrawler.downloadCashSheet(stockCode);
        List<CashFlowSheetItemEntity> xqCashList = xueQiuCrawler.downloadCashSheet(stockCode);

        Map<String, Field> thsMapField = getClassFields(CashFlowSheetEntity.class);
        Map<String, Field> xqMapField = getClassFields(CashFlowSheetItemEntity.class);

        int size=thsCashList.size()>=xqCashList.size()?xqCashList.size():thsCashList.size();
        for (int i = 0; i <size; i++) {
            CashFlowSheetEntity thsCash = thsCashList.get(i);
            CashFlowSheetItemEntity xqCash = xqCashList.get(i);

            CashFlowEntity ths = new CashFlowEntity(thsCash.getYear(), TypeEnum.THS.getCode(),stockName, stockCode);
            CashFlowEntity xq = new CashFlowEntity(thsCash.getYear(), TypeEnum.XQ.getCode(),stockName, stockCode);

            setValue(thsMapField, xqMapField, thsCash, xqCash, ths, xq, CashFlowEntity.class);
            //3.入库
            cashFlowMapper.insert(ths);
            cashFlowMapper.insert(xq);
        }
    }

    private <T> Map<String, Field> getClassFields(Class<T> tClass) {
        Map<String, Field> mapField = new HashMap<>();
        Field[] declaredFields = tClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            mapField.put(field.getName(), field);
        }
        return mapField;
    }

    @SuppressWarnings("unchecked")
    private <T, THS, XQ> void setValue(Map<String, Field> thsMapField, Map<String, Field> xqMapField, THS thsInput, XQ xqInput, T thsOutPut, T xqOutPut, Class<T> tClass) {
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getGenericType().getTypeName().equals("java.math.BigDecimal")) {
                continue;
            }
            field.setAccessible(true);
            String fieldName = field.getName();

            try {
                Field thsField = thsMapField.get(fieldName);
                BigDecimal o1 = (BigDecimal) thsField.get(thsInput);
                field.set(thsOutPut, o1);

                Field xqField = xqMapField.get(fieldName);
                if (xqField == null) {
                    continue;
                }
                List<BigDecimal> o2List = (List<BigDecimal>) xqField.get(xqInput);
                field.set(xqOutPut, o2List.get(0));

                if (o2List.get(0) == null && o1 != null) {
                    field.set(xqOutPut, o1);
                }
            } catch (IllegalAccessException e) {
                log.info("获取字段的值出错", e);
            }
        }
    }
}