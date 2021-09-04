package com.yyb.dto;

import com.yyb.entity.AnalysisSubject;
import com.yyb.entity.OrgNetFit;
import lombok.Data;

import java.util.List;

@Data
public class StockSum {
    private String stockCode;
    private String stockName;
    private String zyfw;
    private List<AnalysisSubject> sheetList;
    private List<OrgNetFit> orgNetfitList;
}
