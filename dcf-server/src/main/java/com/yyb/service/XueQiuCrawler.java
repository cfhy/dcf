package com.yyb.service;

import com.yyb.entity.xueqiu.*;
import com.yyb.utils.RestTemplateUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

@Service
public class XueQiuCrawler implements InitializingBean {
    @Autowired
    private RestTemplateUtils restTemplateUtils;

    private static String cookie = "";

    //每天2，12,22 设置cookie
    @Override
    @Scheduled(cron = "0 0 2,12,22 * * ?")
    public void afterPropertiesSet() {
        String url = "https://xueqiu.com/";
        cookie = restTemplateUtils.getCookies(url);
    }

    //获取股票基本信息
    @SuppressWarnings("unchecked")
    public StockBasicInfoQuoteEntity getStockBasicInfo(String stockCode) {
        String url = MessageFormat.format("https://stock.xueqiu.com/v5/stock/quote.json?symbol={0}&extend=detail", stockCode);
        RestResultEntity<StockBasicInfoDataEntity> body = restTemplateUtils.httpGet(url, cookie
                , new ParameterizedTypeReference<RestResultEntity<StockBasicInfoDataEntity>>() {
                });
        return body == null ? new StockBasicInfoQuoteEntity() : body.getData().getQuote();
    }

    //获取最近5年的资产负债表
    @SuppressWarnings("unchecked")
    public List<BalanceSheetItemEntity> downloadBalanceSheet(String stockCode) {
        String url = MessageFormat.format("https://stock.xueqiu.com/v5/stock/finance/cn/balance.json?symbol={0}&type=Q4&is_detail=true&count=5&timestamp=", stockCode);
        RestResultEntity<BalanceSheetListEntity> body = restTemplateUtils.httpGetRestResultEntity(url, cookie
                , new ParameterizedTypeReference<RestResultEntity<BalanceSheetListEntity>>() {
                });
        return body == null ? Collections.emptyList() : body.getData().getList();
    }

    //获取最近5年的利润表
    @SuppressWarnings("unchecked")
    public List<IncomeSheetItemEntity> downloadIncomeSheet(String stockCode) {
        String url = MessageFormat.format("https://stock.xueqiu.com/v5/stock/finance/cn/income.json?symbol={0}&type=Q4&is_detail=true&count=5&timestamp=", stockCode);
        RestResultEntity<IncomeSheetListEntity> body = restTemplateUtils.httpGetRestResultEntity(url, cookie
                , new ParameterizedTypeReference<RestResultEntity<IncomeSheetListEntity>>() {
                });
        return body == null ? Collections.emptyList() : body.getData().getList();
    }

    //获取最近5年的现金流量表
    @SuppressWarnings("unchecked")
    public List<CashFlowSheetItemEntity> downloadCashSheet(String stockCode) {
        String url = MessageFormat.format("https://stock.xueqiu.com/v5/stock/finance/cn/cash_flow.json?symbol={0}&type=Q4&is_detail=true&count=5&timestamp=", stockCode);
        RestResultEntity<CashFlowSheetListEntity> body = restTemplateUtils.httpGetRestResultEntity(url, cookie
                , new ParameterizedTypeReference<RestResultEntity<CashFlowSheetListEntity>>() {
                });
        return body == null ? Collections.emptyList() : body.getData().getList();
    }

    //获取最近5年的主要指标
    @SuppressWarnings("unchecked")
    public List<MainIndicatorItemEntity> getMainIndicator(String stockCode) {
        String url = MessageFormat.format("https://stock.xueqiu.com/v5/stock/finance/cn/indicator.json?symbol={0}&type=Q4&is_detail=true&count=5&timestamp=", stockCode);
        RestResultEntity<MainIndicatorListEntity> body = restTemplateUtils.httpGetRestResultEntity(url, cookie
                , new ParameterizedTypeReference<RestResultEntity<MainIndicatorListEntity>>() {
                });
        return body == null ? Collections.emptyList() : body.getData().getList();
    }

    //获取自选股
    @SuppressWarnings("unchecked")
    public List<SelectedStockItemEntity> getSelectedStock(String userName) {
        //获取用户ID
        String url = MessageFormat.format("https://xueqiu.com/users/search.json?q={0}&count=5", userName);
        UserInfoListEntity userList = restTemplateUtils.httpGet(url, cookie
                , new ParameterizedTypeReference<UserInfoListEntity>() {
                });
        List<UserInfoEntity> users = userList.getUsers();
        if (CollectionUtils.isEmpty(users)) {
            throw new RuntimeException("该用户不存在");
        }
        String userId = users.get(0).getId();
        url = MessageFormat.format("https://stock.xueqiu.com/v5/stock/portfolio/stock/list.json?pid=-1&category=1&size=1000&uid={0}", userId);
        RestResultEntity<SelectedStockListEntity> body = restTemplateUtils.httpGetRestResultEntity(url, cookie
                , new ParameterizedTypeReference<RestResultEntity<SelectedStockListEntity>>() {
                });
        return body == null ? Collections.emptyList() : body.getData().getStocks();
    }
}
