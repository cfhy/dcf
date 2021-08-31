package com.yyb.service;


import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyb.dto.IndustryRank;
import com.yyb.entity.OrgNetFit;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 从同花顺获取数据
 */
@Service
@Slf4j
public class TongHuaSunCrawler {
    /**
     * 获取机构三年的业绩预测数据
     *
     * @param stockCode
     * @return
     */
    public List<OrgNetFit>  getForecastsProfitsData(String stockCode) {
        String url = String.format("http://basic.10jqka.com.cn/%s/worth.html", stockCode.substring(2));
        List<OrgNetFit> netfitList =new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();

            Elements headList = document.select(".organData>thead>tr>th");
            if(CollUtil.isEmpty(headList))return Collections.emptyList();
            int index=-1;
            for (int i = 0; i < headList.size(); i++) {
                Element element = headList.get(i);
                String text = element.text();
                if (text.contains("预测") && text.contains("（平均）")) {
                    if(index == -1) {
                        index = i;
                    }
                    OrgNetFit profit = new OrgNetFit();
                    String year = text.replace("预测", "").replace("（平均）", "");
                    profit.setYear(Integer.parseInt(year));
                    netfitList.add(profit);
                }
            }
            if(index==-1)return Collections.emptyList();
            Elements netfitRow = document.select(".organData>tbody>tr:nth-child(4)");
            if(CollUtil.isEmpty(netfitRow))return Collections.emptyList();

            Elements bodyList = netfitRow.get(0).children();
            if(CollUtil.isEmpty(netfitRow))return Collections.emptyList();
            if(CollUtil.isNotEmpty(bodyList) && bodyList.size()>=index){
                for (int i = index,j=0; i < bodyList.size(); i++,j++) {
                    netfitList.get(j).setNetfit(bodyList.get(i).select("span").text());
                }
            }
        } catch (IOException e) {
            log.error("获取机构三年的业绩预测数据错误",e);
        }
        return netfitList;
    }

    public List<IndustryRank> getIndustryRankList(String stockCode) {
        String url = String.format("http://basic.10jqka.com.cn/%s/field.html", stockCode.substring(2));
        try {
            Document document = Jsoup.connect(url).get();

            Elements industrys = document.select(".threecate.fl>span");
            if(industrys.size()==0){
                return Collections.emptyList();
            }
            String industry = industrys.get(0).text();

            Elements fieldsChartData = document.select("#fieldsChartData");
            String val = fieldsChartData.val();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(val);

            List<JsonNode> arrayNode = new ArrayList<>();
            root.forEach(m -> {
                arrayNode.add(m);
            });
            //["000895","双汇发展","0.4159","7.275","0.4052","14.41","183.2","371.6","5.890","68.81","16.26","34.65"]
            List<IndustryRank> rankList = new ArrayList<>();
            JsonNode items = arrayNode.get(0);
            for (JsonNode node : items) {
                IndustryRank rank = new IndustryRank();
                rank.setIndustry(industry);
                rank.setStockCode(node.get(0).asText());
                rank.setStockName(node.get(1).asText());
                rank.setNetfit(new BigDecimal(node.get(5).asText()));
                rank.setTotal_revenue(new BigDecimal(node.get(6).asText()));
                rank.setTotal_assets(new BigDecimal(node.get(7).asText()));

                if (rank.getStockCode().startsWith("6")) {
                    rank.setStockCode("SH" + rank.getStockCode());
                } else if (rank.getStockCode().startsWith("0") || rank.getStockCode().startsWith("2") || rank.getStockCode().startsWith("3")) {
                    rank.setStockCode("SZ" + rank.getStockCode());
                }
                rankList.add(rank);
            }
            rankList.sort(Comparator.comparing(IndustryRank::getTotal_assets).reversed());
            return rankList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
