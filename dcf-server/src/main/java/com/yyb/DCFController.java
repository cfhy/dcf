package com.yyb;

import cn.hutool.core.collection.CollUtil;
import com.yyb.dto.StockSum;
import com.yyb.entity.Stock;
import com.yyb.mapper.OrgNetFitMapper;
import com.yyb.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class DCFController {
    @Autowired
    private SheetAnalysisService sheetAnalysisService;
    @Autowired
    private StockService stockService;
    @Autowired
    private OrgNetfitService orgNetfitService;
    @Autowired
    private AccessLogService accessLogService;
    @Autowired
    private DfcfCrawler dfcfCrawler;

    @GetMapping("/search")
    public List<Stock> search(String keyword) {
        return stockService.selectByKeyword(keyword);
    }


    @GetMapping("/list")
    public Map<String, Object> list(String stockName, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(2);
        List<StockSum> list = new ArrayList<>();
        map.put("industry", "");
        map.put("list", list);

        List<Stock> stockList = stockService.getStockByName(stockName);
        if (CollUtil.isEmpty(stockList)) return map;
        map.put("industry", stockList.get(0).getIndustry());

        for (Stock rank : stockList) {
            try {
                StockSum stockSum = new StockSum();
                stockSum.setStockCode(rank.getStock_code());
                stockSum.setStockName(rank.getStock_name());
                stockSum.setZyfw(rank.getOperate_range());
                stockSum.setTotalShares(rank.getTotal_shares());
                stockSum.setSheetList(sheetAnalysisService.analysis(rank.getStock_code()));

                stockSum.setOrgNetfitList(orgNetfitService.getOrgNetfitList(rank.getStock_code()));
                list.add(stockSum);
            } catch (Exception e) {
                log.error("分析财报出错", e);
            }
        }
        List<Stock> stocks = stockList.stream().filter(m -> m.getStock_name().contains(stockName)).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(stocks)){
            accessLogService.writeLog(stocks.get(0).getStock_code(),stocks.get(0).getStock_name(),request);
        }else{
            accessLogService.writeLog(stockList.get(0).getStock_code(),stockList.get(0).getStock_name(),request);
        }

        return map;
    }

    @GetMapping("/sync")
    public void syncStock() {
        //dfcfCrawler.getTotalShares("SZ000895");
        stockService.syncStock();
        //orgNetfitService.syncOrgNetFit();
        //stockService.updateZYFW("SZ301057");
    }
}
