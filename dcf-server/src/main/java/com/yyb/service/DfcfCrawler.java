package com.yyb.service;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.yyb.entity.dfcf.*;
import com.yyb.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 从东方财富获取数据
 */
@Service
@Slf4j
public class DfcfCrawler {
    @Autowired
    private RestTemplateUtils restTemplateUtils;

    /**
     * 获取筛选股本数大于0的股票
     */
    public List<String> getStockList() {
        try {
            String url = "https://xuanguapi.eastmoney.com/Stock/JS.aspx?type=xgq&sty=xgq&token=eastmoney&c=[cz_gbzb01(4|0w)]&p=1&jn=RkQAkazk&ps=10000&s=cz_gbzb01(4|0w)&st=1&r=1641114468168";
            String s = restTemplateUtils.httpGet(url);
            if (StringUtils.isEmpty(s)) return Collections.emptyList();
            s = s.substring(s.indexOf("{"));
            StockWrapEntity body = JSONUtil.toBean(s, StockWrapEntity.class);
            return body == null ? Collections.emptyList() : body.getResults();
        } catch (Exception e) {
            log.error("DfcfCrawler.getStockList", e);
            return Collections.emptyList();
        }
    }

    /**
     * 获取日期
     */
    public List<DateItemEntity> getDateList(String stockCode) {
        try {
            String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/NewFinanceAnalysis/zcfzbDateAjaxNew?companyType=4&reportDateType=1&code={0}", stockCode);
            String s = restTemplateUtils.httpGet(url);
            RestResultEntity<DateItemEntity> body = restTemplateUtils.httpGetRestResultEntity(url, new ParameterizedTypeReference<RestResultEntity<DateItemEntity>>() {
            });
            return body == null ? Collections.emptyList() : body.getData();
        } catch (Exception e) {
            log.error("DfcfCrawler.getDateList", e);
            return Collections.emptyList();
        }
    }

    //获取最近6年的资产负债表
    @SuppressWarnings("unchecked")
    public List<BalanceSheetEntity> downloadBalanceSheet(String stockCode, int year) {
        try {
            List<DateItemEntity> dateList = getDateList(stockCode);
            if (CollUtil.isEmpty(dateList)) return Collections.emptyList();

            String comma = ",";
            List<String> tmpList = new ArrayList<>();
            for (int i = 0; i < dateList.size(); i++) {
                if (i >= year) break;
                tmpList.add(dateList.get(i).getREPORT_DATE().substring(0, 10));
            }
            String date = String.join(comma, tmpList);
            String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/NewFinanceAnalysis/zcfzbAjaxNew?companyType=4&reportDateType=1&reportType=1&dates={0}&code={1}", date, stockCode);
            RestResultEntity<BalanceSheetEntity> body = restTemplateUtils.httpGetRestResultEntity(url, new ParameterizedTypeReference<RestResultEntity<BalanceSheetEntity>>() {
            });
            return body == null ? Collections.emptyList() : body.getData();
        } catch (Exception e) {
            log.error("DfcfCrawler.downloadBalanceSheet", e);
            return Collections.emptyList();
        }
    }

    //获取最近6年的利润表
    @SuppressWarnings("unchecked")
    public List<IncomeSheetEntity> downloadIncomeSheet(String stockCode, int year) {
        try {
            List<DateItemEntity> dateList = getDateList(stockCode);
            if (CollUtil.isEmpty(dateList)) return Collections.emptyList();

            String comma = ",";
            List<String> tmpList = new ArrayList<>();
            for (int i = 0; i < dateList.size(); i++) {
                if (i >= year) break;
                tmpList.add(dateList.get(i).getREPORT_DATE().substring(0, 10));
            }
            String date = String.join(comma, tmpList);
            String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/NewFinanceAnalysis/lrbAjaxNew?companyType=4&reportDateType=1&reportType=1&dates={0}&code={1}", date, stockCode);
            RestResultEntity<IncomeSheetEntity> body = restTemplateUtils.httpGetRestResultEntity(url, new ParameterizedTypeReference<RestResultEntity<IncomeSheetEntity>>() {
            });
            return body == null ? Collections.emptyList() : body.getData();
        } catch (Exception e) {
            log.error("DfcfCrawler.downloadIncomeSheet", e);
            return Collections.emptyList();
        }
    }

    //获取最近6年的资产负债表
    @SuppressWarnings("unchecked")
    public List<CashFlowSheetEntity> downloadCashSheet(String stockCode, int year) {
        try {
            List<DateItemEntity> dateList = getDateList(stockCode);
            if (CollUtil.isEmpty(dateList)) return Collections.emptyList();

            String comma = ",";
            List<String> tmpList = new ArrayList<>();
            for (int i = 0; i < dateList.size(); i++) {
                if (i >= year) break;
                tmpList.add(dateList.get(i).getREPORT_DATE().substring(0, 10));
            }
            String date = String.join(comma, tmpList);
            String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/NewFinanceAnalysis/xjllbAjaxNew?companyType=4&reportDateType=1&reportType=1&dates={0}&code={1}", date, stockCode);
            RestResultEntity<CashFlowSheetEntity> body = restTemplateUtils.httpGetRestResultEntity(url, new ParameterizedTypeReference<RestResultEntity<CashFlowSheetEntity>>() {
            });
            return body == null ? Collections.emptyList() : body.getData();
        } catch (Exception e) {
            log.error("DfcfCrawler.downloadCashSheet", e);
            return Collections.emptyList();
        }
    }

    public OperateRangeEntity getBusinessAnalysis(String stockCode) {
        try {
            String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/BusinessAnalysis/PageAjax?code={0}", stockCode);
            OperateRangeEntity body = restTemplateUtils.httpGet(url, new ParameterizedTypeReference<OperateRangeEntity>() {
            });
            return body;
        } catch (Exception e) {
            log.info("获取简介失败,{}，stock_code={}", e, stockCode);
            return null;
        }
    }

    /**
     * 查询分红
     */
    public List<LnfhrzItemEntity> getBonusFinancing(String stockCode) {
        try {
            String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/BonusFinancing/PageAjax?code={0}", stockCode);
            LnfhrzEntity body = restTemplateUtils.httpGet(url, new ParameterizedTypeReference<LnfhrzEntity>() {
            });
            return body.getLnfhrz();
        } catch (Exception e) {
            log.error("DfcfCrawler.getBonusFinancing", e);
            return Collections.emptyList();
        }
    }

    /**
     * 查询主要指标
     */
    public List<ZyzbItemEntity> getZYZB(String stockCode) {
        try {
            String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/NewFinanceAnalysis/ZYZBAjaxNew?type=1&code={0}", stockCode);
            RestResultEntity<ZyzbItemEntity> body = restTemplateUtils.httpGet(url, new ParameterizedTypeReference<RestResultEntity<ZyzbItemEntity>>() {
            });
            return body.getData();
        } catch (Exception e) {
            log.error("DfcfCrawler.getZYZB", e);
            return Collections.emptyList();
        }
    }
}
