package com.yyb.service;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyb.dto.IndustryRank;
import com.yyb.entity.OrgNetFit;
import com.yyb.entity.tonghuashun.BalanceSheetEntity;
import com.yyb.entity.tonghuashun.BalanceSheetExcelEntity;
import com.yyb.config.Column;
import com.yyb.entity.tonghuashun.CashFlowSheetEntity;
import com.yyb.entity.tonghuashun.IncomeSheetEntity;
import com.yyb.utils.HtmlunitUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
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

    /**
     * 下载资产负债表（excel）
     *
     * @param stockCode
     * @return
     */
    public List<BalanceSheetEntity> downloadBalanceSheet(String stockCode) {
        String url = String.format("http://basic.10jqka.com.cn/api/stock/export.php?export=debt&type=year&code=%s", stockCode.substring(2));
        InputStream inputStream = HtmlunitUtil.downloadSheet(url);
        if (inputStream == null) {
            throw new RuntimeException("下载资产负债表失败");
        }
        List<BalanceSheetExcelEntity> list = EasyExcel.read(inputStream).head(BalanceSheetExcelEntity.class).sheet().doReadSync();
        try {
            inputStream.close();
        } catch (IOException e) {
            log.info("关闭流失败", e);
        }
        return resolveExcelToModel(list, BalanceSheetEntity.class);
    }

    /**
     * 下载利润表（excel）
     *
     * @param stockCode
     * @return
     */
    public List<IncomeSheetEntity> downloadIncomeSheet(String stockCode) {
        String url = String.format("http://basic.10jqka.com.cn/api/stock/export.php?export=benefit&type=year&code=%s", stockCode.substring(2));
        InputStream inputStream = HtmlunitUtil.downloadSheet(url);
        if (inputStream == null) {
            throw new RuntimeException("下载利润表失败");
        }
        List<BalanceSheetExcelEntity> list = EasyExcel.read(inputStream).head(BalanceSheetExcelEntity.class).sheet().doReadSync();
        try {
            inputStream.close();
        } catch (IOException e) {
            log.info("关闭流失败", e);
        }
        return resolveExcelToModel(list, IncomeSheetEntity.class);
    }

    /**
     * 下载现金流量表（excel）
     *
     * @param stockCode
     * @return
     */
    public List<CashFlowSheetEntity> downloadCashSheet(String stockCode) {
        String url = String.format("http://basic.10jqka.com.cn/api/stock/export.php?export=cash&type=year&code=%s", stockCode.substring(2));
        InputStream inputStream = HtmlunitUtil.downloadSheet(url);
        if (inputStream == null) {
            throw new RuntimeException("下载现金流量表失败");
        }
        List<BalanceSheetExcelEntity> list = EasyExcel.read(inputStream).head(BalanceSheetExcelEntity.class).sheet().doReadSync();
        try {
            inputStream.close();
        } catch (IOException e) {
            log.info("关闭流失败", e);
        }
        return resolveExcelToModel(list, CashFlowSheetEntity.class);
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

    @SuppressWarnings("unchecked")
    private <T> List<T> resolveExcelToModel(List<BalanceSheetExcelEntity> list, Class<T> tClass) {
        //创建泛型类的的对象
        T lastFirstYear, lastSecondYear, lastThreeYear, lastFourYear, lastFiveYear;
        try {
            lastFirstYear = tClass.getConstructor().newInstance();
            lastSecondYear = tClass.getConstructor().newInstance();
            lastThreeYear = tClass.getConstructor().newInstance();
            lastFourYear = tClass.getConstructor().newInstance();
            lastFiveYear = tClass.getConstructor().newInstance();
        } catch (Exception e) {
            log.info("创建泛型对象出错", e);
            throw new RuntimeException("解析excel出现错误");
        }

        Field[] declaredFields = tClass.getDeclaredFields();
        Map<String, Field> mapField = new HashMap<>();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Column annotation = field.getAnnotation(Column.class);
            mapField.put(annotation.name(), field);
        }
        list.forEach(balance -> {
            Field field = mapField.get(balance.getSubject());
            if (field != null) {
                try {
                    field.set(lastFirstYear, handVal(field.getGenericType(), balance.getLastFirstYear()));
                    field.set(lastSecondYear, handVal(field.getGenericType(), balance.getLastSecondYear()));
                    field.set(lastThreeYear, handVal(field.getGenericType(), balance.getLastThreeYear()));
                    field.set(lastFourYear, handVal(field.getGenericType(), balance.getLastFourYear()));
                    field.set(lastFiveYear, handVal(field.getGenericType(), balance.getLastFiveYear()));
                } catch (IllegalAccessException e) {
                    log.info("字段赋值失败", e);
                }
            }
        });
        return Arrays.asList(lastFirstYear, lastSecondYear, lastThreeYear, lastFourYear, lastFiveYear);
    }

    private Object handVal(Type type, String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        }
        if ("--".equals(val)) {
            return null;
        }
        if (type.getTypeName().equals("java.lang.String")) {
            return val;
        } else if (type.getTypeName().equals("java.math.BigDecimal")) {
            return new BigDecimal(val);
        } else if (type.getTypeName().equals("java.lang.Integer")) {
            return Integer.valueOf(val);
        }
        return val;
    }




}
