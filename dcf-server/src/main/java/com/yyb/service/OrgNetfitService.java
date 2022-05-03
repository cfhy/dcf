package com.yyb.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yyb.entity.OrgNetFit;
import com.yyb.entity.Stock;
import com.yyb.mapper.OrgNetFitMapper;
import com.yyb.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrgNetfitService {
    @Autowired
    private OrgNetFitMapper orgNetFitMapper;
    @Autowired
    private StockService stockService;
    @Autowired
    private TongHuaSunCrawler tongHuaSunCrawler;

    //每10天同步一次
    @Scheduled(cron = "0 0 0 2,12,22 * ?")
    public void syncOrgNetFit() {
        log.info("syncOrgNetFit同步时间：{}",new Date());
        orgNetFitMapper.delete(Wrappers.emptyWrapper());
        List<Stock> list = stockService.getStockList();
        if (CollUtil.isEmpty(list)) return;
        list.forEach(stock -> {
            try {
                List<OrgNetFit> profitList = tongHuaSunCrawler.getForecastsProfitsData(stock.getStock_code());
                if (CollUtil.isNotEmpty(profitList)) {
                    profitList.forEach(profit -> {
                        OrgNetFit fit = new OrgNetFit();
                        fit.setStock_code(stock.getStock_code());
                        fit.setStock_name(stock.getStock_name());
                        fit.setYear(profit.getYear());
                        fit.setNetfit(profit.getNetfit());
                        fit.setDate_updated(new Date());
                        orgNetFitMapper.insert(fit);
                    });
                }
            }catch (Exception e){
                log.error("OrgNetfitService.syncOrgNetFit error",e);
            }

        });
        log.info("syncOrgNetFit end");
    }

    public List<OrgNetFit> getOrgNetfitList(String stockCode) {
        QueryWrapper<OrgNetFit> wrapper = new QueryWrapper<>();
        wrapper.eq("stock_code", stockCode);
        wrapper.orderByAsc("year");
        wrapper.last("limit 3");
        return orgNetFitMapper.selectList(wrapper);
    }


}
