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

    //每15天同步一次
    @Scheduled(cron = "0 0 0 1,15 * ?")
    public void syncStock() {
        log.info("syncStock同步时间：{}",new Date());
        List<Stock> needStockList = new ArrayList<>();

        //1、筛选股本数大于0的股票
        List<String> stockList = dfcfCrawler.getStockList();
        for (String stockInfo : stockList) {
            Stock stock = assembleStock(stockInfo);
            if (stock.getStock_code().contains("BJ")) continue;
            log.info("stockInfo={}", stockInfo);

            //2、拉取简介
            OperateRangeEntity businessAnalysis = dfcfCrawler.getBusinessAnalysis(stock.getStock_code());
            if (businessAnalysis != null) {
                assembleOperateRange(businessAnalysis, stock);
            }
            needStockList.add(stock);
        }
        //3、同步行业
        needStockList.forEach(stock -> {
            if (StrUtil.isEmpty(stock.getIndustry())) {
                List<IndustryRank> industryRankList = tongHuaSunCrawler.getIndustryRankList(stock.getStockCodeNoArea());
                if (CollUtil.isNotEmpty(industryRankList)) {
                    industryRankList.forEach(rank -> {
                        Optional<Stock> opt = needStockList.stream().filter(item -> item.getStockCodeNoArea().equals(rank.getStockCode())).findFirst();
                        if(opt.isPresent()){
                            Stock tmpStock = opt.get();
                            tmpStock.setIndustry(rank.getIndustry());
                            tmpStock.setNetfit(rank.getNetfit());
                            tmpStock.setTotal_assets(rank.getTotal_assets());
                            tmpStock.setTotal_revenue(rank.getTotal_revenue());
                        }
                    });
                }
            }
        });
        //4、先删后增
        stockMapper.delete(Wrappers.emptyWrapper());
        needStockList.forEach(stock -> {
            stockMapper.insert(stock);
        });
        log.info("同步stock完成");
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

    public List<Stock> getStockList() {
        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("stock_name", "stock_code");
        return stockMapper.selectList(queryWrapper);
    }

    /**
     * 按照东方财富的格式组装股票代码
     *
     * @param stockInfo 格式："1,870204,沪江材料,22765700"
     * @return Stock
     */
    public Stock assembleStock(String stockInfo) {
        Stock stock = new Stock();
        String[] stockInfos = stockInfo.split(",");

        stock.setStockCodeNoArea(stockInfos[1]);
        stock.setStock_name(stockInfos[2]);
        stock.setTotal_shares(new BigDecimal(stockInfos[3]));

        String newCode = stockInfos[1];
        String codeFirstChar = newCode.substring(0, 1);
        switch (codeFirstChar) {
            case "0":
            case "3":
                newCode = "SZ" + newCode;
                break;
            case "6":
                newCode = "SH" + newCode;
                break;
            case "4":
            case "8":
                newCode = newCode + ".BJ";
                break;
        }
        stock.setStock_code(newCode);
        return stock;
    }

    public void assembleOperateRange(OperateRangeEntity businessAnalysis, Stock stock) {
        if (CollUtil.isNotEmpty(businessAnalysis.getZyfw())) {
            String businessScope = businessAnalysis.getZyfw().get(0).getBUSINESS_SCOPE();
            if (StrUtil.isEmpty(businessScope)) {
                businessScope = businessAnalysis.getZyfw().get(0).getMs();
            }
            stock.setOperate_range(businessScope);
        }
        if (CollUtil.isNotEmpty(businessAnalysis.getJyps())) {
            String businessReview = businessAnalysis.getJyps().get(0).getBUSINESS_REVIEW();
            if (StrUtil.isEmpty(businessReview)) {
                businessReview = businessAnalysis.getZyfw().get(0).getMs();
            }
            stock.setOperate_desc(businessReview);
        }
    }
}
