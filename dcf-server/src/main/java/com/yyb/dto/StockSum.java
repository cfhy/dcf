package com.yyb.dto;

import com.yyb.entity.AnalysisSubject;
import com.yyb.entity.OrgNetFit;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class StockSum {
    private String stockCode;
    private String stockName;
    private String zyfw;
    private BigDecimal totalShares;
    private List<AnalysisSubject> sheetList;
    private List<OrgNetFit> orgNetfitList;
}
