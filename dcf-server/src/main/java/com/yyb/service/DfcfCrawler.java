package com.yyb.service;


import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yyb.entity.dfcf.*;
import com.yyb.utils.JsonUtil;
import com.yyb.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

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
     * 获取日期
     */
    public List<DateItemEntity> getDateList(String stockCode) {
        String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/NewFinanceAnalysis/zcfzbDateAjaxNew?companyType=4&reportDateType=1&code={0}", stockCode);
        String s = restTemplateUtils.httpGet(url);
        RestResultEntity<DateItemEntity> body = restTemplateUtils.httpGetRestResultEntity(url, new ParameterizedTypeReference<RestResultEntity<DateItemEntity>>() {
        });
        return body == null ? Collections.emptyList() : body.getData();
    }

    //获取最近6年的资产负债表
    @SuppressWarnings("unchecked")
    public List<BalanceSheetEntity> downloadBalanceSheet(String stockCode,int year) {
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
    }

    //获取最近6年的利润表
    @SuppressWarnings("unchecked")
    public List<IncomeSheetEntity> downloadIncomeSheet(String stockCode,int year) {
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
    }

    //获取最近6年的资产负债表
    @SuppressWarnings("unchecked")
    public List<CashFlowSheetEntity> downloadCashSheet(String stockCode,int year) {
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
    }

    public OperateRangeEntity getBusinessAnalysis(String stockCode){
        String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/BusinessAnalysis/BusinessAnalysisAjax?code={0}",stockCode);
        OperateRangeEntity body = restTemplateUtils.httpGet(url, new ParameterizedTypeReference<OperateRangeEntity>() {});
        return body;
    }

    public List<LnfhrzItemEntity> getBonusFinancing(String stockCode){
        String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/BonusFinancing/PageAjax?code={0}",stockCode);
        LnfhrzEntity body = restTemplateUtils.httpGet(url, new ParameterizedTypeReference<LnfhrzEntity>() {});
        return body.getLnfhrz();
    }


    public List<ZyzbItemEntity> getZYZB(String stockCode){
        String url = MessageFormat.format("https://emweb.eastmoney.com/PC_HSF10/NewFinanceAnalysis/ZYZBAjaxNew?type=1&code={0}",stockCode);
        RestResultEntity<ZyzbItemEntity> body = restTemplateUtils.httpGet(url, new ParameterizedTypeReference<RestResultEntity<ZyzbItemEntity>>() {});
        return body.getData();
    }


}
