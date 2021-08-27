package com.yyb.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyb.dto.IndustryRank;
import com.yyb.entity.Stock;
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
import java.util.*;

@Service
@Slf4j
public class StockService {
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private TongHuaSunCrawler tongHuaSunCrawler;

    @Scheduled(cron = "0 0 1 1 * ?")
    public void syncStock() {
        List<String> urlList = Arrays.asList("https://www.banban.cn/gupiao/list_sh.html"
                , "https://www.banban.cn/gupiao/list_sz.html"
                , "https://www.banban.cn/gupiao/list_cyb.html");
        for (int i = 0; i < urlList.size(); i++) {
            String prefix = i == 0 ? "SH" : "SZ";
            try {
                Document document = Jsoup.connect(urlList.get(i)).get();
                Elements aTagList = document.select("#ctrlfscont a");
                //<a href="/gupiao/601598/">中国外运(601598)</a>
                for (Element element : aTagList) {
                    Stock stock = new Stock();
                    String text = element.text().replace("(", "").replace(")", "");
                    stock.setStock_code(prefix + text.substring(text.length() - 6));
                    stock.setStock_name(text.substring(0, text.length() - 6));
                    Stock tmpStock = getStockByCode(stock.getStock_code());
                    if (tmpStock == null) {
                        //同步行业
                        List<IndustryRank> industryRankList = tongHuaSunCrawler.getIndustryRankList(stock.getStock_code());
                        if (CollUtil.isNotEmpty(industryRankList)) {
                            industryRankList.forEach(rank -> {
                                Stock tempStock = getStockByCode(rank.getStockCode());
                                if (tempStock == null) {
                                    tempStock = new Stock();
                                    BeanUtils.copyProperties(rank, tempStock);
                                    tempStock.setStock_name(rank.getStockName());
                                    tempStock.setStock_code(rank.getStockCode());
                                    stockMapper.insert(tempStock);
                                }
                            });
                        }
                    }
                }
            } catch (IOException e) {
                log.error("同步股票信息出错", e);
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
        if(CollUtil.isNotEmpty(stocks)){
           long num= stocks.stream().filter(m->m.getStock_name().equals(stockName)).count();
           if(num==0){
               QueryWrapper<Stock> wrapper=new QueryWrapper<>();
               wrapper.eq("stock_name",stockName);
               List<Stock> tmpStock= stockMapper.selectList(wrapper);
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
}
