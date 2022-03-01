package com.yyb.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yyb.dto.IndustryRank;
import com.yyb.entity.Stock;
import com.yyb.entity.dfcf.OperateRangeEntity;
import com.yyb.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class StockService {
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private TongHuaSunCrawler tongHuaSunCrawler;
    @Autowired
    private DfcfCrawler dfcfCrawler;

    //每月1号同步一次
    @Scheduled(cron = "0 0 1 1 * ?")
    public void syncStock() {
        stockMapper.delete(Wrappers.emptyWrapper());
        List<String> stockList = dfcfCrawler.getStockList();
        //"1,870204,沪江材料,22765700"
        for (String stockInfo : stockList) {
            String[] stockInfos = stockInfo.split(",");
            String stockCode = getStockCode(stockInfos[1]);
            if(stockCode.contains("BJ")) continue;

            Stock stock = new Stock();
            stock.setStock_code(stockCode);
            stock.setStock_name(stockInfos[2]);
            Stock tmpStock = getStockByCode(stock.getStock_code());
            if (tmpStock == null) {
                //同步行业
                List<IndustryRank> industryRankList = tongHuaSunCrawler.getIndustryRankList(stockInfos[1]);
                if (CollUtil.isNotEmpty(industryRankList)) {
                    industryRankList.forEach(rank -> {
                        Stock tempStock = getStockByCode(rank.getStockCode());
                        if (tempStock == null) {
                            tempStock = new Stock();
                            BeanUtils.copyProperties(rank, tempStock);
                            tempStock.setStock_name(rank.getStockName());
                            tempStock.setStock_code(rank.getStockCode());
                            OperateRangeEntity businessAnalysis = null;
                            try {
                                //拉取简介
                                businessAnalysis = dfcfCrawler.getBusinessAnalysis(rank.getStockCode());
                            } catch (Exception e) {
                                log.info("获取简介失败,{}，stock_code={}", e, tempStock.getStock_code());
                            }
                            if (businessAnalysis != null) {
                                if (CollUtil.isNotEmpty(businessAnalysis.getZyfw())) {
                                    tempStock.setOperate_range(businessAnalysis.getZyfw().get(0).getBUSINESS_SCOPE());
                                    if (StrUtil.isEmpty(tempStock.getOperate_range())) {
                                        tempStock.setOperate_range(businessAnalysis.getZyfw().get(0).getMs());
                                    }
                                }
                                if (CollUtil.isNotEmpty(businessAnalysis.getJyps())) {
                                    tempStock.setOperate_desc(businessAnalysis.getJyps().get(0).getBUSINESS_REVIEW());
                                    if (StrUtil.isEmpty(tempStock.getOperate_desc())) {
                                        tempStock.setOperate_desc(businessAnalysis.getZyfw().get(0).getMs());
                                    }
                                }
                            }
                            //拉取股本
                            BigDecimal totalShares = dfcfCrawler.getTotalShares(tempStock.getStock_code());
                            tempStock.setTotal_shares(totalShares);
                            stockMapper.insert(tempStock);

                        }
                    });
                }
            }
        }
    }

    public List<Stock> selectByKeyword(String keyword) {
        if (StrUtil.isEmpty(keyword)) return Collections.emptyList();
        return stockMapper.selectByKeyword(keyword);
    }

    public List<Stock> getStockByName(String stockName) {
        if (StrUtil.isEmpty(stockName)) return Collections.emptyList();
        List<Stock> stocks = stockMapper.selectByName(stockName);
        if (CollUtil.isNotEmpty(stocks)) {
            long num = stocks.stream().filter(m -> m.getStock_name().equals(stockName)).count();
            if (num == 0) {
                QueryWrapper<Stock> wrapper = new QueryWrapper<>();
                wrapper.eq("stock_name", stockName);
                List<Stock> tmpStock = stockMapper.selectList(wrapper);
                stocks.addAll(tmpStock);
            }
        }
        return stocks;
    }

    private Stock getStockByCode(String stockCode) {
        if (StrUtil.isEmpty(stockCode)) return null;
        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stock_code", stockCode);
        List<Stock> list = stockMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list)) return list.get(0);
        return null;
    }

    public List<Stock> getStockList() {
        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("stock_name", "stock_code");
        return stockMapper.selectList(queryWrapper);
    }

    public String getStockCode(String code) {
        String newCode = code;
        String codeFirstChar = code.substring(0, 1);
        if (codeFirstChar.equals("0") || codeFirstChar.equals("3")) {
            newCode = "SZ" + code;
        } else if (codeFirstChar.equals("6")) {
            newCode = "SH" + code;
        }
        if (codeFirstChar.equals("4") || codeFirstChar.equals("8")) {
            newCode = code + ".BJ";
        }
        return newCode;
    }
}
