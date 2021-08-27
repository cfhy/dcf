package com.yyb.entity.tonghuashun;

import lombok.Data;


/**
 * 资产负债表
 */
@Data
public class BalanceSheetExcelEntity {
    //科目
    private String subject;
    //年份1
    private String lastFirstYear;
    //年份2
    private String lastSecondYear;
    //年份3
    private String lastThreeYear;
    //年份4
    private String lastFourYear;
    //年份5
    private String lastFiveYear;
}
